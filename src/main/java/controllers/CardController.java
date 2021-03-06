package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Timer;

import models.Card;
/**
 * Klasa odpowiadająca za sprawdzanie odwróconych kart
 *
 */
public class CardController implements ActionListener {
	private Vector turnedCards;
	private Timer turnDownTimer;
	public static int pairCount = 0;
	private int pairsUp = 0;
	private final int turnDownDelay=1000;
	
	public CardController() {
		this.turnedCards=new Vector(2);
		this.turnDownTimer=new Timer(this.turnDownDelay, this);
		this.turnDownTimer.setRepeats(false);
	}
	
	public boolean turnUp(Card card) {
		if(this.turnedCards.size()<2) return doAddCard(card);
		
		return false;
	}
	/**
	 * Zlicza pary kart i porównuje odwrócone karty
	 * @param card obiekt klasy Card
	 * @return Zwraca true
	 */
	public boolean doAddCard(Card card) {
		this.turnedCards.add(card);
		if(this.turnedCards.size()==2) {
			Card otherCard = (Card)this.turnedCards.get(0);
			if(otherCard.getNum()==card.getNum()) {
				this.turnedCards.clear();
				pairCount++;
			}
			else this.turnDownTimer.start();
		}
		return true;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		for(int i=0; i<this.turnedCards.size(); i++) {
			Card card = (Card)this.turnedCards.get(i);
			card.turnDown();
		}
		this.turnedCards.clear();
	}
}
