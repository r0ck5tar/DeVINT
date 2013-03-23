package ihm;

import javax.swing.*;
import javax.swing.border.LineBorder;

import devintAPI.FenetreAbstraite;
import devintAPI.Preferences;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/** Cette classe est un exemple d'interface de jeu.
 *  Elle �tend DevintFrame pour avoir un Frame et r�agir aux �v�nements claviers
 *  Impl�mente ActionListener pour r�agir au clic souris sur le bouton.
 *  On surchage la m�thode "keyPressed" pour associer une action � la touche F3
 * 
 *  @author helene
 */

public class IHMGameView extends FenetreAbstraite implements ActionListener{
	
	private JButton question;
	private ArrayList<IHMCase> cases;
	
	/*
	 * Constructeur
	 */
    public IHMGameView(String title) {
    	super(title);
     }
    
   
    /* 
     * Initialisation du frame. 
     */
    protected void init() {
    	setLayout(new BorderLayout());  	
   }

    
    /*
     * Fonctions permettant de g�rer les �v�nements et les actions 
     */
    
    //Action performed: D�fini les actions � effectuer lors de d�tection des �v�nements 
    public void actionPerformed(ActionEvent ae){
    	voix.stop();			    	  // toujours stopper la voix avant de parler
    	
     	Object source = ae.getSource();   // on r�cup�re la source de l'�v�nement
     	
    	if (source.equals(question)) {    // si c'est le bouton "question" on lit la question
    		String text = "les questions sont longues et ont un contenu variable."
    		             +"Il ne faut pas g�n�rer un fichier wave mais lire directement les textes";
    		voix.playText(text);          // le contenu des questions est variable donc on les lit avec SI_VOX
    	}	
    	
    	this.requestFocus();  // on redonne le focus au JFrame principal  (apr�s un clic, le focus est sur le bouton) 
    }
 
    
    //Keyboard event listener: d�tecte les �l�ments clavier. 
    @Override
    public void keyPressed(KeyEvent e) {
    	super.keyPressed(e);		     // appel � la m�thode m�re qui g�re les �v�nements ESC, F1, F3, F4
    	if (e.getKeyCode()==KeyEvent.VK_F5){                  // cas particulier pour ce jeu : la touche F5
    	   	voix.playText("Vous venez d'appuyer sur EFFE 5");
    	}
    }
    
	/**
	 * Pour modifier les couleurs de fond et de premier plan de la fen�tre.
	 * Cette fonction est appel�e par la fonction "changeColor" de la classe "Preferences"
	 * � chaque fois que l'on presse F3 
	 * 
	 **/
	public  void changeColor() {
    	// on r�cup�re les couleurs de base dans la classe Preferences 
		Preferences pref = Preferences.getData();
		/*
		on change les couleurs des �l�ments qu'on veut. Exemple:
		lb1.setBackground(pref.getCurrentBackgroundColor());
		lb1.setForeground(pref.getCurrentForegroundColor());
		
		o� lb1 est un composant Swing (JComponent) tel que JPanel, JButton, JScrollPane, etc.
		*/
	}
	
	 /*
     * Fonctions qui renvoient les sons d'accueil et d'aide.
     * Pour plus d'informations, regarder la classe DevintFrameListener (dans le package devintAPI)
     */
	
	protected  String wavAccueil() {			    // renvoie le fichier wave contenant le message d'accueil
		return "../ressources/sons/accueilJeu.wav";
	}

	protected  String wavRegleJeu() {			    // renvoie le fichier wave contenant la r�gle du jeu
		return "../ressources/sons/aideF1.wav";
	}
	
	protected  String wavAide() {			        // renvoie le fichier wave contenant la r�gle du jeu
		return "../ressources/sons/aide.wav";
	}


}

// Quelques exemples de code:

/*
 *  Exemple de cr�ation d'un bouton
 
question = new JButton();
question.setText("Cliquez sur ce bouton pour �couter la question");
question.setBackground(new Color(50,50,255));
question.setBorder(new LineBorder(Color.BLACK,10));
	question.setFont(new Font("Georgia",1,40));
	question.addActionListener(this);          // c'est l'objet Jeu lui-m�me qui r�agit au clic souris

this.add(question,BorderLayout.EAST);      // on met le bouton � droite
*/
