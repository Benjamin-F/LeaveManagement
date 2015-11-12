package employes;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
		//Création de la session
		HttpSession session = request.getSession(true);
		
		//Basics
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println(PAGE_HEADER);
		
		Boolean credential = myEmployes.checkCredentials(request.getParameter("login"), request.getParameter("pwd"));
		
		if(credential){
			initSession(session, request.getParameter("login"), request.getParameter("pwd"));
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/Conges/demandeConge.jsp");
			dispatcher.include(request, response);
		}
		else{
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/Employes/error.jsp");
			dispatcher.include(request, response);
		}
		
		writer.println(PAGE_FOOTER);
		writer.close();
	}
	
	protected void initSession(HttpSession session, String login, String pwd){
		if(session.isNew()){
			session.setAttribute("login", login);
			session.setAttribute("pwd", pwd);
		}
	}

}
