package beans;

public class FermataDirezioneBean {
	private int id_direzione, id_fermata;
	
	public FermataDirezioneBean() {
		setId_direzione(-1);
		setId_fermata(-1);
	}

	public int getId_direzione() {
		return id_direzione;
	}

	public void setId_direzione(int id_direzione) {
		this.id_direzione = id_direzione;
	}

	public int getId_fermata() {
		return id_fermata;
	}

	public void setId_fermata(int id_fermata) {
		this.id_fermata = id_fermata;
	}
}
