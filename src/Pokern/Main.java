package Pokern;


public class Main {
	public static void main(String[] args) {
		Spieler Eberhart = new Spieler("Eberhard", 500);
		Spieler Guenther = new Spieler("Guenther", 500);
		Tisch tisch = new Tisch();
		tisch.fuegeSpielerHinzu(Eberhart);
		tisch.fuegeSpielerHinzu(Guenther);
		//whats that?

	}
}