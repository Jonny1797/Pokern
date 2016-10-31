package Pokern;


public class Main {
	public static void main(String[] args) {
		//Erstelle Spieler
		Spieler Eberhart = new Spieler("Eberhard", 1500);
		Spieler Guenther = new Spieler("Guenther", 1500);
		//Erstelle einen Tisch und ein Kartendeck
		Tisch tisch = new Tisch();
		KartenDeck deck = new KartenDeck();
		//Füge die Spieler zum Tisch hinzu
		tisch.fuegeSpielerHinzu(Eberhart);
		tisch.fuegeSpielerHinzu(Guenther);
		//Mach dir ein Kartendeck und mische es
		deck.erstelleEinDeck();
		deck.mischeDasDeck();
		//Setze den BigBlind und Dealer
		tisch.setbBlindValue(200);
		tisch.setDealer();
		if(tisch.howManyPlayers() > 2){
			//entweder definiert man einfach die Blinds über den Dealer
			//oder wir machen dafür auch noch Variablen!?
		}
		tisch.nextDealer().geld = tisch.bBlindValue /2;
		//Die Spieler bekommen Karten
		tisch.givePlayerCards();
	}
}