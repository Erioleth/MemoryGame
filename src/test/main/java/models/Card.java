package models;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JLabel;

import controllers.CardController;

public class Card extends JLabel implements MouseListener{
	
	Icon faceIcon;
	Icon backIcon;
	boolean faceUp=false;
	int num;
	int iconWidthHalf, iconHeightHalf;
	boolean mousePressedOnMe=false;
	private CardController controller;
	
	public Card (CardController controller, Icon face, Icon back, int num){
		
		super(back);
		this.faceIcon=face;
		this.backIcon=back;
		this.num=num;
		//catch muse clicks
		this.addMouseListener(this);
		this.iconHeightHalf=back.getIconHeight()/2;
		this.iconWidthHalf=face.getIconWidth()/2;
		this.controller=controller;
	}

	
	public int getNum(){return num;}
	
	
	private boolean overIcon(int x, int y){
	int distX=Math.abs(x-(this.getWidth()/2));
	int distY=Math.abs(y-(this.getHeight()/2));
	
	// outside
	if(distX>this.iconHeightHalf || distY>this.iconWidthHalf)
		return false;
	
	// inside
	return true;
	}
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(overIcon(e.getX(),e.getY())) this.turnUp();
	}

	public void turnUp() {
		// TODO Auto-generated method stub
		if(this.faceUp) return;
		
		this.faceUp=true;
		this.faceUp=this.controller.turnUp(this);
		if (this.faceUp) this.setIcon(this.faceIcon);
	}

	public void turnDown() {
		// TODO Auto-generated method stub
		if(!this.faceUp) return;
		this.setIcon(this.backIcon);
		this.faceUp=false;
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(overIcon(e.getX(), e.getY())) this.mousePressedOnMe=true;
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(this.mousePressedOnMe) {
			this.mousePressedOnMe=false;
			this.mouseClicked(e);
		}
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		this.mousePressedOnMe=false;
	}
}
