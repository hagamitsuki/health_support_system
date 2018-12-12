package controllers.toppage;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Mood;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class TopPageIndexServlet
 */
@WebServlet("/index.html")//コンテキストパス"/"のこと
public class TopPageIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopPageIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    if(request.getSession().getAttribute("flush") != null) {
	        request.setAttribute("flush", request.getSession().getAttribute("flush"));
	        request.getSession().removeAttribute("flush");
	    }

	    /*ログインしたユーザーが登録している気分を取得して送る*/
	    EntityManager em = DBUtil.createEntityManager();

        User login_user = (User)request.getSession().getAttribute("login_user");

        List<Mood> moods = em.createNamedQuery("getMyAllMoods", Mood.class)//getMyAllMoodsを以下の条件で実行するよ
                              .setParameter("user", login_user)//条件："user"は上で取得したlogin_userですよ
                              .getResultList();

        em.close();

        request.setAttribute("moods", moods);


	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/index.jsp");
        rd.forward(request, response);




	}

}

