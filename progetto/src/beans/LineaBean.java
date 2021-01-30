package beans;

import java.sql.Date;

public class LineaBean {
	private int id;
	private String tipo_veicolo, nome_compagnia;
	private Date data_inizio, data_fine;
	
	public LineaBean() {
		setId(-1);
		setTipo_veicolo("");
		setNome_compagnia("");
		setData_inizio(null);
		setData_fine(null);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getTipo_veicolo() {
		return tipo_veicolo;
	}

	public void setTipo_veicolo(String tipo_veicolo) {
		this.tipo_veicolo = tipo_veicolo;
	}
	
	public String getNome_compagnia() {
		return nome_compagnia;
	}

	public void setNome_compagnia(String nome_compagnia) {
		this.nome_compagnia = nome_compagnia;
	}

	public Date getData_inizio() {
		return data_inizio;
	}

	public void setData_inizio(Date data_inizio) {
		this.data_inizio = data_inizio;
	}

	public Date getData_fine() {
		return data_fine;
	}

	public void setData_fine(Date data_fine) {
		this.data_fine = data_fine;
	}
}
