package jeu;

import javax.swing.*;
import javax.swing.border.LineBorder;

import devintAPI.FenetreAbstraite;

import java.awt.*;
import java.awt.event.*;

/** Etend DevintFrame pour avoir un Frame et réagir aux évênements claviers
 * Contient un exemple d'affichage d'image proportionnel à la taille de l'écran
 * 
 * @author helene
 *
 */

public class Histoire extends FenetreAbstraite {

    public Histoire(String title) {
    	super(title);
     }

	// renvoie le fichier wave contenant le message d'accueil
	protected  String wavAccueil() {
		return "../ressources/sons/histoire.wav";
	}
	
	// renvoie le fichier wave contenant la règle du jeu
	protected  String wavRegleJeu() {
		return "../ressources/sons/histoire.wav";
	}
	
	// renvoie le fichier wave contenant la règle du jeu
	protected  String wavAide() {
		return "../ressources/sons/aide.wav";
	}
    
    // initialise le frame 
    protected void init() {
    	// FlowLayout : les composants ont leur taille fixée par setPreferredSize
    	// et sont ajoutés de gauche à droite, de haut en bas
    	setLayout(new FlowLayout());

    	// la largeur et la hauteur actuelle de la fenêtre
    	// si vous fixez la taille des éléments graphiques 
    	// faites le en utilisant des valeurs proportionnelles à la taille
    	// de la fenêtre pour que différentes résolutions d'écran soient possibles
    	int largeur = Toolkit.getDefaultToolkit().getScreenSize().width;
    	int hauteur = Toolkit.getDefaultToolkit().getScreenSize().height;

    	// une image, voir http://java.sun.com/docs/books/tutorial/uiswing/components/icon.html
    	ImageIcon icon = new ImageIcon("../ressources/images/bateau.JPG");
    	
    	// on met l'image dans un label
    	JLabel jl = new JLabel(icon,JLabel.CENTER);
    	jl.setAutoscrolls(true);
    	//composant opaque pour voir le fond bleu
    	jl.setOpaque(true); 
    	// (largeur de la fenetre)/2 et (hauteur fenetre)/3
    	jl.setPreferredSize(new Dimension(largeur,hauteur));
    	add(jl);
   }

	@Override
	/** 
	 * pour cette fenêtre, changer la couleur n'a pas de sens, alors la méthode
	 * ne fait rien
	 */
	public void changeColor() {
		// TODO Auto-generated method stub
	}

    
}
