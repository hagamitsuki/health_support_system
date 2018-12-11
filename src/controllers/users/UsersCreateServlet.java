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
 * Servlet implementation class UserCreateServlet
 */
@WebServlet("/users/create")
public class UsersCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _token = (String)request.getParameter("_token");
		if(_token != null && _token.equals(request.getSession().getId())){
		    /*_tokenがnullでない、かつ_tokenがセッションIDと同じなら、
		     * つまり、きちんとフォームから送られてきていたら以下を実行せよ*/
		    EntityManager em = DBUtil.createEntityManager();

		    User u = new User();

		    u.setCode(request.getParameter("code"));
		    u.setName(request.getParameter("name"));
		    u.setPassword(
		            /*入力したパスワードにsaltをくっつけて暗号化した後、setPassword*/
		            EncryptUtil.getPasswordEncrypt(
		                    request.getParameter("password"),
		                    (String)this.getServletContext().getAttribute("salt")
		                    )
		            );
		    u.setAdmin_flag(Integer.parseInt(request.getParameter("admin_flag")));

		    Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		    u.setCreated_at(currentTime);
		    u.setUpdated_at(currentTime);
            u.setDelete_flag(0);

            /*入力情報をセットした後、空欄がないか以下でチェック*/

            /* 空欄有：エラー内容をセットし、new.jspに送る*/
            List<String> errors = UserValidator.validate(u, true, true);
            if(errors.size() > 0){
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("user", u);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/new.jsp");
                rd.forward(request, response);
            }else{
            /* 空欄無：データベースに情報を保存した後、flushをセットしindex.jspに送る。*/

                em.getTransaction().begin();
                em.persist(u);
                em.getTransaction().commit();
                em.close();

                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/users/index");
            }
		}
	}

}
