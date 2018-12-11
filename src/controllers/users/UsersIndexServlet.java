package controllers.users;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class UsersIndexServlet
 */
@WebServlet("/users/index")
public class UsersIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();

		int page = 1;
		try{
		    page = Integer.parseInt(request.getParameter("page"));
		} catch(NumberFormatException e){ }
		List<User> users = em.createNamedQuery("getAllUsers", User.class)
		                     /*第二引数"User.class"はクエリの結果を格納するクラスで、そのクラス達を左辺のuserリストに格納する*/
		                     .setFirstResult(15 * (page - 1))
		                     .setMaxResults(15)
		                     .getResultList();

		long users_count = (long)em.createNamedQuery("getUsersCount", Long.class)
		                    /*上に同じ。全てのユーザーの数を数えて、その数を左辺のlong型変数users_countに格納*/
		                            .getSingleResult();

	    em.close();


	    request.setAttribute("users", users);
        request.setAttribute("users_count", users_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush") != null){
            /*createやupdateで更新があれば"flush"が渡されるのでそれもindex.jspに送る*/
            request.setAttribute("flush",request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/index.jsp");
        rd.forward(request, response);



	}

}
