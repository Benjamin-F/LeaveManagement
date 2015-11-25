package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
	private static final String ATT_SESSION_USER = "sessionUtilisateur";
	private static final int MAX_AGE_COOKIES = 60 * 60 * 24 * 4;// 4 jours
	private static final String COOKIES_YES_OR_NO = "useCookies";
	public static final String COOKIE_CONNEXION_PWD = "conpwd";
	public static final String COOKIE_CONNEXION_LOGIN= "conl";

	public static final String FORMAT_DATE = "dd/MM/yyyy HH:mm:ss";
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//        String log = getCookieValue( request, this.COOKIE_CONNEXION_LOGIN );
//        String pwd= getCookieValue( request, this.COOKIE_CONNEXION_PWD);
//        /* Si le cookie existe, alors calcul de la durée */
//    	Boolean credential = myEmployes.checkCredentials(log, pwd);
//		HttpSession session = request.getSession();
//        if ( log != null && pwd !=null ) {
//
//        	//On vérifie les infos de connexions
//    		if (credential) {
//
//    			session.setAttribute(this.ATT_SESSION_USER, log);
//
//    			response.sendRedirect("/LeaveManagement/Conges/demandeConge.jsp");
//
//    		} else {
//    			session.setAttribute(this.ATT_SESSION_USER, null);
//    			response.sendRedirect("/LeaveManagement/Employes/error.jsp");
//
//    		}
//
//        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("login");
		String pwd = request.getParameter("pwd");

		Boolean credential = myEmployes.checkCredentials(login, pwd);
		HttpSession session = request.getSession();
		
		//On vérifie si l'utilisateur souhaite des cookies
		if (request.getParameter(this.COOKIES_YES_OR_NO) != null) {
			/* Récupération de la date courante */
			Date dt = Calendar.getInstance().getTime();
			/* Formatage de la date et conversion en texte */
			SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
			String dateConnexion = dateFormat.format(dt);

			/* Création du cookie, et ajout à la réponse HTTP */
			setCookie(response, this.COOKIE_CONNEXION_PWD, pwd, MAX_AGE_COOKIES);
			setCookie(response, this.COOKIE_CONNEXION_LOGIN, login, MAX_AGE_COOKIES);
		} else {
			//age 0 pour un cookie le détruit
			setCookie(response, this.COOKIE_CONNEXION_PWD, "", 0);
			setCookie(response, this.COOKIE_CONNEXION_LOGIN, "", 0);
		}
		
		//On vérifie les infos de connexions
		if (credential) {

			session.setAttribute(this.ATT_SESSION_USER, login);

			response.sendRedirect("/LeaveManagement/Conges/demandeConge.jsp");

		} else {
			session.setAttribute(this.ATT_SESSION_USER, null);
			response.sendRedirect("/LeaveManagement/Employes/error.jsp");

		}

	}

	/*
	 * Initialisation de notre cookie
	 */
	private static void setCookie(HttpServletResponse response, String nom, String valeur, int maxAge) {

		Cookie cookie = new Cookie(nom, valeur);

		cookie.setMaxAge(maxAge);

		response.addCookie(cookie);

	}
	 private static String getCookieValue( HttpServletRequest request, String nom ) {

	        Cookie[] cookies = request.getCookies();

	        if ( cookies != null ) {

	            for ( Cookie cookie : cookies ) {

	                if ( cookie != null && nom.equals( cookie.getName() ) ) {

	                    return cookie.getValue();

	                }

	            }

	        }

	        return null;

	    }

}
