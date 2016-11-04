package Pokern;

//DU MONK DU GEORG blabla

public class Main {
	public static void main(String[] args) {
		//Erstelle Spieler
		Spieler Eberhart = new Spieler("Eberhard", 500);
		Spieler Guenther = new Spieler("Guenther", 500);
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
		tisch.setBigBlind(200);
		tisch.setzeDealer();
		if(tisch.howManyPlayers()<= 2){

		}
		//Die Spieler bekommen Karten
		tisch.givePlayerCards();
		tisch.givePlayerCards();
	}
}