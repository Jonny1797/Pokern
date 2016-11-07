package Pokern;
import java.util.ArrayList;
import java.util.Scanner;

public class Spieler{
	String name;
	long geld;
	boolean isAllIn = false;
	ArrayList<Karte> handKarten = new ArrayList<>();

	public Spieler(String Name/*, long geld*/){
		this.name = Name;
		//this.geld = geld;
	}
	public Spieler(){}

	//KARTEN############################################################################################################
	public void bekommeKarte(Karte karte){
		handKarten.add(karte);
	}
	//ENDE_KARTEN#######################################################################################################

	//SPIELER###########################################################################################################
	public String getSpielerName(){
		return name;
	}
	//spieler--------------------------------------------------------------------------------------------------------------
	public long spielerAktionRundeEins(){
		while(true) {
			System.out.println("Wählen Sie zwischen folgenden Aktionen:");
			System.out.println("1: Call");
			System.out.println("2: Fold");
			System.out.println("3: Raise");
			Scanner s = new Scanner(System.in);
			int i = s.nextInt();
			switch (i) {
				case 1:
					return 0;
				case 2:
					return -1;
				case 3:
					System.out.println("Um wieviel möchtest du erhöhen?");
					return s.nextLong();
				default:
					System.out.println("Ungültige Eingabe.");
					break;
			}
		}
	}
	//ENDE_SPIELER######################################################################################################

	//GELD##############################################################################################################
	public void setGeld(long geld){
		this.geld = geld;
	}
	//geld--------------------------------------------------------------------------------------------------------------
	public void bekommeGeld(long Gewinn){
		geld = geld + Gewinn;
	}
	//geld--------------------------------------------------------------------------------------------------------------
	public void verliereGeld(long Verlust){
		geld = geld - Verlust;
	}
	//geld--------------------------------------------------------------------------------------------------------------
	public long wieVielGeld(){
		return geld;
	}
	//ENDE_GELD#########################################################################################################

	//ALLIN#############################################################################################################
	public void setAllInZuIstAllIn(){
		isAllIn = true;
	}
	//allin-------------------------------------------------------------------------------------------------------------
	public  void setAllInZuIstNichtAllIn(){
		isAllIn = false;
	}
	//allin-------------------------------------------------------------------------------------------------------------
	public boolean getIsAllIn(){
		return isAllIn;
	}
	//ENDE_ALLIN########################################################################################################
}