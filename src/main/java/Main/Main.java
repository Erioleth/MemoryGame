package Main;

import views.MemoryGame;
/**
 * @author Monika Molenda, Radosław Smutek
 * 
 */
public class Main {
	/**
	 * Funkcja uruchamiająca program
	 */
	public static void main(String[] argv) {
		MemoryGame instance = new MemoryGame();
		instance.newGame();
	}
}
