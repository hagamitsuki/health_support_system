package controllers.results;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Mood;
import utils.DBUtil;

/**
 * Servlet implementation class ResultsIndexServlet
 */
@WebServlet("/results/index")
public class ResultsIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultsIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();

        Mood m = em.find(Mood.class, Integer.parseInt(request.getParameter("mood.id")));
        /*       em.find(エンティティ, 主キー)
         * マイページで選択した"気分"のidを受け取り、そのidを持つ"気分"をDBから取得し変数"m"に格納*/

        em.close();

        request.setAttribute("mood", m);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/results/index.jsp");
        rd.forward(request, response);
    }
}