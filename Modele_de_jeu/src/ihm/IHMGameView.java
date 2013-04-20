package ihm;

import javax.swing.*;

import Tool.Tool;

import devintAPI.FenetreAbstraite;
import devintAPI.Preferences;
import fonctionnement.environnement.Plateau;
import fonctionnement.jeu.Game;
import fonctionnement.objet.Joueur;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** Cette classe est un exemple d'interface de jeu.
 *  Elle étend DevintFrame pour avoir un Frame et réagir aux évênements claviers
 *  Implémente ActionListener pour réagir au clic souris sur le bouton.
 *  On surchage la méthode "keyPressed" pour associer une action à la touche F3
 * 
 *  @author helene
 */

public class IHMGameView extends FenetreAbstraite implements ActionListener{

	private Game game;
	private Plateau plateauJeu;
	
	private JButton boutonDe;
	private IHMPlateau plateau;
	private ArrayList<Joueur> listJoueurs;
	private Map<Joueur, Color> couleursJoueurs;
	JDialog menuDe;

	JPanel infoJoueurGauche;
	JPanel infoJoueurDroite;
	ArrayList<IHMInfoJoueur> infoJoueurs;


	private int currentButton = 5;
	private int qui;

	/*
	 * Constructeur
	 * 
	 */

	public IHMGameView (String title, Game game) {
		super(title);
		this.game = game;
		listJoueurs = game.getJoueurs();
		game.initialisePlateau();
		plateauJeu = game.getPlateau();
		couleursJoueurs = new HashMap<Joueur, Color>();
		initialize();
		qui = -1;
		game.setQui(qui);
		initializePlayerPositions();
		
		play();
	}


	/* 
	 * Initialisation du frame. 
	 */
	@Override
	protected void init() {
	}

	private void initialize() {

		ArrayList<Color> colorList = new ArrayList<Color>();
		colorList.add(Color.BLUE);
		colorList.add(Color.GREEN);
		colorList.add(Color.MAGENTA);
		colorList.add(Color.ORANGE);
		
		for(int i = 0; i<listJoueurs.size(); i++) {
			couleursJoueurs.put(listJoueurs.get(i), colorList.get(i));
		}
	
		infoJoueurs = new ArrayList<IHMInfoJoueur>();


		for(int i=0; i<listJoueurs.size(); i++) {
			infoJoueurs.add(new IHMInfoJoueur(listJoueurs.get(i), this));
		}

		setLayout(new FlowLayout(FlowLayout.CENTER));
		plateau = new IHMPlateau(plateauJeu, this);

		infoJoueurGauche = new JPanel();
		infoJoueurDroite = new JPanel();

		infoJoueurDroite.setLayout(new BorderLayout());
		infoJoueurDroite.add(infoJoueurs.get(0), BorderLayout.NORTH);
		infoJoueurDroite.add(infoJoueurs.get(1), BorderLayout.SOUTH);

		if(infoJoueurs.size() >2) {
			infoJoueurGauche.setLayout(new BorderLayout());
			infoJoueurGauche.add(infoJoueurs.get(2), BorderLayout.NORTH);
			infoJoueurGauche.add(infoJoueurs.get(3), BorderLayout.SOUTH);
		}



		this.add(infoJoueurDroite);
		this.add(plateau);
		this.add(infoJoueurGauche);
	}


	/*
	 * Fonctions permettant de gérer les évènements et les actions 
	 */

	 //Action performed: Défini les actions à effectuer lors de détection des évènements 
	public void actionPerformed(ActionEvent ae){
		voix.stop();			    	  // toujours stopper la voix avant de parler

		Object source = ae.getSource();   // on récupère la source de l'évènement

		if (source.equals(boutonDe)) {    // si c'est le bouton "question" on lit la question
			
			int de = Tool.lancerDe(listJoueurs.get(qui).getDeplacementMax())+1;
			plateau.afficheChoixDeplacement(listJoueurs.get(qui).getPosition().getChoixCase(de, null));
			menuDe.dispose();
			
			
			String text = Integer.toString(de);
			voix.playText(text);          // le contenu des questions est variable donc on les lit avec SI_VOX
		}

		if(source.getClass().getSimpleName().equals("IHMCase")) {
			for(int i=0; i<57; i++) {
				if (plateau.getCase(i).equals(source)){
					unFocusedButton(currentButton);
					currentButton = i;
					setFocusedButton(currentButton);
					break;
				}
			}
		}

		this.requestFocus();  // on redonne le focus au JFrame principal  (après un clic, le focus est sur le bouton) 
	}
	
	public void play() {
		qui++;
		if(qui>3) {qui = 0;}
		deplacement(qui);
		
	}
	
	
	public void initializePlayerPositions() {
		ArrayList<Integer> positionJouers = new ArrayList<Integer>();
		positionJouers.add(5);
		positionJouers.add(35);
		positionJouers.add(16);
		positionJouers.add(26);
		for(int i = 0; i<listJoueurs.size(); i++) {
			listJoueurs.get(i).setPosition(game.getPlateau().getCase(positionJouers.get(i)));
		}
		
		setCaseJoueur();
	}
	
	public void deplacement(int qui) {
		unFocusedButton(currentButton);
		currentButton = plateau.getIndexOfCase(listJoueurs.get(qui).getPosition());
		setFocusedButton(currentButton);
		lancerDe(qui);
	}

	public void lancerDe (int qui) {
		menuDe = new JDialog(this, "lancer le dé");
		menuDe.setLayout(new BorderLayout());
		menuDe.setSize(200, 100);
		switch(qui){
		case 0:
			menuDe.setLocation(370, 20);
			break;
		case 1:
			menuDe.setLocation(770, 20);
			break;
		case 2:
			menuDe.setLocation(370, 20);
			break;
		case 3:
			menuDe.setLocation(370, 20);
			break;
		}
		boutonDe = new JButton ("lancer le dé");
		boutonDe.setVisible(true);
		boutonDe.addActionListener(this);
		menuDe.add(boutonDe);
		menuDe.setVisible(true);
	}
	
	public void setCaseJoueur() {
		for(Joueur joueur : listJoueurs) {
			plateau.getCaseAtIndex(joueur.getPosition()).setBackground(couleursJoueurs.get(joueur));
			plateau.getCaseAtIndex(joueur.getPosition()).setForeground(Color.BLACK);
		}
	}


	//Keyboard event listener: détecte les éléments clavier. 
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);		     // appel à la méthode mère qui gère les évènements ESC, F1, F3, F4
		switch(e.getKeyCode()){
		
		case KeyEvent.VK_ENTER:
		case KeyEvent.VK_SPACE:
			//change position to the selected case if it's accessible.
			if(plateau.getIndiceCaseAccessibles().contains(currentButton)) {
				plateau.getCaseAtIndex(listJoueurs.get(qui).getPosition()).setCouleurCaseNormale();
				listJoueurs.get(qui).setPosition(plateauJeu.getCase(currentButton));
				setCaseJoueur();
				plateau.masquerChoixDeplacement(currentButton);
				play();
			}
			break;
		case KeyEvent.VK_UP:
			unFocusedButton(currentButton);
			if((currentButton >=22 && currentButton <30) || (currentButton >=40 && currentButton <48)
					|| (currentButton >=31 && currentButton <39)){
				currentButton++;
			}

			else if(currentButton == 0){
				currentButton=22;
			}
			else if(currentButton == 10){
				currentButton = 31;
			}
			else if(currentButton == 5){
				currentButton = 40;
			}
			else if(currentButton == 30){
				currentButton = 11;
			}
			else if(currentButton == 48){
				currentButton = 16;
			}
			else if(currentButton == 39){
				currentButton = 21;
			}
			setFocusedButton(currentButton);
			break;

		case KeyEvent.VK_DOWN:
			unFocusedButton(currentButton);
			if((currentButton >31 && currentButton <=39) || (currentButton >40 && currentButton <=48)
					|| (currentButton >22 && currentButton <=30)){
				currentButton--;
			}
			else if(currentButton == 31){
				currentButton=10;
			}
			else if(currentButton == 40) {
				currentButton = 5;
			}
			else if(currentButton == 22) {
				currentButton = 0;
			}
			else if(currentButton == 11) {
				currentButton = 30;
			}
			else if(currentButton == 16) {
				currentButton = 48;
			}
			else if(currentButton == 21) {
				currentButton = 39;
			}

			setFocusedButton(currentButton);
			break;

		case KeyEvent.VK_LEFT:
			unFocusedButton(currentButton);
			if((currentButton >11 && currentButton <=21) || (currentButton>0 && currentButton<=10)
					|| (currentButton >49 && currentButton<=52) || (currentButton>53 && currentButton<=56)){
				currentButton--;
			}
			else if(currentButton == 53){
				currentButton=44;
			}
			else if(currentButton == 44){
				currentButton = 52;
			}
			else if(currentButton == 35){
				currentButton=56;
			}
			else if(currentButton == 49) {
				currentButton = 26;
			}

			setFocusedButton(currentButton);
			break;

		case KeyEvent.VK_RIGHT:
			unFocusedButton(currentButton);
			if((currentButton >=0 && currentButton <10) || (currentButton >=11 && currentButton <21) 
					|| (currentButton >=49 && currentButton <52) || (currentButton >=53 && currentButton <56)){
				currentButton++;
			}
			else if(currentButton == 26){
				currentButton=49;
			}
			else if(currentButton ==44){
				currentButton=53;
			}
			else if(currentButton == 56) {
				currentButton = 35;
			}
			else if(currentButton == 52) {
				currentButton = 44;
			}

			setFocusedButton(currentButton);
			break; 
		case KeyEvent.VK_F5:
			currentButton = 20;
		} 	
	}

	/**
	 * Pour modifier les couleurs de fond et de premier plan de la fenêtre.
	 * Cette fonction est appelée par la fonction "changeColor" de la classe "Preferences"
	 * à chaque fois que l'on presse F3 
	 * 
	 **/
	@Override
	public  void changeColor() {
		// on récupère les couleurs de base dans la classe Preferences 
		Preferences pref = Preferences.getData();

		//on change les couleurs de tous les éléments

		for(int i=0; i<57; i++) {
			JButton button = plateau.getCase(i);
			button.setBackground(pref.getCurrentBackgroundColor());
			button.setForeground(pref.getCurrentForegroundColor());
		}	

		for(int i=0; i<4; i++) {
			JButton button = plateau.getCabane(i);
			button.setBackground(pref.getCurrentBackgroundColor());
			button.setForeground(pref.getCurrentForegroundColor());
		}
		
		setFocusedButton(currentButton);
		setCaseJoueur();
	}

	// mettre le focus sur un bouton
	private void setFocusedButton(int i) {
		JButton button = plateau.getCase(i);
		voix.playShortText(button.getText());
		Color oldBackground = button.getBackground();
		button.setBackground(button.getForeground());
		button.setForeground(oldBackground);
	}

	// enlever le focus d'un bouton
	private void unFocusedButton(int i) {

		if(i>=0){
			JButton button = plateau.getCase(i);
			Color oldBackground = button.getBackground();
			button.setBackground(button.getForeground());
			button.setForeground(oldBackground);
			setCaseJoueur();
		}
	}

	/*
	 * Fonctions qui renvoient les sons d'accueil et d'aide.
	 * Pour plus d'informations, regarder la classe DevintFrameListener (dans le package devintAPI)
	 */

	@Override
	protected  String wavAccueil() {			    // renvoie le fichier wave contenant le message d'accueil
		return "../ressources/sons/accueilJeu.wav";
	}

	@Override
	protected  String wavRegleJeu() {			    // renvoie le fichier wave contenant la règle du jeu
		return "../ressources/sons/aideF1.wav";
	}

	@Override
	protected  String wavAide() {			        // renvoie le fichier wave contenant la règle du jeu
		return "../ressources/sons/aide.wav";
	}
}
