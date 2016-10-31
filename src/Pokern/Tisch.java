package Pokern;
import java.util.ArrayList;

public class Tisch {
	ArrayList<Spieler> mitSpieler = new ArrayList<>();
    //Oder wir machen hierfür noch einen Konstruktor, der nix erwartet?
    //Oder alles ganz anders? ^^
    Spieler dealer = new Spieler("Dealer", 0);
    int dealerIndex = -1;
    int bigBlind;


	public void setzeDealer(){
        for(int i=0; i<Math.random();i++) {
            dealer = mitSpieler.get(i);
        }
//	    if(dealerIndex < 0){
//            dealerIndex = (int) (Math.random() * mitSpieler.size());
//        }else{
//            dealerIndex++;
//            if(dealerIndex > mitSpieler.size()){
//                dealerIndex = 0;
//            }
//        }
    }

    public Spieler whoIsDealer(){
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

    public int getBigBlind(){
        return bigBlind;
    }

    public void setBigBlind(int bigBlind){
        this.bigBlind = bigBlind;
    }

    public void givePlayerCards (){
        for(Spieler i:mitSpieler) {
            for(int j=0; j < 2; j++)
                i.bekommeKarte(KartenDeck.getKarte());
        }
    }
}