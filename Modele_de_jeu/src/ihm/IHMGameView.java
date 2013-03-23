package ihm;

import javax.swing.*;
import javax.swing.border.LineBorder;

import devintAPI.FenetreAbstraite;
import devintAPI.Preferences;

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
	private ArrayList<IHMCase> cases;
	
	private JLabel lblPlateau;
	
    public IHMGameView(String title) {
    	super(title);
     }
    
	protected  String wavAccueil() {			    // renvoie le fichier wave contenant le message d'accueil
		return "../ressources/sons/accueilJeu.wav";
	}

	protected  String wavRegleJeu() {			    // renvoie le fichier wave contenant la règle du jeu
		return "../ressources/sons/aideF1.wav";
	}
	
	protected  String wavAide() {			        // renvoie le fichier wave contenant la règle du jeu
		return "../ressources/sons/aide.wav";
	}

    // initialise le frame 
    protected void init() {
    	setLayout(new BorderLayout());
    	  	
    	// on récupère les couleurs de base dans la classe Preferences 
		Preferences pref = Preferences.getData();
		Color foregroundColor = pref.getCurrentForegroundColor();
		Color backgroundColor = pref.getCurrentBackgroundColor();  	
    	
    	
    	
    	// deuxième label, qui n'est pas géré par les préférences
		String text = "";
       	text = "C'est rigolo les jeux DeViNT";
       	text += "\nIci c'est un JLabel avec un bord gris.\n";
       	text += "Il est placé au centre.";
       	text += "\n\nExemple d'utilisation d'une touche : essayez de taper F5";
    	JTextArea lb2 = new JTextArea (text);
    	lb2.setLineWrap(true);
    	lb2.setEditable(false);
    	lb2.setFont(new Font("Georgia",1,30));
    	// on met un contour gris foncé
       	lb2.setBorder(new LineBorder(Color.GRAY,5));
       	// on met un fond noir
    	lb2.setBackground(Color.BLACK);
    	// le composant doit être opaque pour qu'on voit le fond
       	lb2.setOpaque(true);
    	// on écrit en blanc
       	lb2.setForeground(Color.WHITE);  	
       	// on place ce composant au centre
       	this.add(lb2,BorderLayout.CENTER);
       	

    	
    	// Exemple de création d'un bouton
    	question = new JButton();
    	question.setText("Cliquez sur ce bouton pour écouter la question");
    	question.setBackground(new Color(50,50,255));
    	question.setBorder(new LineBorder(Color.BLACK,10));
     	question.setFont(new Font("Georgia",1,40));
       	question.addActionListener(this);          // c'est l'objet Jeu lui-même qui réagit au clic souris
    	
    	this.add(question,BorderLayout.EAST);      // on met le bouton à droite
   }

    //Action performed: Défini les actions à effectuer lors de détection  des évènements 
    public void actionPerformed(ActionEvent ae){
    	voix.stop();			    	  // toujours stopper la voix avant de parler
    	
     	Object source = ae.getSource();   // on récupère la source de l'évènement
     	
    	if (source.equals(question)) {    // si c'est le bouton "question" on lit la question
    		String text = "les questions sont longues et ont un contenu variable."
    		             +"Il ne faut pas générer un fichier wave mais lire directement les textes";
    		voix.playText(text);          // le contenu des questions est variable donc on les lit avec SI_VOX
    	}	
    	
    	this.requestFocus();  // on redonne le focus au JFrame principal  (après un clic, le focus est sur le bouton) 
    }
 
    
    //Keyboard event listener: détecte les éléments clavier. 
    @Override
    public void keyPressed(KeyEvent e) {
    	super.keyPressed(e);		     // appel à la méthode mère qui gère les évènements ESC, F1, F3, F4
    	if (e.getKeyCode()==KeyEvent.VK_F5){                  // cas particulier pour ce jeu : la touche F5
    	   	voix.playText("Vous venez d'appuyer sur EFFE 5");
    	}
    }
    
	/**
	 * Pour modifier les couleurs de fond et de premier plan de la fenêtre.
	 * Cette fonction est appelée par la fonction "changeColor" de la classe "Preferences"
	 * à chaque fois que l'on presse F3 
	 * 
	 * on change la couleur du texte principal
	 **/
	public  void changeColor() {
    	// on récupère les couleurs de base dans la classe Preferences 
		Preferences pref = Preferences.getData();
		/*
		on change les couleurs des éléments qu'on veut. Exemple:
		lb1.setBackground(pref.getCurrentBackgroundColor());
		lb1.setForeground(pref.getCurrentForegroundColor());
		
		où lb1 est un composant Swing (JComponent) tel que JPanel, JButton, JScrollPane, etc.
		*/
	}

}
