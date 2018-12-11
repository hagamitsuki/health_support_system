package controllers.moods;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Mood;

/**
 * Servlet implementation class ReportsNewServlet
 */
@WebServlet("/moods/new")
public class MoodsNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoodsNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());

        Mood m = new Mood();
        request.setAttribute("mood", m);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/moods/new.jsp");
        rd.forward(request, response);
    }

}