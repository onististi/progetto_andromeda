package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import classi.TicketClass;
import beans.TicketBean;

@WebServlet("/ticket")
public class ticketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ticketServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("h")!=null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("ticket?h");
			dispatcher.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext sc = request.getSession().getServletContext();
		TicketClass ticket = new TicketClass( request.getParameter("partenza"), request.getParameter("arrivo"), request.getParameter("ora"), request.getParameter("giorno"));
		
		try {			

	        HttpSession session = request.getSession();
	        HashMap<Integer,ArrayList<TicketBean>> tickets = ticket.creaTickets();
	        
	  	    if(tickets.isEmpty())
	  	    	response.sendRedirect("ticket?h&e=1");
	  	    else {
	  	    	session.setAttribute("tickets", null);
			    session.setAttribute("tickets",tickets);
			    //response.sendRedirect("pages/ticket.jsp");
			   // Cookie cookie = new Cookie("partenza", request.getParameter("partenza"));
			    
			    //Cookie cookie2 = new Cookie("arrivo", request.getParameter("arrivo"));
			    
			    System.out.println("c: "+request.getParameter("partenza")+ " "+ request.getParameter("arrivo"));
			     //response.addCookie(cookie);
			    //response.addCookie(cookie2);
			    request.setAttribute("partenza", request.getParameter("partenza"));
			    request.setAttribute("arrivo", request.getParameter("arrivo"));
				RequestDispatcher dispatcher = request.getRequestDispatcher("ticket?h");
				dispatcher.forward(request, response);
	  	    }
	    } catch(SQLException e) {printSQLException(e);} catch (ClassNotFoundException e) {e.printStackTrace();}		
	}		

	private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Errorore: " + ((SQLException) e).getErrorCode());
                System.err.println("Messaggio: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Causa: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}