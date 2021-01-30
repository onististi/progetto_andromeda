package beans;

import java.sql.Time;

public class FermataPercorsoBean {
	private int id_percorso, id_fermata, ritardo;
	private Time orario;
	private String comune;
	
	public FermataPercorsoBean() {
		setId_percorso(-1);
		setId_fermata(-1);
		setRitardo(0);
		setOrario(null);
		setComune("");
	}

	public int getId_percorso() {
		return id_percorso;
	}

	public void setId_percorso(int id_percorso) {
		this.id_percorso = id_percorso;
	}

	public int getId_fermata() {
		return id_fermata;
	}

	public void setId_fermata(int id_fermata) {
		this.id_fermata = id_fermata;
	}

	public int getRitardo() {
		return ritardo;
	}

	public void setRitardo(int ritardo) {
		this.ritardo = ritardo;
	}

	public Time getOrario() {
		return orario;
	}

	public void setOrario(Time orario) {
		this.orario = orario;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}
}
