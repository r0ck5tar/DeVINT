package ihm;

import javax.swing.*;

import Tool.Tool;

import devintAPI.FenetreAbstraite;
import devintAPI.Preferences;
import fonctionnement.environnement.Case;
import fonctionnement.environnement.Plateau;
import fonctionnement.jeu.Game;
import fonctionnement.objet.Joueur;
import fonctionnement.objet.Objet;
import fonctionnement.objet.ObjetEffet;
import fonctionnement.objet.Ressource;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Cette classe est un exemple d'interface de jeu. Elle �tend DevintFrame pour
 * avoir un Frame et r�agir aux �v�nements claviers Impl�mente
 * ActionListener pour r�agir au clic souris sur le bouton. On surchage la
 * m�thode "keyPressed" pour associer une action � la touche F3
 * 
 * @author helene
 */

public class IHMGameView extends FenetreAbstraite implements ActionListener {

	private Game game;
	private Plateau plateauJeu;

	private IHMPlateau plateau;
	private ArrayList<Joueur> listJoueurs;
	private Map<Joueur, Color> couleursJoueurs;
	
	private IHMMenu menu;

	private JPanel infoJoueurGauche;
	private JPanel infoJoueurDroite;
	private ArrayList<IHMInfoJoueur> infoJoueurs;

	private int currentButton = 5;
	private int qui;

	/*
	 * Constructeur
	 */

	public IHMGameView(String title, Game game) {
		super(title);
		this.game = game;
		menu = new IHMMenu(voix, this);
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

		for (int i = 0; i < listJoueurs.size(); i++) {
			couleursJoueurs.put(listJoueurs.get(i), colorList.get(i));
		}

		infoJoueurs = new ArrayList<IHMInfoJoueur>();

		for (int i = 0; i < listJoueurs.size(); i++) {
			infoJoueurs.add(new IHMInfoJoueur(listJoueurs.get(i)));
		}

		setLayout(new FlowLayout(FlowLayout.CENTER));
		plateau = new IHMPlateau(plateauJeu, this);

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
		positionJouers.add(5);
		positionJouers.add(35);
		positionJouers.add(16);
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
	 * Fonctions permettant de g�rer les �v�nements et les actions
	 */

	// Action performed: D�fini les actions � effectuer lors de d�tection
	// des �v�nements
	public void actionPerformed(ActionEvent ae) {
		voix.stop(); // toujours stopper la voix avant de parler
	
		Object source = ae.getSource(); // on r�cup�re la source de
										// l'�v�nement
	
		if (source.equals(menu.boutonDe())) { // si c'est le bouton "question" on lit
										// la question
	
			int de = Tool.lancerDe(listJoueurs.get(qui).getDeplacementMax()) + 1;
			plateau.afficheChoixDeplacement(listJoueurs.get(qui).getPosition()
					.getChoixCase(de, null, listJoueurs.get(qui)));
			menu.menuDe().dispose();
	
			String text = Integer.toString(de);
			voix.playText(text); // le contenu des questions est variable donc
									// on les lit avec SI_VOX
			System.out.println(listJoueurs.get(qui).getPosition().getNom());
		}
		
		if (source.equals(menu.boutonTrue())) {
			if (listJoueurs.get(qui).getPosition() == listJoueurs.get(qui)
					.getCabane().getPosition()) {
				menu.menuGeneral().dispose();
				System.out
						.println("Votre inventaire de la cabane avant transfert\n : "
								+ listJoueurs.get(qui).getCabane().getStock()
										.getStock());
				Tool.viderSac(listJoueurs.get(qui));
				System.out
						.println("Votre inventaire de la cabane apres transfert\n : "
								+ listJoueurs.get(qui).getCabane().getStock()
										.getStock());
				ArrayList<String> listBuildable = Tool
						.getBuildables(listJoueurs.get(qui));
				if (listBuildable.isEmpty()) {
					// GO TO OBJET EFFET
					menu.afficheObjetEffet(Tool.recupObjetSpecial(listJoueurs.get(qui)));
					System.out.println("recupererobjet");
				} else {
					System.out.println("construction possible");
					menu.afficheConstruction(listBuildable);
				}
			} else {
				menu.menuGeneral().dispose();
				Objet r = Tool.recupRessource(listJoueurs.get(qui));
				if (r != null && r instanceof Ressource) {
					Tool.mettreRessource((Ressource) r, listJoueurs.get(qui));
					System.out.println("Vous avez recupere : " + r);
					System.out.println("Votre inventaire \n : "
							+ listJoueurs.get(qui).getSac().getStock());
				}
	
				menu.afficheObjetEffet(Tool.recupObjetSpecial(listJoueurs.get(qui)));
			}
			
			infoJoueurs.get(qui).updateDisplay();
		}
	
		for(int i = 0; i < menu.listButtonConstruire().size();i++) {
			if(source.equals(menu.listButtonConstruire().get(i))) {
				//METTRE IHM INFOJOUEUR
				Tool.construire(menu.listButtonConstruire().get(i).getText(), listJoueurs.get(qui));
	
				menu.menuGeneral().dispose();
				menu.listButtonConstruire().clear();
				play();
			}
		}
	
		for(int i = 0; i < menu.listButtonEffet().size();i++) {
			if(source.equals(menu.listButtonEffet().get(i))) {
				//METTRE IHM INFOJOUEUR
				menu.menuGeneral().dispose();
				
				Case positionAvantEffet = listJoueurs.get(qui).getPosition();
				
				ArrayList<ObjetEffet> l = Tool.recupObjetSpecial(listJoueurs.get(qui));
				Tool.appliquerEffet(listJoueurs.get(qui), l.get(i));
				
				if(positionAvantEffet == listJoueurs.get(qui).getPosition()) {
					play();
				}
				else {
					// ICI IL FAUT DESELECTIONNER LA CASE PRECEDENTE
					if (listJoueurs.get(qui).getPosition() == listJoueurs.get(qui)
							.getCabane().getPosition()) {
						menu.afficheRentreChezToi();
						
					} else {
						menu.afficheRecupRessource();
					}
				}
			}
		}
		
		if (source.equals(menu.boutonFalse())) {
			menu.menuGeneral().dispose();
			play();
		}
		
		if (source.equals(menu.boutonFalseConstruire()) || source.equals(menu.boutonFalseRessource())) {
			menu.menuGeneral().dispose();
			if(listJoueurs.get(qui).getSac().containsObjetEffet()) {
				menu.afficheObjetEffet(Tool.recupObjetSpecial(listJoueurs.get(qui)));
			}
			else {
				play();
			}
		}
	
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
	
		this.requestFocus(); // on redonne le focus au JFrame principal (apr�s
								// un clic, le focus est sur le bouton)
	}

	public void play() {
		qui++;
		if (qui > 3) {
			qui = 0;
		}
		
		unFocusedButton(currentButton);
		currentButton = plateau.getIndexOfCase(listJoueurs.get(qui).getPosition());
		shiftFocusedButton(currentButton);
		menu.lancerDe(qui);
	}

	public void setCaseJoueur() {
		for (Joueur joueur : listJoueurs) {
			plateau.getCaseAtIndex(joueur.getPosition()).setBackground(
					couleursJoueurs.get(joueur));
			plateau.getCaseAtIndex(joueur.getPosition()).setForeground(
					Color.BLACK);
		}
	}

	// Keyboard event listener: d�tecte les �l�ments clavier.
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e); // appel � la m�thode m�re qui g�re les
								// �v�nements ESC, F1, F3, F4
		switch (e.getKeyCode()) {

		case KeyEvent.VK_ENTER:
		case KeyEvent.VK_SPACE:
			// change position to the selected case if it's accessible.
			if (plateau.getIndiceCaseAccessibles().contains(currentButton)) {
				plateau.getCaseAtIndex(listJoueurs.get(qui).getPosition()).setCouleurCaseNormale();
				listJoueurs.get(qui).setPosition(plateauJeu.getCase(currentButton));
				setCaseJoueur();
				plateau.masquerChoixDeplacement(currentButton);
				
				System.out.println("pos:"+ listJoueurs.get(qui).getPosition().getNom());

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
	 * Pour modifier les couleurs de fond et de premier plan de la fen�tre.
	 * Cette fonction est appel�e par la fonction "changeColor" de la classe
	 * "Preferences" � chaque fois que l'on presse F3
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
	protected String wavAccueil() { // renvoie le fichier wave contenant le
									// message d'accueil
		return "../ressources/sons/accueilJeu.wav";
	}

	@Override
	protected String wavRegleJeu() { // renvoie le fichier wave contenant la
										// r�gle du jeu
		return "../ressources/sons/aideF1.wav";
	}

	@Override
	protected String wavAide() { // renvoie le fichier wave contenant la r�gle
									// du jeu
		return "../ressources/sons/aide.wav";
	}
}
