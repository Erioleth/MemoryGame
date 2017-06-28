package views;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controllers.CardController;
import models.Card;

public class MemoryGame implements ActionListener {
	protected JFrame mainFrame;
	private Container mainContentPane;
	private ImageIcon cardIcon[]; // 0-7 front karty, 8 back karty
	private boolean autorsOpened=false;
	
	private Timer czasGry;
	JLabel time;
	public MemoryGame() {
		// main window
		this.mainFrame=new JFrame("Memory Game");
		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainFrame.setSize(450, 500); // tutaj rozmiar okna
		this.mainContentPane=this.mainFrame.getContentPane();
		this.mainContentPane.setLayout(new BoxLayout(this.mainContentPane, BoxLayout.PAGE_AXIS));
		
		// pasek navigacji
		JMenuBar menuBar = new JMenuBar();
		this.mainFrame.setJMenuBar(menuBar);
		
		// Game Menu
		JMenu gameMenu=new JMenu("Gra");
		menuBar.add(gameMenu);
		
		// kreator do submenu
		newMenuItem("Nowa gra", gameMenu, this);
		newMenuItem("Wyj�cie", gameMenu, this);
		
		// Menu o oautorze
		JMenu aboutMenu=new JMenu("Pomoc");
		menuBar.add(aboutMenu);
		
		newMenuItem("Autorzy", aboutMenu, this);
		newMenuItem("Instrukcja obs�ugi", aboutMenu, this);
		// load cards
		this.cardIcon=this.loadCardIcons();
		// timer
		time = new JLabel("Time: ");
		time.setVisible(true);
		this.mainContentPane.add(time);
		czasGry = new Timer(time);
		czasGry.start();

	}
	
	private ImageIcon[] loadCardIcons() {
		// TODO Auto-generated method stub
		ImageIcon icon[]=new ImageIcon[9];
		for(int i=0; i<9; i++) {
			String fileName = "images/card"+i+".png";
			ImageIcon imageIcon=new ImageIcon(fileName);
			Image image = imageIcon.getImage();
			Image newImage = image.getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH);
			icon[i]=new ImageIcon(newImage);
		}
		return icon;
	}
	
	public JPanel makeCards() {
		JPanel panel=new JPanel(new GridLayout(4, 4));
		
		// wszystkie karty maja ten sam spod
		ImageIcon backIcon=this.cardIcon[8];
		CardController controller=new CardController();
		
		int[] cardsToAdd=new int[16]; // 4 x 4 daje 16
		for(int i=0; i<8; i++) {
			cardsToAdd[2*i]=i;
			cardsToAdd[2*i+1]=i;
		}
		
		// randomize
		randomizeCardArray(cardsToAdd);
		
		// tworzymy obiekt karty
		for(int i=0; i< cardsToAdd.length; i++) {
			int num=cardsToAdd[i];
			Card newCard=new Card(controller, this.cardIcon[num], backIcon, num);
			panel.add(newCard);
		}
		
		return panel;
	}
	

	private void randomizeCardArray(int[] t) {
		// TODO Auto-generated method stub
		Random randomizer=new Random();
		for(int i=0; i<t.length; i++) {
			int d=randomizer.nextInt(t.length);
			// swap!
			int tmp = t[d];
			t[d] = t[i];
			t[i] = tmp; // !! <3
		}
	}

	private void newMenuItem(String string, JMenu menu, ActionListener listener) {
		// TODO Auto-generated method stub
		JMenuItem newItem =new JMenuItem(string);
		newItem.setActionCommand(string);
		newItem.addActionListener(listener);
		menu.add(newItem);
	}

	public void newGame() {
		// resetujemy gre
		czasGry.zeruj();
		
		this.mainContentPane.removeAll();
		// make a new card set visible
		this.mainContentPane.add(makeCards());
		// draw a timer
		this.mainContentPane.add(time);
		// show main window
		this.mainFrame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Nowa gra")) newGame();
		if(e.getActionCommand().equals("Autorzy")) newAuthors();
		if(e.getActionCommand().equals("Instrukcja obs�ugi")) newHelps();
		if(e.getActionCommand().equals("Wyj�cie")) System.exit(0);
	}

	private void newAuthors() {
		// TODO Auto-generated method stub
		if(this.autorsOpened) return;
		
		JFrame mainFrame=new JFrame("Autorzy:");
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setSize(400, 115); // tutaj rozmiar okna
		mainFrame.setVisible(true);
		
		JLabel text = new JLabel("<html>Autorzy:<br>Monika Molenda<br>Rados�aw Smutek<br>2ID15A</html>", SwingConstants.CENTER);
		text.setForeground(Color.red);
		text.setBackground(Color.white);
		text.setOpaque(true);
		
		mainFrame.add(text);
		
		
//		this.autorsOpened=true;
	}
	private void newHelps() {
		// TODO Auto-generated method stub
		if(this.autorsOpened) return;
		
		JFrame mainFrame=new JFrame("Instrukcja obs�ugi:");
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setSize(400, 300); // tutaj rozmiar okna
		mainFrame.setVisible(true);
		JLabel text = new JLabel("<html>U�ywaj lewego przycisku myszy do odkrywania kart.<br>Znajduj pary i sprawdz sw�j czas! <br>Op�nienie w odwrocie kart spowodowane jest kar�, <br>�e nie trafi�es.<br>Je�li chcesz, mo�esz wypi� shota za ka�dym<br>razem, gdy nie trafisz, aby doda� nieco dramatyzmu.</html>", SwingConstants.CENTER);
		text.setForeground(Color.orange);
		text.setBackground(Color.gray);
		text.setOpaque(true);
		mainFrame.add(text);
	
}
}

