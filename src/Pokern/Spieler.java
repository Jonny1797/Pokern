package Pokern;
import java.util.ArrayList;

public class Spieler {
	String name;
	long geld;
	long pod;
	boolean isAllIn = false;
	boolean isInDerRunde = true;
	ArrayList<Karte> handKarten = new ArrayList<>();

	public Spieler(String Name/*, long Geld*/){
		this.name = Name;
		//this.geld = Geld;
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

	//IN_DER_RUNDE######################################################################################################
	public void setIsInDerRundeZuFold(){
		isInDerRunde = false
	}
	//isinderrunde------------------------------------------------------------------------------------------------------
	public void setIsInDerRundeZuIstDabei(){
		isInDerRunde = true;
	}
	//isinderrunde------------------------------------------------------------------------------------------------------
	public boolean getIsInDerRunde(){
		return isInDerRunde;
	}
	//ENDE_IN_DER_RUNDE#################################################################################################
}
