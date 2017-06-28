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
	/**
	 * Konstruktor, który tworzy okno gry
	 */
	public MemoryGame() {
		
		this.mainFrame=new JFrame("Memory Game");
		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainFrame.setSize(450, 500); // tutaj rozmiar okna
		this.mainContentPane=this.mainFrame.getContentPane();
		this.mainContentPane.setLayout(new BoxLayout(this.mainContentPane, BoxLayout.PAGE_AXIS));
		
		
		JMenuBar menuBar = new JMenuBar();
		this.mainFrame.setJMenuBar(menuBar);
		
		
		JMenu gameMenu=new JMenu("Gra");
		menuBar.add(gameMenu);
		
		
		newMenuItem("Nowa gra", gameMenu, this);
		newMenuItem("Wyjœcie", gameMenu, this);
		
		
		JMenu aboutMenu=new JMenu("Pomoc");
		menuBar.add(aboutMenu);
		
		newMenuItem("Autorzy", aboutMenu, this);
		newMenuItem("Instrukcja obs³ugi", aboutMenu, this);
		
		this.cardIcon=this.loadCardIcons();
		
		time = new JLabel();
		time.setVisible(true);
		this.mainContentPane.add(time);
		czasGry = new Timer(time);
		czasGry.start();

	}
	/**
	 * Wczytuje ikony
	 * @return tablica ikon
	 */
	private ImageIcon[] loadCardIcons() {
		
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
	
	/**
	 * Tworzy tablicê kart
	 * @return panel z kartami
	 */
	public JPanel makeCards() {
		JPanel panel=new JPanel(new GridLayout(4, 4));
		
		
		ImageIcon backIcon=this.cardIcon[8];
		CardController controller=new CardController();
		
		int[] cardsToAdd=new int[16];
		for(int i=0; i<8; i++) {
			cardsToAdd[2*i]=i;
			cardsToAdd[2*i+1]=i;
		}
		
		
		randomizeCardArray(cardsToAdd);
		
		
		for(int i=0; i< cardsToAdd.length; i++) {
			int num=cardsToAdd[i];
			Card newCard=new Card(controller, this.cardIcon[num], backIcon, num);
			panel.add(newCard);
		}
		
		return panel;
	}
	
	/**
	 * Miesza tablicê kart
	 * @param wymieszana tablica kart
	 */
	private void randomizeCardArray(int[] t) {
		
		Random randomizer=new Random();
		for(int i=0; i<t.length; i++) {
			int d=randomizer.nextInt(t.length);
			
			int tmp = t[d];
			t[d] = t[i];
			t[i] = tmp;
		}
	}

	private void newMenuItem(String string, JMenu menu, ActionListener listener) {
		
		JMenuItem newItem =new JMenuItem(string);
		newItem.setActionCommand(string);
		newItem.addActionListener(listener);
		menu.add(newItem);
	}
	
	/**
	 * Uruchamia now¹ grê
	 */
	
	public void newGame() {
		
		czasGry.zeruj();
		this.mainContentPane.removeAll();
		
		this.mainContentPane.add(makeCards());
		
		this.mainContentPane.add(time);
		
		this.mainFrame.setVisible(true);
	}
	
	/**
	 * Obs³uguje menu
	 */
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Nowa gra")) newGame();
		if(e.getActionCommand().equals("Autorzy")) newAuthors();
		if(e.getActionCommand().equals("Instrukcja obs³ugi")) newHelps();
		if(e.getActionCommand().equals("Wyjœcie")) System.exit(0);
	}

	/**
	 * Wyœwietla okno z autorami
	 */
	private void newAuthors() {
		
		if(this.autorsOpened) return;
		
		JFrame mainFrame=new JFrame("Autorzy:");
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setSize(400, 115);
		mainFrame.setVisible(true);
		
		JLabel text = new JLabel("<html>Autorzy:<br>Monika Molenda<br>Rados³aw Smutek<br>2ID15A</html>", SwingConstants.CENTER);
		text.setForeground(Color.red);
		text.setBackground(Color.white);
		text.setOpaque(true);
		
		mainFrame.add(text);
		
		
		/**
		 * WYœwietla okno z instrukcj¹ obs³ugi
		 */
	}
	private void newHelps() {
		
		if(this.autorsOpened) return;
		
		JFrame mainFrame=new JFrame("Instrukcja obs³ugi:");
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setSize(400, 300); 
		mainFrame.setVisible(true);
		JLabel text = new JLabel("<html>U¿ywaj lewego przycisku myszy do odkrywania kart.<br>Znajduj pary i sprawdz swój czas! <br>Opó¿nienie w odwrocie kart spowodowane jest kar¹, <br>¿e nie trafi³es.<br>Jeœli chcesz, mo¿esz wypiæ shota za ka¿dym<br>razem, gdy nie trafisz, aby dodaæ nieco dramatyzmu.</html>", SwingConstants.CENTER);
		text.setForeground(Color.orange);
		text.setBackground(Color.gray);
		text.setOpaque(true);
		mainFrame.add(text);
	
}
}

