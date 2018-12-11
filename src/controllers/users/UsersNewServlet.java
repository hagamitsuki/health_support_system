package controllers.users;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;

/**
 * Servlet implementation class UsersNewServlet
 */
@WebServlet("/users/new")
public class UsersNewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    /*     .setAttribute("名前"  , 中身) */
	    request.setAttribute("_token", request.getSession().getId());
	    /* request.getSession().getId()：
	     * フォームから hidden 要素で送られた値とセッションに格納された値が同一であれば送信を受け付けるようにする、というもの。
	     * こうすることで、サイト外からPOST送信された投稿を拒否することができる。ここではセッションIDと呼ばれるものを利用している。
	     * セッションID：セッションに割り当てたID。セッションを識別するための情報
	     * 今回はせっしょんIDに_tokenという名前を付けた。*/
		request.setAttribute("user", new User());

		 RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/new.jsp");
	     rd.forward(request, response);
	}

}
