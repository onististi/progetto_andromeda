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

import classi.Cript;
import beans.TicketsBean;

@WebServlet("/ticket")
public class ticket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ticket() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext sc = request.getSession().getServletContext();
		int result = 0;
		String partenza = request.getParameter("partenza");
		String arrivo = request.getParameter("arrivo");
		String ora = request.getParameter("ora");
		String giorno = request.getParameter("giorno");
		
		ArrayList<TicketsBean> tickets = new ArrayList<TicketsBean>();
		
		String sql="SELECT percorso.id AS percorso_id, direzione.nome as nome_Linea, F.comune, F.nome, F.coordinate,fermata_percorso.orario, fermata_percorso.ritardo FROM percorso "
				+ "INNER JOIN direzione on direzione.id = percorso.id_direzione "
				+ "INNER JOIN fermata_percorso on fermata_percorso.id_percorso = percorso.id "
				+ "INNER JOIN fermata F on F.id = fermata_percorso.id_fermata "
				+ "WHERE fermata_percorso.orario > '14:20' "
				+ "AND fermata_percorso.andata = 0 "
				+ "AND percorso.id IN("
				+ "SELECT id AS mmm FROM percorso "
				+ "JOIN fermata_percorso on fermata_percorso.id_percorso = percorso.id "
				+ "JOIN (SELECT DISTINCT id AS KK FROM fermata \r\n"
				+ "WHERE fermata.nome = \"Domodossola F.S.\") FE on FE.KK = fermata_percorso.id_fermata) "
				+ "AND percorso.id IN(  "
				+ "SELECT id AS mmm FROM percorso "
				+ "JOIN fermata_percorso on fermata_percorso.id_percorso = percorso.id "
				+ "JOIN (SELECT DISTINCT id AS KK FROM fermata "
				+ "WHERE fermata.nome = \"Viganella\") FEE on FEE.KK = fermata_percorso.id_fermata) "
				+ "ORDER BY percorso.id, fermata_percorso.orario";
		
		try {
			
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/androme", "aro", "cavolo22");
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        rs.next();
	                
	        
	        String h = rs.getString("nome");
	        
	        HttpSession session = request.getSession();
	  	  session.setAttribute("tickets",h);
	  	  response.sendRedirect("pages/ticket.jsp");
			con.close();
			
	    } catch(SQLException e) {printSQLException(e);} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
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