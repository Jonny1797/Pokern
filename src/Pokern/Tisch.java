package Pokern;

import java.util.ArrayList;

public class Tisch implements Runnable{
    ArrayList<Spieler> mitSpieler = new ArrayList<>();

    long startGeld = 5000;

    int[] sBlindList = {100, 150, 200, 400, 500, 1000, 2000, 4000, 5000};
    int sBlindIndex = 0;
    int currentSmallBlind;

    int dealerIndex;
    int smallBlindIndex;
    int bigBlindIndex;
    int smallBlindValue;
    int currentSpieler;
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
        currentSmallBlind = getSmallBlindValue();

        //Setzte den Dealer
        setDealer();

        while(mitSpieler.size() > 1){
            System.out.println("Mehr als 2 Spieler");

            nextDealer();
            setSmallBlindIndex();
            setBigBlindIndex();

            //SmallBlind wird hinzugefügt
            if(mitSpieler.get(smallBlindIndex).wieVielGeld() < getSmallBlindValue()){
                long allInValue = mitSpieler.get(bigBlindIndex).wieVielGeld();
                mitSpieler.get(smallBlindIndex).setAllInZuIstAllIn();                                                   //Spieler wird All-in gesetzt
                mitSpieler.get(smallBlindIndex).verliereGeld(allInValue);                                               //All-in Betrag wird dem Spieler abgezogen
                pod = pod + allInValue;                                                                                 //All-in Betrag wird dem Pod hinzugefügt
                System.out.println(mitSpieler.get(smallBlindIndex).getSpielerName() + " ist All-in mit: " + allInValue);
            }else{
                mitSpieler.get(smallBlindIndex).verliereGeld(getSmallBlindValue());
                pod = pod + getSmallBlindValue();
            }

            //BigBlind wird hinzugefügt
            if(mitSpieler.get(bigBlindIndex).wieVielGeld() < getSmallBlindValue()){
                long allInValue = 2 * mitSpieler.get(bigBlindIndex).wieVielGeld();
                mitSpieler.get(bigBlindIndex).setAllInZuIstAllIn();                                                     //Spieler wird All-in gesetzt
                mitSpieler.get(bigBlindIndex).verliereGeld(allInValue);                                                 //All-in Betrag wird dem Spieler abgezogen
                pod = pod + allInValue;                                                                                 //All-in Betrag wird dem Pod hinzugefügt
                System.out.println(mitSpieler.get(bigBlindIndex).getSpielerName() + " ist All-in mit: " + allInValue);
            }else{
                mitSpieler.get(bigBlindIndex).verliereGeld(2 * getSmallBlindValue());
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
        dealerIndex = (int) (Math.random() * (mitSpieler.size()-1));
    }
    //dealer------------------------------------------------------------------------------------------------------------
    public void nextDealer(){
        dealerIndex++;
        if(dealerIndex >= mitSpieler.size()){
            dealerIndex = 0;
        }
    }
    //dealer------------------------------------------------------------------------------------------------------------
    public Spieler getDealer(){
        return mitSpieler.get(dealerIndex);
    }
    //ENDE_DEALER#######################################################################################################

    public void setSmallBlindIndex(){
        if(mitSpieler.size() > 2){
            smallBlindIndex = dealerIndex + 1;
            if(smallBlindIndex >= mitSpieler.size()){
                smallBlindIndex = smallBlindIndex - mitSpieler.size();
            }
        }else{
            smallBlindIndex = dealerIndex;
        }

    }

    public void setBigBlindIndex(){
       if(mitSpieler.size() > 2){
           bigBlindIndex = dealerIndex + 2;
           if(bigBlindIndex >= mitSpieler.size()){
               bigBlindIndex = bigBlindIndex - mitSpieler.size();
           }
       }else{
           if(dealerIndex == 0){
               bigBlindIndex = 1;
           }else{
               bigBlindIndex = 0;
           }
       }

    }


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
    public void gebeSpielerKarten (){
        for(Spieler i:mitSpieler) {
            for(int j=0; j < 2; j++)
                i.bekommeKarte(KartenDeck.getKarte());
        }
    }
    //ENDE_KARTEN#######################################################################################################
}