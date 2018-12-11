package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.User;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    /**
     * Default constructor.
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String context_path = ((HttpServletRequest)request).getContextPath();
        String servlet_path = ((HttpServletRequest)request).getServletPath();

        if(!servlet_path.matches("/css.*")) { //『cssでないフィルターである場合』＝『css以外全てにフィルターをかけよ』という意味

            HttpSession session = ((HttpServletRequest)request).getSession();
            User u = (User)session.getAttribute("login_user");
            /*セッションスコープに入っていた"login_user"情報を、User型変数"u"に格納
             * つまり、条件式(u != null)はログインしている状態を指す*/

            if(!servlet_path.equals("/login")) {//(★)：ログインページ以外に行ったとき
                if(u == null) {//(★)かつログインしていなかったら・・・
                    ((HttpServletResponse)response).sendRedirect(context_path + "/login");
                    return;
                }/* "/login" 以外のページにアクセスした場合、ログイン状態でなければ /login ページに強制的にリダイレクトさせる。*/

                if(servlet_path.matches("/users.*") && u.getAdmin_flag() == 0) {//(★)かつユーザー管理ページにアクセスし、かつ一般ユーザーだったら・・・
                    ((HttpServletResponse)response).sendRedirect(context_path + "/");
                    return;
                }/*ユーザー管理（/users）のページにアクセスした場合、ログインしているユーザー情報の admin_flag をチェックし、
                  *一般ユーザーであればトップページへリダイレクトさせるようにしている*/

            } else {//(☆)：ログインページに行ったとき
                if(u != null) {//(☆)かつログインしていたら・・・
                    ((HttpServletResponse)response).sendRedirect(context_path + "/");
                    return;
                }/*ログインしているのにログインページを表示させる理由がないので、そのようなアクセスがあった場合はトップページへリダイレクトさせる*/
            }
        }

        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}