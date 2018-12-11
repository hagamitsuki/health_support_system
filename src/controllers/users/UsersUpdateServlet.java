package controllers.users;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import models.validators.UserValidator;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class UsersUpdateServlet
 */
@WebServlet("/users/update")
public class UsersUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _token = (String)request.getParameter("_token");
		if(_token != null && _token.equals(request.getSession().getId())){
		    EntityManager em = DBUtil.createEntityManager();

		    User u = em.find(User.class, (Integer)(request.getSession().getAttribute("user_id")));


		    Boolean code_duplicate_check = true;
		    if(u.getCode().equals(request.getParameter("code"))){
		        code_duplicate_check = false;
		    }else{
		        u.setCode(request.getParameter("code"));
		    }
	        /*もし現在のユーザ番号が、更新のために入力されたユーザ番号と同じならcode_duplicate_check = false
             *現在値と入力値が異なれば、入力値をセット*/



		    Boolean password_check_flag =true;
		    String password = request.getParameter("password");
            if(password == null || password.equals("")) {
                password_check_flag = false;
            } else {
                u.setPassword(
                        EncryptUtil.getPasswordEncrypt(
                                password,
                                (String)this.getServletContext().getAttribute("salt")
                                )
                        );
            }
            /*もしパスワードが入力されてなかったら（変更されなかったら）、password__check_flag = false
             *それ以外（入力されていれば）、入力値を暗号化してセット。*/

            u.setName(request.getParameter("name"));
            u.setAdmin_flag(Integer.parseInt(request.getParameter("admin_flag")));
            u.setUpdated_at(new Timestamp(System.currentTimeMillis()));
            u.setDelete_flag(0);

            List<String> errors = UserValidator.validate(u, code_duplicate_check, password_check_flag);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("user", u);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/edit.jsp");
                rd.forward(request, response);
            }else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("user_id");

                response.sendRedirect(request.getContextPath() + "/users/index");
            }

		}
	}

}
