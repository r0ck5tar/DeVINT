package ihm;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import devintAPI.FenetreAbstraite;
import devintAPI.Preferences;
import fonctionnement.jeu.Game;

public class IHMGameStart extends FenetreAbstraite implements ActionListener{
	private Game game;
	
	ArrayList<JLabel> labelJoueurs;
	ArrayList<JTextField> champNoms;
	JButton startButton;
	

	public IHMGameStart(String title){
		super(title + "- Saisir les noms des joueurs");
		game = new Game();
	}
	
	@Override
	protected void init() {
		setLayout(new GridBagLayout());
		GridBagConstraints labelsPos = new GridBagConstraints();
		GridBagConstraints champsPos = new GridBagConstraints();
		GridBagConstraints startButtonPos = new GridBagConstraints();
		labelsPos.fill = GridBagConstraints.BOTH;
		champsPos.fill = GridBagConstraints.HORIZONTAL;
		labelsPos.insets = new Insets(20, 0, 0, 20);
		champsPos.insets = new Insets(20, 0, 0, 20);
		
		
		Preferences pref = Preferences.getData();
		Color backgroundColor = pref.getCurrentBackgroundColor();
		Color foregroundColor = pref.getCurrentForegroundColor();
		
		startButton = new JButton("Commencer");
		startButton.setBackground(backgroundColor);
		startButton.setForeground(foregroundColor);
		startButton.setFont(new Font("Tahoma", 1, 56));
		startButton.addActionListener(this);
		
		labelJoueurs = new ArrayList<JLabel>();
		champNoms = new ArrayList<JTextField>();
		
		for(int i=0; i<4; i++) {
			labelJoueurs.add(new JLabel("Joueur " + (i+1) + " : "));
			champNoms.add(new JTextField(10));
			
			labelJoueurs.get(i).setLayout(new BorderLayout());
			labelJoueurs.get(i).setFont(new Font("Tahoma", 1, 56));
			labelJoueurs.get(i).setForeground(foregroundColor);
			
			champNoms.get(i).setFont(new Font("Tahoma", 1, 56));
			//champNoms.get(i).setSize(400, 100);

			labelsPos.gridx = 0;
			labelsPos.gridy = i;
			
			champsPos.gridx = 1;
			champsPos.gridy = i;
			
			this.add(labelJoueurs.get(i), labelsPos);
			this.add(champNoms.get(i), champsPos);			
		}
		
		startButtonPos.insets = new Insets(20, 0, 0, 20);
		startButtonPos.gridwidth = 2;
		startButtonPos.gridx = 0;
		startButtonPos.gridy = 4;
		this.add(startButton, startButtonPos);
	}
	
	 public void actionPerformed(ActionEvent ae){
	    	voix.stop();		
	    	for(JTextField champNom : champNoms) {
	    		if(!champNom.getText().isEmpty()){
	    			game.ajouterJoueur(champNom.getText());
	    		}
	    	}
	    	
	    	new IHMGameView("Ile des Naufragés", game);
	    	this.setVisible(false);
	    }

	@Override
	protected String wavAide() {
		return "../ressources/sons/gameStartAide.wav";
	}

	@Override
	protected String wavAccueil() {
		return "../ressources/sons/gameStart.wav";
	}

	@Override
	protected String wavRegleJeu() {
		return "../ressources/sons/gameStart.wav";
	}

	@Override
	public void changeColor() {
		// TODO Auto-generated method stub
		
	}

}
