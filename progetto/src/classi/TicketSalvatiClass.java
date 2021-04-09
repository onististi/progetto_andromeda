package classi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.*;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.sun.jdi.Value;

public class TicketSalvatiClass {

	public TicketSalvatiClass() {}
	
	public void salva(int id_utente, String partenza, String arrivo) throws ClassNotFoundException {
		
		int result = 0;
		String sql="INSERT INTO tratta_preferita (fk_utente, partenza, arrivo) VALUES (?, ?, ?)";
		Class.forName("com.mysql.jdbc.Driver");
		
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/androme", "aro", "cavolo22")){
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id_utente);
			ps.setString(2, partenza);
			ps.setString(3, arrivo);
			
			result = ps.executeUpdate();	
	
		}catch (SQLException e) {printSQLException(e);}
	}

	
	public String carica(int id_utente) throws ClassNotFoundException { //per il menu
		
		int result = 0;
		String sql="SELECT * FROM tratta_preferita WHERE fk_utente= "+id_utente;
		
		try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/androme", "aro", "cavolo22");
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        
	        HashMap<Integer, ArrayList<String>> map = new HashMap();
	        
	        int i=0;
	        if(!rs.next())
	        	return "";
	        
	        rs.previous();
	        while(rs.next()) {
	        	map.put(i, new ArrayList(Arrays.asList(rs.getString("partenza"),rs.getString("arrivo"))));
	        	i++;
	        }
	        
	       String risposta = new Gson().toJson(map);
	        
	      return risposta;
		}catch (SQLException e) {printSQLException(e);}
		return "";
	}
	
	public void elimina(int id_utente, String partenza, String arrivo) throws ClassNotFoundException {
		int result = 0;
		String sql="DELETE FROM tratta_preferita WHERE fk_utente = '"+String.valueOf(id_utente)+"' AND partenza = '"+partenza+"' AND arrivo = '"+arrivo+"'";
		System.out.println(sql);
		try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/androme", "aro", "cavolo22");
	        Statement stmt = con.createStatement();
	        result = stmt.executeUpdate(sql);
	        
	        if(result==0)
	        		System.out.println("eliminato");
	        
		}catch (SQLException e) {printSQLException(e);}
	}
	
	public boolean check(int id_utente, String partenza, String arrivo) throws ClassNotFoundException { //per colorare la stella (fare nell if delle sessioni dell utgente un altro if per vedere se ce if(session || gia salvata)
		
		String sql="SELECT * FROM tratta_preferita WHERE fk_utente= "+id_utente+" AND partenza='"+partenza+"' AND arrivo= '"+arrivo+"'";
		//System.out.println("chetko: "+partenza+arrivo);
		
		Class.forName("com.mysql.jdbc.Driver");
		
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/androme", "aro", "cavolo22")){
			ResultSet rs;
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(sql); 
			
			if(rs.next()) {
				//System.out.println("tratta salvata");
				return true;
			}else {
				//System.out.println("non salvata");
				return false;
			}
		}catch (SQLException e) {printSQLException(e);}
		
		return false;
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