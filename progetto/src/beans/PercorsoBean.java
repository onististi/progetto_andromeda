package beans;

public class PercorsoBean {
	private int id, id_direzione;
	private String note;
	
	public PercorsoBean() {
		setId(-1);
		setId_direzione(-1);
		setNote("");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_direzione() {
		return id_direzione;
	}

	public void setId_direzione(int id_direzione) {
		this.id_direzione = id_direzione;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
