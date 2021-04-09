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
	public int id_direzione=9991;
	private String sql;
	private int andata =0;
	public ResultSet rs;
	public String partenza;
	public String arrivo;
	public String ora;
	public String giorno;	
	public ArrayList<TicketBean> tickets;
	public HashMap<Integer,ArrayList<TicketBean>> spezzato;
	
	public TicketClass(String p, String a, String o, String g) {
		this.partenza = p;
		this.arrivo = a;
		this.ora = o;
		System.out.println(o);
		this.giorno = g;
		this.tickets = new ArrayList<TicketBean>();
		this.spezzato = new HashMap<Integer,ArrayList<TicketBean>>();
	}
	
	public int getAndata() {return this.andata;}
	public void setAndata(int n) {this.andata=n;}
	public void setSql(boolean a) {
		
		if(!a)
			this.sql = "SELECT direzione.id AS id_direzione, percorso.id AS percorso_id, direzione.nome as nome_Linea, F.comune, F.nome, F.coordinate,fermata_percorso.orario, fermata_percorso.ritardo, linea.nome_compagnia FROM percorso "
					+ "INNER JOIN direzione on direzione.id = percorso.id_direzione "
					+ "INNER JOIN linea on linea.id = direzione.id_linea "
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
			this.sql = "SELECT direzione.id AS id_direzione, percorso.id AS percorso_id, direzione.nome as nome_Linea, F.comune, F.nome, F.coordinate,fermata_percorso.orario, fermata_percorso.ritardo, linea.nome_compagnia FROM percorso "
					+ "INNER JOIN direzione on direzione.id = percorso.id_direzione "
					+ "INNER JOIN linea on linea.id = direzione.id_linea "
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
	
	public boolean query(boolean r) throws ClassNotFoundException {
		try {
			System.out.println(this.partenza+this.arrivo);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/androme", "aro", "cavolo22");
		   Statement stmt = con.createStatement();
		   this.rs = stmt.executeQuery(this.sql);  
		  
		   if(!this.rs.next())
			   return false;
		   else 						 //qui ce la prima fermata
			   rs.previous();
		   
		   return true;
		} catch(SQLException e) {printSQLException(e);} catch (ClassNotFoundException e) {e.printStackTrace();}	
		return true;
    }
	
	public HashMap<Integer,ArrayList<TicketBean>> creaTickets() throws SQLException, ClassNotFoundException {
		
		setSql(false);	//false sta per andata= 0
		if(query(false)) {  
	    this.andata = controlla_andata(this.rs);
	    //stampaRS();
	    this.rs.beforeFirst();
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
	 	
		}else 
			return this.spezzato;
	}
	
	
	public void dividiTickets() {
		for(int i=0;i<this.tickets.size();i++) {
			
			if(this.tickets.get(i).getId_percorso()!= this.tickets.get(i).getId_percorso()) { //nuovo biglietto
				System.out.println("entrato nel primo dividi ticket "+this.tickets.get(i).getId_percorso()+" "+tickets.get(i).getNome()+" "+tickets.get(i).getComune());
				this.spezzato.put(this.tickets.get(i).getId_percorso(),new ArrayList<TicketBean>(Arrays.asList(this.tickets.get(i))));
			}
			else if(this.spezzato.containsKey(this.tickets.get(i).getId_percorso())){ //biglietto contenuto
				
				ArrayList<TicketBean> campo = spezzato.get(this.tickets.get(i).getId_percorso());
				campo.add(this.tickets.get(i));
				//System.out.println("r "+campo.get(campo.size()-1).getComune());
				this.spezzato.put(this.tickets.get(i).getId_percorso(),campo);
				
			}else { //primo biglietto
				this.spezzato.put(this.tickets.get(i).getId_percorso(),new ArrayList<TicketBean>(Arrays.asList(this.tickets.get(i))));
				System.out.println("entrato nel terzo dividi ticket "+this.tickets.get(i).getId_percorso()+" "+tickets.get(i).getNome()+" "+tickets.get(i).getComune());
			}
		}
		System.out.println("---------------------------");
	}
	
	
	public int controlla_andata(ResultSet rs) throws SQLException {
        while(rs.next()) {  
        	System.out.println("eco: "+rs.getString("comune"));
        	if(rs.getString("comune").equals(this.partenza)) 
        		return 0;
        	else if(rs.getString("comune").equals(this.arrivo))
        		return 1;
        }
		return 0;
	}
	
	 
	public void creaBean() throws SQLException { //
    boolean p = false; 
    boolean a = false;
    
    try {
    	
    	rs.previous(); //bug fixato trovato qui (saltava la prima fermata quindi anche il primo biglietto) ma non si sa cosa gli faceva fare il next() prima
    	
     while(rs.next()) {
     	System.out.println("rs: "+rs.getString("comune")+" "+rs.getString("nome")+" "+rs.getString("orario")+" "+rs.getString("percorso_id"));
     	
     	if(a && rs.getString("comune").equals(this.arrivo))   //per fare prendere dalla prima fermata alla ultima fermata conn comune = Partenza || Arrivo
     		a=false;
     	
     	if(rs.getString("comune").equals(this.partenza)) {
     		p = true;
     		a= false;
     	}
     	
     	if(p && !a) {
     	TicketBean t = new TicketBean(rs.getInt("percorso_id"), rs.getString("nome_linea"), rs.getString("comune"), rs.getString("nome"), rs.getString("coordinate"), rs.getString("orario"), rs.getInt("ritardo"),rs.getString("nome_compagnia"));
     	 this.tickets.add(t);
     	//System.out.println("creabean(): "+tickets.get(tickets.size()-1).getComune()+" "+tickets.get(tickets.size()-1).getOrario()+" "+tickets.get(tickets.size()-1).getNome()+ " "+tickets.get(tickets.size()-1).getId_percorso());
     	}
     	
     	if(rs.getString("comune").equals(this.arrivo))
     		a = true;
     }
    	}catch(SQLException e) {printSQLException(e);}		
	}
	
	public void stampaRS() throws SQLException {
		while(this.rs.next()) {
			try {
				System.out.println("ll: "+rs.getString("comune"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	
	public void stampabean() {
		System.out.println("-----------------------------------");
		for(int i=0;i<this.tickets.size();i++) {
			System.out.println("stampabean(): "+this.tickets.get(i).getComune()+" "+this.tickets.get(i).getNome()+" "+this.tickets.get(i).getId_percorso());
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
