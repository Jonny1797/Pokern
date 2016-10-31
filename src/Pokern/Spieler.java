package Pokern;
import java.util.ArrayList;

public class Spieler {
	String name;
	long geld;
	ArrayList<Karte> handKarten = new ArrayList<>();
	

	public void bekommeKarte(Karte karte){
		handKarten.add(karte);
	}

	public Spieler(String Name, long Geld){
		this.name = Name;
		this.geld = Geld;
	}
	
	public void bekommeGeld(long Gewinn){
		geld = geld + Gewinn;
	}
	
	public void verliereGeld(long Verlust){
		geld = geld - Verlust;
	}
	
	public long wieVielGeld(){
		return geld;
	}
	
	public String nameVomSpieler(){
		return name;
	}
}
