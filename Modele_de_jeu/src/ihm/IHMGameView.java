package ihm;

import ihm.IHMInfoJoueur.Objets;

import javax.swing.*;

import Tool.Tool;

import devintAPI.FenetreAbstraite;
import devintAPI.Preferences;
import fonctionnement.environnement.Case;
import fonctionnement.environnement.Plateau;
import fonctionnement.jeu.Game;
import fonctionnement.objet.Cabane;
import fonctionnement.objet.Joueur;
import fonctionnement.objet.Objet;
import fonctionnement.objet.ObjetEffet;
import fonctionnement.objet.Ressource;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class IHMGameView extends FenetreAbstraite implements ActionListener {

	private Game game;
	private Plateau plateauJeu;
	private ArrayList<Joueur> listJoueurs;
	private Map<Joueur, Color> couleursJoueurs;

	private IHMPlateau plateau;
	private IHMMenu menu;
	private ArrayList<IHMInfoJoueur> infoJoueurs;
	
	private JPanel infoJoueurGauche;
	private JPanel infoJoueurDroite;

	private int currentButton = 5;
	private int qui = -1;

	/*
	 * Constructeur
	 */

	public IHMGameView(String title, Game game) {
		super(title);
		this.game = game;

		game.initialisePlateau();
		game.setQui(qui);
		plateauJeu = game.getPlateau();
		listJoueurs = game.getJoueurs();
		
		couleursJoueurs = new HashMap<Joueur, Color>();
		menu = new IHMMenu(voix, this);
		
		initialize();
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

		for (int i = 0; i < listJoueurs.size(); i++) {
			couleursJoueurs.put(listJoueurs.get(i), colorList.get(i));
		}

		infoJoueurs = new ArrayList<IHMInfoJoueur>();

		for (int i = 0; i < listJoueurs.size(); i++) {
			infoJoueurs.add(new IHMInfoJoueur(listJoueurs.get(i), couleursJoueurs.get(listJoueurs.get(i)), this));
		}

		setLayout(new FlowLayout(FlowLayout.CENTER));
		ArrayList<Cabane> cabanes = new ArrayList<Cabane>();
		for(int i=0; i<listJoueurs.size(); i++) {
			cabanes.add(listJoueurs.get(i).getCabane());
		}
		plateau = new IHMPlateau(plateauJeu, cabanes,couleursJoueurs, this);

		infoJoueurGauche = new JPanel();
		infoJoueurDroite = new JPanel();

		infoJoueurDroite.setLayout(new BorderLayout());
		infoJoueurDroite.add(infoJoueurs.get(0), BorderLayout.NORTH);
		infoJoueurDroite.add(infoJoueurs.get(1), BorderLayout.SOUTH);

		if (infoJoueurs.size() > 2) {
			infoJoueurGauche.setLayout(new BorderLayout());
			infoJoueurGauche.add(infoJoueurs.get(2), BorderLayout.NORTH);
			infoJoueurGauche.add(infoJoueurs.get(3), BorderLayout.SOUTH);
		}

		this.add(infoJoueurDroite);
		this.add(plateau);
		this.add(infoJoueurGauche);
	}

	private void initializePlayerPositions() {
		ArrayList<Integer> positionJouers = new ArrayList<Integer>();
		positionJouers.add(16);
		positionJouers.add(35);
		positionJouers.add(5);
		positionJouers.add(26);
		for (int i = 0; i < listJoueurs.size(); i++) {
			listJoueurs.get(i).setPosition(
					game.getPlateau().getCase(positionJouers.get(i)));
		}
		for (int i = 0; i < listJoueurs.size(); i++) {
			listJoueurs.get(i).getCabane()
					.setPosition(listJoueurs.get(i).getPosition());
		}
		setCaseJoueur();
	}
	
	/*
	 * Fonctions permettant de gerer les evenements et les actions
	 */

	// Action performed: defini les actions a effectuer lors de detection des evenements
	public void actionPerformed(ActionEvent ae) {
		voix.stop(); // toujours stopper la voix avant de parler
		this.requestFocus(); // on redonne le focus au JFrame principal (apres un clic, le focus est sur le bouton)
		boolean disposeMenuGeneral = true;
	
		Object source = ae.getSource(); // on recupere la source de l'evenement
		
		if (source.getClass().getSimpleName().equals("IHMCase")) {
			for (int i = 0; i < 57; i++) {
				if (plateau.getCase(i).equals(source)) {
					unFocusedButton(currentButton);
					currentButton = i;
					setFocusedButton(currentButton);
					break;
				}
			}
		}
		
		else {
			for(IHMInfoJoueur infoJ :infoJoueurs) {
				for(int i=0; i<infoJ.boutonSac().size(); i++){
					if(source.equals(infoJ.boutonSac().get(i))){
						disposeMenuGeneral = false;
						voix.playShortText(Objets.atValue(i).toTypeRessource().getNom() +"," + infoJ.nbRessourceSac(i));
						break;
					}
					
					if(source.equals(infoJ.boutonCabane().get(i))){
						disposeMenuGeneral = false;
						System.out.println(Objets.atValue(i).toTypeRessource().getNom());
						voix.playShortText(Objets.atValue(i).toTypeRessource().getNom() +"," + infoJ.nbRessourceCabane(i));
						break;
					}
				}
			}
			
			
			if(disposeMenuGeneral) {menu.menuGeneral().dispose();}
			
			/*
			 * When the player clicks on boutonDe
			 */
			if (source.equals(menu.boutonDe())) { 
				//throw the dice.
				int de = Tool.lancerDe(listJoueurs.get(qui).getDeplacementMax()) + 1;
				//highlight the spaces which the player can move to.
				plateau.afficheChoixDeplacement(listJoueurs.get(qui).getPosition().getChoixCase(de, null, listJoueurs.get(qui)));

				String chiffre = Integer.toString(de);
				voix.playText(chiffre); 
			}

			if (source.equals(menu.boutonTrue())) {
				/*
				 * if the player is in his own cabin, clicking the "true" button does the following:
				 * transfer objects from bag, then show the menu for construction and for using objects.
				 */
				if (listJoueurs.get(qui).getPosition() == listJoueurs.get(qui).getCabane().getPosition()) {

					Tool.viderSac(listJoueurs.get(qui)); //empty the player's bag

					ArrayList<String> listBuildable = Tool.getBuildables(listJoueurs.get(qui));
					//If something is buildable, show the menu for construction.
					if (!listBuildable.isEmpty()) {
						menu.afficheConstruction(listBuildable);	
					} 

					//otherwise, if nothing is buildable, skip to the menu for using objects.
					else {
						menu.afficheObjetEffet(Tool.recupObjetSpecial(listJoueurs.get(qui)));

					}
					
					infoJoueurs.get(qui).updateDisplay();
				} 
				
				/*
				 * if the player is on a resource space, clicking the "true" button does the following:
				 * recover the resource and add it to the player's bag, then show the menu for using objects. 
				 */
				else {
					Objet r = Tool.recupRessource(listJoueurs.get(qui));
					if (r != null && r instanceof Ressource) {
						Tool.mettreRessource((Ressource) r, listJoueurs.get(qui));
					}

					menu.afficheObjetEffet(Tool.recupObjetSpecial(listJoueurs.get(qui)));
				}
				infoJoueurs.get(qui).updateDisplay();
			}

			/*
			 * Check if the source is one of the buttons in the listButtonConstruire
			 */
			for(int i = 0; i < menu.listButtonConstruire().size();i++) {
				if(source.equals(menu.listButtonConstruire().get(i))) {
					//Construct the selected object
					Tool.construire(menu.listButtonConstruire().get(i).getText(), listJoueurs.get(qui));
				
					infoJoueurs.get(qui).updateDisplay();
					plateau.getCabane(qui).updateCabane(menu.listButtonConstruire().get(i).getText());
					menu.listButtonConstruire().clear();
					play();
				}
			}

			/*
			 * Check if the source is one of the buttons in the listButtonEffet
			 */
			for(int i = 0; i < menu.listButtonEffet().size();i++) {
				if(source.equals(menu.listButtonEffet().get(i))) {

					Case positionAvantEffet = listJoueurs.get(qui).getPosition();

					//Use the selected object
					ArrayList<ObjetEffet> l = Tool.recupObjetSpecial(listJoueurs.get(qui));
					Tool.appliquerEffet(listJoueurs.get(qui), l.get(i));
					
					infoJoueurs.get(qui).updateDisplay();

					if(positionAvantEffet == listJoueurs.get(qui).getPosition()) {
						play();
					}
					
					else {
						// ICI IL FAUT DESELECTIONNER LA CASE PRECEDENTE
						if (listJoueurs.get(qui).getPosition() == listJoueurs.get(qui).getCabane().getPosition()) {
							menu.afficheRentreChezToi();

						} else {
							menu.afficheRecupRessource();
						}
					}
					
				}
			}

			/*
			 * When the player clicks on boutonFalseEffet in the menu for using objects
			 */
			if (source.equals(menu.boutonFalseEffet())) {
				play();
			}

			/*
			 * When the player clicks on the boutonFalse in the menu for Taking a resource, construction or going into his cabin.
			 */
			if (source.equals(menu.boutonFalse())) {
				if(listJoueurs.get(qui).getSac().containsObjetEffet()) {
					menu.afficheObjetEffet(Tool.recupObjetSpecial(listJoueurs.get(qui)));
				}
				else {
					play();
				}
			}
		}
	
		
	}

	public void play() {
		qui++;
		if (qui > 3) {
			qui = 0;
		}
		
		unFocusedButton(currentButton);
		currentButton = plateau.getIndexOfCase(listJoueurs.get(qui).getPosition());
		shiftFocusedButton(currentButton);
		menu.afficheLancerDe(qui);
	}

	public void setCaseJoueur() {
		for (Joueur joueur : listJoueurs) {
			plateau.getCaseAtIndex(joueur.getPosition()).setBackground(
					couleursJoueurs.get(joueur));
			plateau.getCaseAtIndex(joueur.getPosition()).setForeground(
					Color.BLACK);
		}
	}

	// Keyboard event listener: detecte les evenements clavier.
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e); // appel a la methode mere qui gere les evenements ESC, F1, F3, F4
		switch (e.getKeyCode()) {

		case KeyEvent.VK_ENTER:
		case KeyEvent.VK_SPACE:
			// change position to the selected case if it's accessible.
			if (plateau.getIndiceCaseAccessibles().contains(currentButton)) {
				plateau.getCaseAtIndex(listJoueurs.get(qui).getPosition()).setCouleurCaseNormale();
				listJoueurs.get(qui).setPosition(plateauJeu.getCase(currentButton));
				setCaseJoueur();
				plateau.masquerChoixDeplacement(currentButton);

				if (listJoueurs.get(qui).getPosition() == listJoueurs.get(qui)
						.getCabane().getPosition()) {
					menu.afficheRentreChezToi();
				} 
				
				else if (!listJoueurs.get(qui).getPosition().getNom().equals("home")) {
					menu.afficheRecupRessource();
				}
				else {
					menu.afficheObjetEffet(Tool.recupObjetSpecial(listJoueurs.get(qui)));
				}
			}
			break;
		case KeyEvent.VK_UP:
			unFocusedButton(currentButton);
			if ((currentButton >= 22 && currentButton < 30)
					|| (currentButton >= 40 && currentButton < 48)
					|| (currentButton >= 31 && currentButton < 39)) {
				currentButton++;
			}

			else if (currentButton == 0) {
				currentButton = 22;
			} else if (currentButton == 10) {
				currentButton = 31;
			} else if (currentButton == 5) {
				currentButton = 40;
			} else if (currentButton == 30) {
				currentButton = 11;
			} else if (currentButton == 48) {
				currentButton = 16;
			} else if (currentButton == 39) {
				currentButton = 21;
			}
			setFocusedButton(currentButton);
			break;

		case KeyEvent.VK_DOWN:
			unFocusedButton(currentButton);
			if ((currentButton > 31 && currentButton <= 39)
					|| (currentButton > 40 && currentButton <= 48)
					|| (currentButton > 22 && currentButton <= 30)) {
				currentButton--;
			} else if (currentButton == 31) {
				currentButton = 10;
			} else if (currentButton == 40) {
				currentButton = 5;
			} else if (currentButton == 22) {
				currentButton = 0;
			} else if (currentButton == 11) {
				currentButton = 30;
			} else if (currentButton == 16) {
				currentButton = 48;
			} else if (currentButton == 21) {
				currentButton = 39;
			}

			setFocusedButton(currentButton);
			break;

		case KeyEvent.VK_LEFT:
			unFocusedButton(currentButton);
			if ((currentButton > 11 && currentButton <= 21)
					|| (currentButton > 0 && currentButton <= 10)
					|| (currentButton > 49 && currentButton <= 52)
					|| (currentButton > 53 && currentButton <= 56)) {
				currentButton--;
			} else if (currentButton == 53) {
				currentButton = 44;
			} else if (currentButton == 44) {
				currentButton = 52;
			} else if (currentButton == 35) {
				currentButton = 56;
			} else if (currentButton == 49) {
				currentButton = 26;
			}

			setFocusedButton(currentButton);
			break;

		case KeyEvent.VK_RIGHT:
			unFocusedButton(currentButton);
			if ((currentButton >= 0 && currentButton < 10)
					|| (currentButton >= 11 && currentButton < 21)
					|| (currentButton >= 49 && currentButton < 52)
					|| (currentButton >= 53 && currentButton < 56)) {
				currentButton++;
			} else if (currentButton == 26) {
				currentButton = 49;
			} else if (currentButton == 44) {
				currentButton = 53;
			} else if (currentButton == 56) {
				currentButton = 35;
			} else if (currentButton == 52) {
				currentButton = 44;
			}

			setFocusedButton(currentButton);
			break;
		case KeyEvent.VK_F5:
			currentButton = 20;
		}
	}

	/**
	 * Pour modifier les couleurs de fond et de premier plan de la fenetre.
	 * Cette fonction est appellee par la fonction "changeColor" de la classe
	 * "Preferences" a chaque fois que l'on presse F3
	 * 
	 **/
	@Override
	public void changeColor() {
		// on r�cup�re les couleurs de base dans la classe Preferences
		Preferences pref = Preferences.getData();

		// on change les couleurs de tous les �l�ments

		for (int i = 0; i < 57; i++) {
			JButton button = plateau.getCase(i);
			button.setBackground(pref.getCurrentBackgroundColor());
			button.setForeground(pref.getCurrentForegroundColor());
		}

		for (int i = 0; i < 4; i++) {
			JButton button = plateau.getCabane(i);
			button.setBackground(pref.getCurrentBackgroundColor());
			button.setForeground(pref.getCurrentForegroundColor());
		}

		setFocusedButton(currentButton);
		setCaseJoueur();
	}

	// mettre le focus sur un bouton
	private void setFocusedButton(int i) {
		IHMCase button = plateau.getCase(i);
		voix.playShortText(button.getContenu());
		Color oldBackground = button.getBackground();
		button.setBackground(button.getForeground());
		button.setForeground(oldBackground);
	}

	private void shiftFocusedButton(int i) {
		IHMCase button = plateau.getCase(i);
		Color oldBackground = button.getBackground();
		button.setBackground(button.getForeground());
		button.setForeground(oldBackground);
	}

	// enlever le focus d'un bouton
	private void unFocusedButton(int i) {

		if (i >= 0) {
			JButton button = plateau.getCase(i);
			Color oldBackground = button.getBackground();
			button.setBackground(button.getForeground());
			button.setForeground(oldBackground);
			setCaseJoueur();
		}
	}
	  

	/*
	 * Fonctions qui renvoient les sons d'accueil et d'aide. Pour plus
	 * d'informations, regarder la classe DevintFrameListener (dans le package
	 * devintAPI)
	 */

	@Override
	protected String wavAccueil() { // renvoie le fichier wave contenant le message d'accueil
		return "../ressources/sons/accueilJeu.wav";
	}

	@Override
	protected String wavRegleJeu() { // renvoie le fichier wave contenant la regle du jeu
		return "../ressources/sons/aideF1.wav";
	}

	@Override
	protected String wavAide() { // renvoie le fichier wave contenant la regle du jeu
		return "../ressources/sons/aide.wav";
	}
}
