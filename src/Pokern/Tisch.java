package Pokern;

import java.util.ArrayList;
//import java.util.ListIterator;

public class Tisch implements Runnable{
    ArrayList<Spieler> mitSpieler = new ArrayList<>();
    //ListIterator<Spieler> listIterator = mitSpieler.listIterator();
    //Oder wir machen hierfür noch einen Konstruktor, der nix erwartet?
    //Oder alles ganz anders? ^^
    //ich glaube nicht

    long startGeld = 5000;

    int[] sBlindList = {100, 200, 400, 500, 1000, 2000, 4000, 5000};
    int sBlindIndex = 0;
    int currentSmallBlind;

    int dealerIndex;
    int smallBlindValue;
    int currentSpieler;
    long pod;

    @Override public void run(){
        //Erstelle Spieler
        Spieler Eberhart = new Spieler("Eberhard");
        Spieler Guenther = new Spieler("Guenther");

        //Erstelle  ein Kartendeck
        KartenDeck deck = new KartenDeck();
        System.out.print("Das Deck wurde hinzugefügt");

        //Füge die Spieler zum Tisch hinzu
        fuegeSpielerHinzu(Eberhart);
        fuegeSpielerHinzu(Guenther);

        //Gebe den Mitspielern das Startgeld
        gibSpielerStartGeld(getStartGeld());

        //Mach dir ein Kartendeck und mische es
        deck.erstelleEinDeck();
        deck.mischeDasDeck();

        //Setze den SmallBlind
        currentSmallBlind = getSmallBlindValue();

        //Setzte den Dealer
        setDealer();

//		//what ever
//		if(tisch.wieVielerSpieler() > 2){
//			//entweder definiert man einfach die Blinds über den Dealer
//			//oder wir machen dafür auch noch Variablen!?
//		}else if (tisch.wieVielerSpieler() == 2){
//			//tisch.nextDealer().geld = tisch.bBlindValue /2;
//			//Die Spieler bekommen Karten
//			tisch.givePlayerCards();
//		}else{
//			//Letzter Spieler hat gewonnen!!!
//
//		}
    }


    //DEALER############################################################################################################
    public void setDealer(){
        dealerIndex = (int) (Math.random() * (mitSpieler.size()-1));
    }
    //dealer------------------------------------------------------------------------------------------------------------
    public void nextDealer(){
        dealerIndex++;
        if(dealerIndex > mitSpieler.size()){
            dealerIndex = 0;
        }
    }
    //dealer------------------------------------------------------------------------------------------------------------
    public Spieler getDealer(){
        return mitSpieler.get(dealerIndex);
    }
    //ENDE_DEALER#######################################################################################################

    //Spieler###########################################################################################################
    public Spieler nextSpieler(){
        return mitSpieler.get(currentSpieler);
    }
    //spieler-----------------------------------------------------------------------------------------------------------
    public void fuegeSpielerHinzu(Spieler s){
        mitSpieler.add(s);
    }
    //spieler-----------------------------------------------------------------------------------------------------------
    public void entferneSpieler(Spieler s){
        mitSpieler.remove(s);
    }
    //spieler-----------------------------------------------------------------------------------------------------------
    public int wieVielerSpieler(){
        return mitSpieler.size();
    }
    //END_SPIELER#######################################################################################################

    //GELD##############################################################################################################
    public void gibSpielerStartGeld(long geld){
        for (Spieler i : mitSpieler){
            i.setGeld(geld);
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    public long getStartGeld(){
        return startGeld;
    }
    //ENDE_GELD#########################################################################################################

    //BLINDS############################################################################################################
    private int getSmallBlindValue(){
        return sBlindList[sBlindIndex];
    }
    //blind-------------------------------------------------------------------------------------------------------------
    private long setNextSmallBlind(){
        if(sBlindIndex++ <= sBlindList.length){
            return sBlindIndex++;
        }else{
            return sBlindIndex;
        }
    }
    //ENDE_BLINDS#######################################################################################################

    //KARTEN############################################################################################################
    public void givePlayerCards (){
        for(Spieler i:mitSpieler) {
            for(int j=0; j < 2; j++)
                i.bekommeKarte(KartenDeck.getKarte());
        }
    }
    //ENDE_KARTEN#######################################################################################################
}