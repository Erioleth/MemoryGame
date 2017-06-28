package views;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controllers.CardController;

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
				JOptionPane.showMessageDialog(null, "Jesteœ zwyciêzc¹!!");
				
				
			}
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}