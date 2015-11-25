package controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class RestrictionFilter
 */
@WebFilter("/RestrictionFilter")
public class RestrictionFilter implements Filter {

    private static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String COOKIE_CONNEXION_LOGIN= "conl";
    public static final String COOKIE_CONNEXION_PWD = "conpwd";

	/**
     * Default constructor. 
     */
    public RestrictionFilter() {
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
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		 HttpServletRequest request = (HttpServletRequest) req;
		 
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session= request.getSession();
		if (session.getAttribute(ATT_SESSION_USER)==null && AuthentificationServlet.getCookieValue(request, this.COOKIE_CONNEXION_LOGIN)==null) {
			response.sendRedirect("/LeaveManagement/Employes/index.jsp");
		}
		else{
			if(session.getAttribute(ATT_SESSION_USER)==null){
				String login = AuthentificationServlet.getCookieValue(request, this.COOKIE_CONNEXION_LOGIN);
				session.setAttribute(this.ATT_SESSION_USER, login);
			}
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
