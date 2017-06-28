package views;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controllers.CardController;

/**
 * klasa odpowiadaj�ca za licznik czasu
 *
 */
public class Timer extends Thread {
	int time;
	JLabel text;
	public Timer(JLabel t) {
		time = 0;
		text = t;
	}
	public void zeruj()
	{
		time = 0;
	}
	@Override
	public void run() {
		
		while (true) {
			time++;
			text.setText("Time: " + time);
			if(CardController.pairCount>=8){
				JOptionPane.showMessageDialog(null, "Jeste� zwyci�zc�!!");
				break;
				
			}
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}
}