package beans;

public class FermataBean {
	private int id;
	private String nome, coordinate;
	
	FermataBean() {
		setId(-1);
		setNome("");
		setCoordinate("");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}
}
