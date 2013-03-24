package ihm;

import javax.swing.*;
import javax.swing.border.LineBorder;

import devintAPI.FenetreAbstraite;
import devintAPI.Preferences;
import fonctionnement.objet.Joueur;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/** Cette classe est un exemple d'interface de jeu.
 *  Elle étend DevintFrame pour avoir un Frame et réagir aux évênements claviers
 *  Implémente ActionListener pour réagir au clic souris sur le bouton.
 *  On surchage la méthode "keyPressed" pour associer une action à la touche F3
 * 
 *  @author helene
 */

public class IHMGameView extends FenetreAbstraite implements ActionListener{
	
	private JButton question;
	private IHMPlateau plateau;
	private int currentButton = -1;

	/*
	 * Constructeur
	 * 
	 */
	
    public IHMGameView(String title) {
    	super(title);
     }
    
   
    /* 
     * Initialisation du frame. 
     */
    protected void init() {
    	setLayout(new GridBagLayout());
    	plateau = new IHMPlateau(this);
    	this.add(plateau);
    	setLayout(new FlowLayout(FlowLayout.RIGHT)); 
    	Joueur joueur= new Joueur("Pierre"); 
    	IHMInfoJoueur infosJoueur1= new IHMInfoJoueur(joueur);
    	this.add(infosJoueur1); 
   }

    
    /*
     * Fonctions permettant de gérer les évènements et les actions 
     */
    
    //Action performed: Défini les actions à effectuer lors de détection des évènements 
    public void actionPerformed(ActionEvent ae){
    	voix.stop();			    	  // toujours stopper la voix avant de parler
    	
     	Object source = ae.getSource();   // on récupère la source de l'évènement
     	
    	if (source.equals(question)) {    // si c'est le bouton "question" on lit la question
    		String text = "les questions sont longues et ont un contenu variable."
    		             +"Il ne faut pas générer un fichier wave mais lire directement les textes";
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
 
    
    //Keyboard event listener: détecte les éléments clavier. 
    @Override
    public void keyPressed(KeyEvent e) {
    	super.keyPressed(e);		     // appel à la méthode mère qui gère les évènements ESC, F1, F3, F4
    	switch(e.getKeyCode()){
    	case KeyEvent.VK_UP:
    		unFocusedButton(currentButton);
    		if((currentButton >10 && currentButton <=20) || (currentButton >40 && currentButton <=48)){
    			currentButton--;
    		}
    		
    		else if(currentButton >=30 && currentButton <39){
    			currentButton++;
    		}
    		
    		else if(currentButton == 25){
    			currentButton=48;
    		}
    		else if(currentButton == 39){
    			currentButton = 0;
    		}
    		else if(currentButton == 40){
    			currentButton = 5;
    		}
    		setFocusedButton(currentButton);
    		break;
    		
    	case KeyEvent.VK_DOWN:
    		unFocusedButton(currentButton);
    		if((currentButton >=10 && currentButton <20) || (currentButton >=40 && currentButton <48)){
    			currentButton++;
    		}
    		
    		else if (currentButton >30 && currentButton <=39) {
    			currentButton--;
    		}

    		else if(currentButton == 48){
    			currentButton=25;
    		}
    		else if(currentButton == 5) {
    			currentButton = 40;
    		}
    		else if(currentButton == 0) {
    			currentButton = 39;
    		}
    		setFocusedButton(currentButton);
    		break;
    		
    	case KeyEvent.VK_LEFT:
    		unFocusedButton(currentButton);
    		if((currentButton >53 && currentButton <=56) || (currentButton>49 && currentButton<53)|| currentButton <=10 && currentButton >0){
    			currentButton--;
    		}
    		
    		else if((currentButton >=20 && currentButton <30)){
    			currentButton++;
    		}
    		
    		else if(currentButton == 53){
    			currentButton=44;
    		}
    		
    		else if(currentButton == 44){
    			currentButton = 52;
    		}
    		
    		else if(currentButton == 15){
    			currentButton=56;
    		}
    		else if(currentButton == 49) {
    			currentButton = 35;
    		}
 
    		setFocusedButton(currentButton);
    		break;
    		
    	case KeyEvent.VK_RIGHT:
    		unFocusedButton(currentButton);
    		if((currentButton >=0 && currentButton <10) || (currentButton >=49 && currentButton <52) || (currentButton >=53 && currentButton <56)){
    			currentButton++;
    		}
    		
    		else if (currentButton <31 && currentButton >20) {
    			currentButton--;
    		}

    		else if(currentButton == 52){
    			currentButton=44;
    		}
    		
    		else if(currentButton ==44){
    			currentButton=53;
    		}
    		else if(currentButton == 56) {
    			currentButton = 15;
    		}
    		else if(currentButton == 35) {
    			currentButton = 49;
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
		if(i>0){
			JButton button = plateau.getCase(i);
			Color oldBackground = button.getBackground();
			button.setBackground(button.getForeground());
			button.setForeground(oldBackground);
		}
	}
	
	 /*
     * Fonctions qui renvoient les sons d'accueil et d'aide.
     * Pour plus d'informations, regarder la classe DevintFrameListener (dans le package devintAPI)
     */
	
	protected  String wavAccueil() {			    // renvoie le fichier wave contenant le message d'accueil
		return "../ressources/sons/accueilJeu.wav";
	}

	protected  String wavRegleJeu() {			    // renvoie le fichier wave contenant la règle du jeu
		return "../ressources/sons/aideF1.wav";
	}
	
	protected  String wavAide() {			        // renvoie le fichier wave contenant la règle du jeu
		return "../ressources/sons/aide.wav";
	}
}
