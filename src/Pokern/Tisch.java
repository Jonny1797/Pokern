package Pokern;
import java.util.ArrayList;

public class Tisch {
	ArrayList<Spieler> mitSpieler = new ArrayList<>();
    int dealerIndex = -1;


	public void setzeDealer(){
	    if(dealerIndex < 0){
            dealerIndex = (int) (Math.random() * mitSpieler.size());
        }else{
            dealerIndex++;
            if(dealerIndex > mitSpieler.size()){
                dealerIndex = 0;
            }
        }
    }

	public void fuegeSpielerHinzu(Spieler s){
	    mitSpieler.add(s);
    }

    public void entferneSpieler(Spieler s){
        mitSpieler.remove(s);
    }
}