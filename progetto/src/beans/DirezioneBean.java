package beans;

public class DirezioneBean {
	private int id, id_linea;
	private String nome;
	private boolean direzione;
	
	DirezioneBean() {
		setId(-1);
		setId_linea(-1);
		setNome("");
		setDirezione(false);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_linea() {
		return id_linea;
	}

	public void setId_linea(int id_linea) {
		this.id_linea = id_linea;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isDirezione() {
		return direzione;
	}

	public void setDirezione(boolean direzione) {
		this.direzione = direzione;
	}
}