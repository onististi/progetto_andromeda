package beans;

public class TicketsBean {

	private int id_percorso;
	private String nome_linea;
	private String comune;
	private String nome;
	private float coordinata1;
	private float coordinata2;
	private String orario;
	private int ritardo;
	
	public TicketsBean() {
		this.ritardo = 0;
		this.coordinata1 = 0;
		this.coordinata2= 0;
	}
	
	public void setCoordinate(String coordinata) {
		
	}
	
	public int getId_percorso() {
		return id_percorso;
	}
	public void setId_percorso(int id_percorso) {
		this.id_percorso = id_percorso;
	}
	public String getNome_linea() {
		return nome_linea;
	}
	public void setNome_linea(String nome_linea) {
		this.nome_linea = nome_linea;
	}
	public String getComune() {
		return comune;
	}
	public void setComune(String comune) {
		this.comune = comune;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getCoordinata1() {
		return coordinata1;
	}
	public void setCoordinata1(float coordinata1) {
		this.coordinata1 = coordinata1;
	}
	public float getCoordinata2() {
		return coordinata2;
	}
	public void setCoordinata2(float coordinata2) {
		this.coordinata2 = coordinata2;
	}
	public String getOrario() {
		return orario;
	}
	public void setOrario(String orario) {
		this.orario = orario;
	}
	public int getRitardo() {
		return ritardo;
	}
	public void setRitardo(int ritardo) {
		this.ritardo = ritardo;
	}
}
