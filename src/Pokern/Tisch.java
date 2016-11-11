package Pokern;

import java.util.ArrayList;
import java.util.Scanner;

public class Tisch implements Runnable{
    ArrayList<Spieler> mitSpieler = new ArrayList<>();

    long startGeld = 5000;

    int[] smallBlindList = {100, 200, 400, 500, 1000, 2000, 4000, 5000};
    int smallBlindListIndex = 0;
    int currentSmallBlindValue;

    int dealerSpielerIndex;
    int smallBlindSpielerIndex;
    int bigBlindSpielerIndex;
    int currentSpielerIndex;
    long pod;

    @Override public void run(){
        //Erstelle Spieler
        Spieler Eberhart = new Spieler("Eberhard");
        Spieler Guenther = new Spieler("Guenther");
        Spieler Max = new Spieler("Max");

        //Erstelle  ein Kartendeck
        KartenDeck deck = new KartenDeck();
        System.out.println("Das Deck wurde hinzugefügt");

        //Füge die Spieler zum Tisch hinzu
        fuegeSpielerHinzu(Eberhart);
        fuegeSpielerHinzu(Guenther);
        fuegeSpielerHinzu(Max);

        //Gebe den Mitspielern das Startgeld
        gibSpielerStartGeld(getStartGeld());

        //Mach dir ein Kartendeck und mische es
        deck.erstelleEinDeck();
        deck.mischeDasDeck();

        //Setze den SmallBlind
        currentSmallBlindValue = getSmallBlindValue();

        //Setzte den Dealer
        setDealer();

        while(mitSpieler.size() > 1){
            //Wer ist Small und BigBlind und wer ist an der Reihe?
            setSmallBlindSpielerIndex();
            setBigBlindSpielerIndex();
            currentSpielerIndex = nextSpieler(bigBlindSpielerIndex);

            //SmallBlindValue wird gelegt
            gibSmallBlind();

            //BigBlindValue wird gelegt
            gibBigBlind();

            //Die Spieler bekommen Karten
            gibSpielerKarten();

            //Wahlmöglichkeiten erste Runde
            while(!getCurrentSpieler().raised || currentSpielerIndex != bigBlindSpielerIndex +1){
                rundeEins();
                nextSpieler(currentSpielerIndex);
            }

        }
        System.out.println("Spieler " + mitSpieler.get(0).getSpielerName() + " gewinnt");
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
        System.out.println("Ende vom Tisch");
    }


    //DEALER############################################################################################################
    public void setDealer(){
        dealerSpielerIndex = (int) (Math.random() * (mitSpieler.size()-1));
    }
    //dealer------------------------------------------------------------------------------------------------------------
    public void nextDealer(){
        dealerSpielerIndex = nextSpieler(dealerSpielerIndex);
    }
    //dealer------------------------------------------------------------------------------------------------------------
    public Spieler getDealer(){
        return mitSpieler.get(dealerSpielerIndex);
    }
    //ENDE_DEALER#######################################################################################################

    //Spieler###########################################################################################################
    public int nextSpieler(int i){
        i  += 1;
        if(i >= mitSpieler.size()){
            i = i - mitSpieler.size();
        }
        return i;
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
    public int wieVieleSpieler(){
        return mitSpieler.size();
    }
    //spieler-----------------------------------------------------------------------------------------------------------
    public void setSmallBlindSpielerIndex(){
        if(mitSpieler.size() > 2){
            smallBlindSpielerIndex = nextSpieler(dealerSpielerIndex);
        }else{
            smallBlindSpielerIndex = dealerSpielerIndex;
        }
    }
    //spieler-----------------------------------------------------------------------------------------------------------
    public void setBigBlindSpielerIndex(){
        if(mitSpieler.size() > 2){
            bigBlindSpielerIndex = nextSpieler(nextSpieler(dealerSpielerIndex));
        }else{
            if(dealerSpielerIndex == 0){
                bigBlindSpielerIndex = 1;
            }else{
                bigBlindSpielerIndex = 0;
            }
        }
    }
    //spieler-----------------------------------------------------------------------------------------------------------
    public Spieler getCurrentSpieler(){
        return mitSpieler.get(currentSpielerIndex);
    }
    //spieler-----------------------------------------------------------------------------------------------------------
    public void rundeEins(){
        boolean ende = false;
        while(!ende) {
            switch (getCurrentSpieler().spielerWahlRundeEins()) {
                case -1:
                    getCurrentSpieler().istDabei = false;
                    ende = true;
                    break;
                case 0:
                    if(mitSpieler.get(currentSpielerIndex-1).raised){
                        break;
                    }
                    ende = true;
                case 1:
                    gibRaiseOrCall(raiseWieViel());
                    ende = true;
                    break;
            }
        }
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
    //------------------------------------------------------------------------------------------------------------------
    public void gibSmallBlind(){
        if(mitSpieler.get(smallBlindSpielerIndex).wieVielGeld() < getSmallBlindValue()){
            long allInValue = mitSpieler.get(smallBlindSpielerIndex).wieVielGeld();
            mitSpieler.get(smallBlindSpielerIndex).setAllInZuIstAllIn();                                                   //Spieler wird All-in gesetzt
            mitSpieler.get(smallBlindSpielerIndex).verliereGeld(allInValue);                                               //All-in Betrag wird dem Spieler abgezogen
            pod = pod + allInValue;                                                                                 //All-in Betrag wird dem Pod hinzugefügt
            System.out.println(mitSpieler.get(smallBlindSpielerIndex).getSpielerName() + " ist All-in mit: " + allInValue);
        }else{
            mitSpieler.get(smallBlindSpielerIndex).verliereGeld(getSmallBlindValue());
            pod = pod + getSmallBlindValue();
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    public void gibBigBlind(){
        if(mitSpieler.get(bigBlindSpielerIndex).wieVielGeld() < getSmallBlindValue()){
            long allInValue = 2 * mitSpieler.get(bigBlindSpielerIndex).wieVielGeld();
            mitSpieler.get(bigBlindSpielerIndex).setAllInZuIstAllIn();                                                     //Spieler wird All-in gesetzt
            mitSpieler.get(bigBlindSpielerIndex).verliereGeld(allInValue);                                                 //All-in Betrag wird dem Spieler abgezogen
            pod = pod + allInValue;                                                                                 //All-in Betrag wird dem Pod hinzugefügt
            System.out.println(mitSpieler.get(bigBlindSpielerIndex).getSpielerName() + " ist All-in mit: " + allInValue);
        }else{
            mitSpieler.get(bigBlindSpielerIndex).verliereGeld(2 * getSmallBlindValue());
            pod = pod + (2 * getSmallBlindValue());
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    public long raiseWieViel(){
        long wert;
        System.out.println("Um wie viel möchtest du raisen?");
        Scanner s = new Scanner(System.in);
        while(!legalRaise(wert=s.nextLong())){
            System.out.println("Der Wert ist leider nicht zulässig. Wählen Sie ein Vielfaches von " + smallBlindList[smallBlindListIndex]*2 + " sein.");
        }
        return wert;
    }
    //------------------------------------------------------------------------------------------------------------------
    public void gibRaiseOrCall(long i){
        this.pod += i;
    }
    //ENDE_GELD#########################################################################################################

    //BLINDS############################################################################################################
    private int getSmallBlindValue(){
        return smallBlindList[smallBlindListIndex];
    }
    //blind-------------------------------------------------------------------------------------------------------------
    private long setNextSmallBlind(){
        if(smallBlindListIndex++ <= smallBlindList.length){
            return smallBlindListIndex++;
        }else{
            return smallBlindListIndex;
        }
    }
    //ENDE_BLINDS#######################################################################################################

    //KARTEN############################################################################################################
    public void gibSpielerKarten(){
        for(Spieler i:mitSpieler) {
            for(int j=0; j < 2; j++)
                i.bekommeKarte(KartenDeck.getKarte());
        }
    }
    //ENDE_KARTEN#######################################################################################################

    //PRÜFE#############################################################################################################
    private boolean legalRaise(long raise){
        return raise > smallBlindList[smallBlindListIndex] && smallBlindList[smallBlindListIndex] % raise == 0;
    }
    //PRÜFE_ENDE########################################################################################################
}