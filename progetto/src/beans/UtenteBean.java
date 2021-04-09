package beans;
import classi.Cript;

public class UtenteBean {
	
	private int id;
	private String username;
	private String password;
	
	public UtenteBean() {
		this. id=-1;
		this. username="";
		this.password="";
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) throws Exception {
		this.password = password;
	}
	
	public int getID() {
		return this.id;
	}
	
	public void setPasswordCript(String password) throws Exception{
		
		Cript criptante = new Cript();
		String passwordH = criptante.Encrypt(password,"cavolo22");
		this.password = passwordH;
	}
}
