package classi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap; 
import java.util.Map;
import javax.servlet.http.HttpSession;
import beans.TicketBean;

public class TicketClass {
	
	private String sql;
	private int andata =0;
	public ResultSet rs;
	public ResultSet rsA;
	public String partenza;
	public String arrivo;
	public String ora;
	public String giorno;	
	public ArrayList<TicketBean> tickets;
	public HashMap<Integer,ArrayList<TicketBean>> spezzato;
	
	public TicketClass(String p, String a, String o, String g) {
		this.partenza = p;
		this.arrivo = a;
		this.ora = "12:00";
		this.giorno = g;
		this.tickets = new ArrayList<TicketBean>();
		this.spezzato = new HashMap<Integer,ArrayList<TicketBean>>();
	}
	
	
	public int getAndata() {return this.andata;}
	public void setAndata(int n) {this.andata=n;}
	public void setSql(boolean a) {
		
		if(!a)
			this.sql = "SELECT percorso.id AS percorso_id, direzione.nome as nome_Linea, F.comune, F.nome, F.coordinate,fermata_percorso.orario, fermata_percorso.ritardo FROM percorso "
					+ "INNER JOIN direzione on direzione.id = percorso.id_direzione "
					+ "INNER JOIN fermata_percorso on fermata_percorso.id_percorso = percorso.id "
					+ "INNER JOIN fermata F on F.id = fermata_percorso.id_fermata "
					+ "WHERE fermata_percorso.andata = 0 "
					+ "AND percorso.id IN("
					+ " SELECT percorso.id AS iddd FROM percorso"
					+ "    JOIN fermata_percorso on fermata_percorso.id_percorso = percorso.id"
					+ "    JOIN fermata on fermata.id = fermata_percorso.id_fermata"
					+ "    	WHERE fermata.comune = '" +this.partenza +"' AND fermata_percorso.orario > '"+this.ora+"')"
					+ "AND percorso.id IN("
					+ " SELECT percorso.id AS iddd FROM percorso "
					+ "    JOIN fermata_percorso on fermata_percorso.id_percorso = percorso.id "
					+ "    JOIN fermata on fermata.id = fermata_percorso.id_fermata "
					+ "    	WHERE fermata.comune = '" +this.arrivo +"' AND fermata_percorso.orario > '"+this.ora+"')"
					+ "ORDER BY percorso.id, fermata_percorso.orario";
		else
			this.sql = "SELECT percorso.id AS percorso_id, direzione.nome as nome_Linea, F.comune, F.nome, F.coordinate,fermata_percorso.orario, fermata_percorso.ritardo FROM percorso "
					+ "INNER JOIN direzione on direzione.id = percorso.id_direzione "
					+ "INNER JOIN fermata_percorso on fermata_percorso.id_percorso = percorso.id "
					+ "INNER JOIN fermata F on F.id = fermata_percorso.id_fermata "
					+ "WHERE fermata_percorso.andata = 1 "
					+ "AND percorso.id IN("
					+ " SELECT percorso.id AS iddd FROM percorso"
					+ "    JOIN fermata_percorso on fermata_percorso.id_percorso = percorso.id"
					+ "    JOIN fermata on fermata.id = fermata_percorso.id_fermata"
					+ "    	WHERE fermata.comune = '" +this.partenza +"' AND fermata_percorso.orario > '"+this.ora+"')"
					+ "AND percorso.id IN("
					+ " SELECT percorso.id AS iddd FROM percorso "
					+ "    JOIN fermata_percorso on fermata_percorso.id_percorso = percorso.id "
					+ "    JOIN fermata on fermata.id = fermata_percorso.id_fermata "
					+ "    	WHERE fermata.comune = '" +this.arrivo +"' AND fermata_percorso.orario > '"+this.ora+"')"
					+ "ORDER BY percorso.id, fermata_percorso.orario";
		
		//System.out.println(this.sql);
	}
	
	public HashMap<Integer,ArrayList<TicketBean>> creaTickets() throws SQLException, ClassNotFoundException {
		
		setSql(false);	//false sta per andata= 0
		if(query(false)) {  
	    this.andata = controlla_andata(this.rsA);
	 		System.out.println("andata "+andata); 	
		
	 	if(this.andata == 0) 
	 		creaBean();
	 	else {
	 		setSql(true);	//è vero è ritorno
	 		query(true);
	 		creaBean();
	 	}
	 	
	 	stampabean(); 
	 	
	 	dividiTickets();
	 	stampaS();
	 	return this.spezzato;
		}else {
			return this.spezzato;
		}
	}
	
	
	public void dividiTickets() {
	
		for(int i=0;i<this.tickets.size();i++) {
			
			if(this.tickets.get(i).getId_percorso()!= this.tickets.get(i).getId_percorso()) 
				spezzato.put(this.tickets.get(i).getId_percorso(),new ArrayList<TicketBean>(Arrays.asList(tickets.get(i))));
				
			else if(spezzato.containsKey(this.tickets.get(i).getId_percorso())){
				
				ArrayList<TicketBean> campo = spezzato.get(this.tickets.get(i).getId_percorso());
				campo.add(tickets.get(i));
				spezzato.put(this.tickets.get(i).getId_percorso(),campo);
				
			}else 		
				spezzato.put(this.tickets.get(i).getId_percorso(),new ArrayList<TicketBean>(Arrays.asList(tickets.get(i))));
				
		}
	}
	
	public void stampaS() {
		for (Map.Entry<Integer,ArrayList<TicketBean>> me : this.spezzato.entrySet()) {
			ArrayList<TicketBean> a = me.getValue();
			System.out.println("Key: "+me.getKey());
				for(int i =0;i<a.size();i++) {
				 TicketBean b = a.get(i);
		         System.out.printf(" & Value: " + b.getNome());
				}
				System.out.println("--------------------");
	        }
	}
	
	public int controlla_andata(ResultSet rsA) throws SQLException {
        while(rsA.next()) 
        	if(rsA.getString("comune").equals(this.partenza)) 
        		return 0;
        	else if(rsA.getString("comune").equals(this.arrivo))
        		return 1;
        
		return 0;
	}
	
	
	public boolean query(boolean r) throws ClassNotFoundException {
		try {
			System.out.println(this.partenza+this.arrivo);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/androme", "aro", "cavolo22");
		   Statement stmt = con.createStatement();
		   this.rs = stmt.executeQuery(this.sql);
		   
		  
		   if(!this.rs.next()){
			   return false;
		   }else
			   rs.previous();
		   
		   if(!r) //se è la prima volta che entra ovvero quando la chiama subito in creaBiglietto
			   this.rsA = rs;   
		   return true;
		} catch(SQLException e) {printSQLException(e);} catch (ClassNotFoundException e) {e.printStackTrace();}	
		return true;
    }
	
	 
	public void creaBean() throws SQLException {
    boolean p = false; 
    boolean a = false;
    boolean c = false;
    
    try {
    	
     while(rs.next()) {
     	System.out.println("rs: "+rs.getString("comune")+" "+rs.getString("nome")+" "+rs.getString("percorso_id"));
     	if(c && !rs.getString("comune").equals(this.arrivo)) {
     		p=false;	 //variabili per far prendere dalla query risultante(rs) solo da quando trova la prima fermata delle due all arrivo finale(quindi anche se ripetuto perche è per comuni)
			a=true;
			c=false;
     	}
     	
     	if(rs.getString("comune").equals(this.partenza)) {
     		p = true;
     		a= false;
     	}
     	
     	if(p && !a) {
     	TicketBean t = new TicketBean(rs.getInt("percorso_id"), rs.getString("nome_linea"), rs.getString("comune"), rs.getString("nome"), rs.getString("coordinate"), rs.getString("orario"), rs.getInt("ritardo"));
     	 this.tickets.add(t);
     	System.out.println("creabean(): "+tickets.get(tickets.size()-1).getComune()+" "+tickets.get(tickets.size()-1).getOrario()+" "+tickets.get(tickets.size()-1).getNome()+ " "+tickets.get(tickets.size()-1).getId_percorso());
     	}
     	
     	if(rs.getString("comune").equals(this.arrivo))
     		c = true;
     }
    
    	}catch(SQLException e) {printSQLException(e);}		
	}
	
	public void stampabean() {
		for(int i=0;i<this.tickets.size();i++) {
			System.out.println("stampabean(): "+this.tickets.get(i).getNome()+" "+this.tickets.get(i).getId_percorso());
		}
		System.out.println("-----------------------------------");
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
