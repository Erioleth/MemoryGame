package Main;

import views.MemoryGame;
/**
 * @author Monika Molenda, Rados�aw Smutek
 * 
 */
public class Main {
	/**
	 * Funkcja uruchamiaj�ca program
	 */
	public static void main(String[] argv) {
		MemoryGame instance = new MemoryGame();
		instance.newGame();
	}
}
