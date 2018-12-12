package controllers.moods;

import java.io.IOException;

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
 * Servlet implementation class MoodsEditServlet
 */
@WebServlet("/moods/edit")
public class MoodsEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoodsEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    EntityManager em = DBUtil.createEntityManager();

        Mood m = em.find(Mood.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        User login_user = (User)request.getSession().getAttribute("login_user");
        if(login_user.getId() == m.getUser().getId()) {
            request.setAttribute("mood", m);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("mood_id", m.getId());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/moods/edit.jsp");
        rd.forward(request, response);
    }

}