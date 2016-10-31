package Pokern;

import java.util.ArrayList;
import java.util.ListIterator;

public class Tisch {
	ArrayList<Spieler> mitSpieler = new ArrayList<>();
    ListIterator<Spieler> listIterator = mitSpieler.listIterator();
    //Oder wir machen hierfür noch einen Konstruktor, der nix erwartet?
    //Oder alles ganz anders? ^^
    Spieler dealer = new Spieler();
    Spieler bigBlind = new Spieler();
    Spieler smallBlind = new Spieler();
//  int dealerIndex = -1;
    int bBlindValue;
    long pod;


	public void setDealer() {
        //Man muss auch noch abprüfuen, ob es überhaupt ein nächstes Element gibt
        dealer = mitSpieler.get((int) Math.random() % mitSpieler.size());
        smallBlind = listIterator.next();
        //nicht so sicher hier
        bigBlind = listIterator.next();
    }

    public void nextDealer(){
        dealer = listIterator.next();
    }

    public Spieler getDealer(){
        return dealer;
    }

	public void fuegeSpielerHinzu(Spieler s){
	    mitSpieler.add(s);
    }

    public void entferneSpieler(Spieler s){
        mitSpieler.remove(s);
    }

    public int howManyPlayers(){
        return mitSpieler.size();
    }

    public int getbBlindValue(){
        return bBlindValue;
    }

    public void setbBlindValue(int bBlindValue){
        this.bBlindValue = bBlindValue;
    }

    public void givePlayerCards (){
        for(Spieler i:mitSpieler) {
            for(int j=0; j < 2; j++)
                i.bekommeKarte(KartenDeck.getKarte());
        }
    }
}