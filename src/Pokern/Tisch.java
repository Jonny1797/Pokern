package Pokern;

import java.util.ArrayList;

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
            System.out.println("Mehr als 2 Spieler");

            nextDealer();
            setSmallBlindSpielerIndex();
            setBigBlindSpielerIndex();

            //SmallBlind wird hinzugefügt
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

            //BigBlind wird hinzugefügt
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
            gebeSpielerKarten();


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
        dealerSpielerIndex++;
        if(dealerSpielerIndex >= mitSpieler.size()){
            dealerSpielerIndex = 0;
        }
    }
    //dealer------------------------------------------------------------------------------------------------------------
    public Spieler getDealer(){
        return mitSpieler.get(dealerSpielerIndex);
    }
    //ENDE_DEALER#######################################################################################################



    //Spieler###########################################################################################################
    public Spieler nextSpieler(){
        currentSpielerIndex++;
        return mitSpieler.get(currentSpielerIndex);
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
    //spieler-----------------------------------------------------------------------------------------------------------
    public void setSmallBlindSpielerIndex(){
        if(mitSpieler.size() > 2){
            smallBlindSpielerIndex = dealerSpielerIndex + 1;
            if(smallBlindSpielerIndex >= mitSpieler.size()){
                smallBlindSpielerIndex = smallBlindSpielerIndex - mitSpieler.size();
            }
        }else{
            smallBlindSpielerIndex = dealerSpielerIndex;
        }
    }
    //spieler-----------------------------------------------------------------------------------------------------------
    public void setBigBlindSpielerIndex(){
        if(mitSpieler.size() > 2){
            bigBlindSpielerIndex = dealerSpielerIndex + 2;
            if(bigBlindSpielerIndex >= mitSpieler.size()){
                bigBlindSpielerIndex = bigBlindSpielerIndex - mitSpieler.size();
            }
        }else{
            if(dealerSpielerIndex == 0){
                bigBlindSpielerIndex = 1;
            }else{
                bigBlindSpielerIndex = 0;
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
    public void updatePod(){
        for(Spieler i:mitSpieler){

        }
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
    public void gebeSpielerKarten (){
        for(Spieler i:mitSpieler) {
            for(int j=0; j < 2; j++)
                i.bekommeKarte(KartenDeck.getKarte());
        }
    }
    //ENDE_KARTEN#######################################################################################################
}