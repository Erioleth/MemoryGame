package models;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JLabel;

import controllers.CardController;

/**
 * 
 * Klasa tworz¹ca obiekty-karty
 *
 */
public class Card extends JLabel implements MouseListener{
	
	Icon faceIcon;
	Icon backIcon;
	boolean faceUp=false;
	int num;
	int iconWidthHalf, iconHeightHalf;;
	boolean mousePressedOnMe=false;
	private CardController controller;
	
	/**
	 * Przypisuje front i ty³ karcie
	 */
	
	public Card (CardController controller, Icon face, Icon back, int num){
		
		super(back);
		this.faceIcon=face;
		this.backIcon=back;
		this.num=num;
		
		this.addMouseListener(this);
		this.iconHeightHalf=back.getIconHeight()/2;
		this.iconWidthHalf=face.getIconWidth()/2;
		this.controller=controller;
	}

	/**
	 * 
	 * @return Zwraca numer karty
	 */
	public int getNum(){return num;}
	
	/**
	 * 
	 * @param x pozycja X
	 * @param y pozycja Y
	 * @return zwraca wartoœæ zale¿n¹ od pozycji myszy nad kart¹
	 */
	private boolean overIcon(int x, int y){
	int distX=Math.abs(x-(this.getWidth()/2));
	int distY=Math.abs(y-(this.getHeight()/2));
	
	
	if(distX>this.iconHeightHalf || distY>this.iconWidthHalf)
		return false;
	
	
	return true;
	}
	
	public void mouseClicked(MouseEvent e) {
		
		if(overIcon(e.getX(),e.getY())) this.turnUp();
	}
	/**
	 * metoda odnoœnie odwrócenia karty
	 */
	public void turnUp() {
		
		if(this.faceUp) return;
		
		this.faceUp=true;
		this.faceUp=this.controller.turnUp(this);
		if (this.faceUp) this.setIcon(this.faceIcon);
	}
	/**
	 * metoda odwracaj¹ca z powrotem 
	 */
	public void turnDown() {
		
		if(!this.faceUp) return;
		this.setIcon(this.backIcon);
		this.faceUp=false;
	}

	public void mousePressed(MouseEvent e) {
		
		if(overIcon(e.getX(), e.getY())) this.mousePressedOnMe=true;
	}

	public void mouseReleased(MouseEvent e) {
		
		if(this.mousePressedOnMe) {
			this.mousePressedOnMe=false;
			this.mouseClicked(e);
		}
	}

	public void mouseEntered(MouseEvent e) {
		
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		this.mousePressedOnMe=false;
	}
}
