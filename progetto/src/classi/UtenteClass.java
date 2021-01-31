package classi;

import java.sql.*;
import beans.UtenteBean;

public class UtenteClass {

	public int registrazione(UtenteBean utente) throws ClassNotFoundException, SQLException {
		int result = 0;
		
		String sql="INSERT INTO utente (username, password) VALUES (?, ?)";
		
		Class.forName("com.mysql.jdbc.Driver");
		
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/androme?useSSL=false", "root", "root")){
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, utente.getUsername());
			ps.setString(2, utente.getPassword());
			
			result = ps.executeUpdate();	
			
		}catch (SQLException e) {
			printSQLException(e);
		}
		
		return result;
	}
		
	private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
