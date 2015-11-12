package conges;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VerificationServlet
 */
@WebServlet("/VerificationServlet")
public class VerificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static String PAGE_HEADER = "<html><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/><head><title>helloworld</title><body>";
	static String PAGE_FOOTER = "</body></html>";
	
	Conges myConges;
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerificationServlet() {
        super();
        myConges = Conges.instance();
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
		
		//Convert birth date to Date format
		String leaveD = request.getParameter("leaveDate");
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = format.parse(leaveD);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//Get day number
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayNumber = cal.get(Calendar.DAY_OF_YEAR);
		
		Boolean booked;
		Boolean availability = myConges.verifierJour(dayNumber);
		
		//Set day
		if(availability){
			myConges.poserJour(dayNumber);
			booked = true;
		}
		else
			booked = false;
		
		//Add status to request
		request.setAttribute("booked", booked);
		
		//Redirection
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/Conges/resultatDemande.jsp");
		dispatcher.include(request, response);
	
		writer.println(PAGE_FOOTER);
		writer.close();
	}

}
