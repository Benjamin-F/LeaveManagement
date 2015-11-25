package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Employes;

/**
 * Servlet implementation class AuthentificationServlet
 */
@WebServlet("/AuthentificationServlet")
public class AuthentificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ATT_SESSION_USER="sessionUtilisateur";
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
		
	
		String login=request.getParameter("login");
		String pwd=request.getParameter("pwd");
		
		Boolean credential = myEmployes.checkCredentials(login,pwd );
		HttpSession session = request.getSession();

		if(credential){
//			initSessionCookies(request, response);
			//initSession(login,pwd,request,response);
			session.setAttribute(this.ATT_SESSION_USER,login);
			response.sendRedirect("/LeaveManagement/Conges/demandeConge.jsp"); 
			//this.getServletContext().getRequestDispatcher("/Conges/demandeConge.jsp").forward(request, response);
		}
		else{
			session.setAttribute(this.ATT_SESSION_USER, null);
			response.sendRedirect("/LeaveManagement/Employes/error.jsp");  
			//RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/Employes/error.jsp");
			//dispatcher.include(request, response);
		}
		
	}
//	private void initSession(String login,String pwd,HttpServletRequest request, HttpServletResponse response){
//		//Création de la session
//		HttpSession session = request.getSession();
//		String sessionNumber=null;
//		try {
//			sessionNumber = createSessionNumber(login,pwd);
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		session.setAttribute("sessionUtilisateur",sessionNumber);
//			
//	}
//	private  String createSessionNumber(String login,String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//		MessageDigest digest = MessageDigest.getInstance("SHA-256");
//		digest.reset();
//		//Pour créer le numéro de session on utilise la date le login et le pwd
//		
//		digest.update((login+"bfeg"+Calendar.getInstance().toString()).getBytes());
//		byte[] input = digest.digest((password+login+Calendar.getInstance().toString()).getBytes("UTF-8"));
//		digest.reset();
//		input = digest.digest(input);
//		return input.toString();
//	}
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