package employes;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AuthentificationServlet
 */
@WebServlet("/AuthentificationServlet")
public class AuthentificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static String PAGE_HEADER = "<html><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/><head><title>helloworld</title><body>";
	static String PAGE_FOOTER = "</body></html>";
	Employes myEmployes = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthentificationServlet() {
        
    	super();
    	myEmployes = Employes.instance();
    	myEmployes.addEmploye("coucou", "hello");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//Basics
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println(PAGE_HEADER);
		
		Boolean credential = myEmployes.checkCredentials(request.getParameter("login"), request.getParameter("pwd"));
		
		if(credential){
			initSessionCookies(request, response);
			response.sendRedirect("/LeaveManagement/Conges/demandeConge.jsp");  
			//RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/Conges/demandeConge.jsp");
			//dispatcher.include(request, response);
		}
		else{
			response.sendRedirect("/LeaveManagement/Employes/error.jsp");  
			//RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/Employes/error.jsp");
			//dispatcher.include(request, response);
		}
		
		writer.println(PAGE_FOOTER);
		writer.close();
	}
	
	/**
	 * Initialize Session and cookies
	 * @param request
	 * @param response
	 */
	private static void initSessionCookies(HttpServletRequest request, HttpServletResponse response){
		//Création de la session
		HttpSession session = request.getSession();
		session.setAttribute("login", request.getParameter("login"));
		session.setAttribute("pwd", request.getParameter("pwd"));
		session.setMaxInactiveInterval(50);
		
		//Cookies
		Cookie loginCookie = new Cookie("login", request.getParameter("login"));
		Cookie pwdCookie = new Cookie("pwd", request.getParameter("pwd"));
		
		//Tps vie cookie, 4j
		loginCookie.setMaxAge(90);
		pwdCookie.setMaxAge(90);
		
		
		//Ajout des cookies
		response.addCookie(loginCookie);
		response.addCookie(pwdCookie);
	}
}
