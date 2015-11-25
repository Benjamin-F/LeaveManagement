package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DeconnexionServlet
 */
@WebServlet("/DeconnexionServlet")
public class DeconnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String COOKIE_CONNEXION_LOGIN= "conl";
	public static final String COOKIE_CONNEXION_PWD = "conpwd";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeconnexionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		deleteCookie(request, response);
	    deleteCookie(request, response);
		
	    /* Récupération et destruction de la session en cours */
        HttpSession session = request.getSession();
        session.invalidate();
       

        /* Redirection vers l'index */
        response.sendRedirect( "/LeaveManagement/Employes/index.jsp" );
	}

	private static void deleteCookie( HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null)
	        for (int i = 0; i < cookies.length; i++) {
	            cookies[i].setValue("");
	            cookies[i].setPath("/");
	            cookies[i].setMaxAge(0);
	            response.addCookie(cookies[i]);
	        }
    }
	

}
