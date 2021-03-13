package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import classi.TicketClass;
import classi.Cript;
import beans.TicketBean;

@WebServlet("/ticket")
public class ticket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ticket() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext sc = request.getSession().getServletContext();
		
		TicketClass ticket = new TicketClass( request.getParameter("partenza"), request.getParameter("arrivo"), request.getParameter("ora"), request.getParameter("giorno"));
		
		try {			

	        HttpSession session = request.getSession();
	  	   // session.setAttribute("tickets", ticket.creaTickets());
	  	    //response.sendRedirect("pages/ticket.jsp");
			
	  	    
	  	  request.setAttribute("tickets", ticket.creaTickets());
			RequestDispatcher dispatcher = request.getRequestDispatcher("pages/ticket.jsp");
			dispatcher.forward(request, response);
	  	    
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