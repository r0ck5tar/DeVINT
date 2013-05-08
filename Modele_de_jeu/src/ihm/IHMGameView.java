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
 * Cette classe est un exemple d'interface de jeu. Elle ï¿½tend DevintFrame pour
 * avoir un Frame et rï¿½agir aux ï¿½vï¿½nements claviers Implï¿½mente
 * ActionListener pour rï¿½agir au clic souris sur le bouton. On surchage la
 * mï¿½thode "keyPressed" pour associer une action ï¿½ la touche F3
 * 
 * @author helene
 */

public class IHMGameView extends FenetreAbstraite implements ActionListener {

	private Game game;
	private Plateau plateauJeu;

	private JButton boutonTrue;
	private JButton boutonFalse;
	private JButton boutonFalseConstruire;
	private JButton boutonFalseRessource;

	private ArrayList<JButton> listButtonConstruire;
	private ArrayList<JButton> listButtonEffet;

	private IHMPlateau plateau;
	private ArrayList<Joueur> listJoueurs;
	private Map<Joueur, Color> couleursJoueurs;
	
	private JDialog menuGeneral;
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
		listButtonConstruire = new ArrayList<JButton>();
		listButtonEffet = new ArrayList<JButton>();
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
	 * Fonctions permettant de gï¿½rer les ï¿½vï¿½nements et les actions
	 */

	// Action performed: Dï¿½fini les actions ï¿½ effectuer lors de dï¿½tection
	// des ï¿½vï¿½nements
	public void actionPerformed(ActionEvent ae) {
		voix.stop(); // toujours stopper la voix avant de parler
	
		Object source = ae.getSource(); // on rï¿½cupï¿½re la source de
										// l'ï¿½vï¿½nement
	
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
		
		if (source.equals(boutonTrue)) {
			if (listJoueurs.get(qui).getPosition() == listJoueurs.get(qui)
					.getCabane().getPosition()) {
				menuGeneral.dispose();
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
					afficheObjetEffet(Tool.recupObjetSpecial(listJoueurs.get(qui)));
					System.out.println("recupererobjet");
				} else {
					System.out.println("construction possible");
					afficheConstruction(listBuildable);
				}
			} else {
				menuGeneral.dispose();
				Objet r = Tool.recupRessource(listJoueurs.get(qui));
				if (r != null && r instanceof Ressource) {
					Tool.mettreRessource((Ressource) r, listJoueurs.get(qui));
					System.out.println("Vous avez recupere : " + r);
					System.out.println("Votre inventaire \n : "
							+ listJoueurs.get(qui).getSac().getStock());
				}
	
				afficheObjetEffet(Tool.recupObjetSpecial(listJoueurs.get(qui)));
			}
			
			infoJoueurs.get(qui).updateDisplay();
		}
	
		for(int i = 0; i < listButtonConstruire.size();i++) {
			if(source.equals(listButtonConstruire.get(i))) {
				//METTRE IHM INFOJOUEUR
				Tool.construire(listButtonConstruire.get(i).getText(), listJoueurs.get(qui));
	
				menuGeneral.dispose();
				listButtonConstruire.clear();
				play();
			}
		}
	
		for(int i = 0; i < listButtonEffet.size();i++) {
			if(source.equals(listButtonEffet.get(i))) {
				//METTRE IHM INFOJOUEUR
				menuGeneral.dispose();
				
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
						afficheRentreChezToi();
						
					} else {
						afficheRecupRessource();
					}
				}
			}
		}
		
		if (source.equals(boutonFalse)) {
			menuGeneral.dispose();
			play();
		}
		
		if (source.equals(boutonFalseConstruire) || source.equals(boutonFalseRessource)) {
			menuGeneral.dispose();
			if(listJoueurs.get(qui).getSac().containsObjetEffet()) {
				afficheObjetEffet(Tool.recupObjetSpecial(listJoueurs.get(qui)));
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
	
		this.requestFocus(); // on redonne le focus au JFrame principal (aprï¿½s
								// un clic, le focus est sur le bouton)
	}

	public void play() {
		qui++;
		if (qui > 3) {
			qui = 0;
		}
		
		unFocusedButton(currentButton);
		currentButton = plateau.getIndexOfCase(listJoueurs.get(qui)
				.getPosition());
		shiftFocusedButton(currentButton);
		menu.lancerDe(qui);
	}

	private void afficheRecupRessource() {
	
		menuGeneral = new JDialog(this, "Recupere ressource !");
		menuGeneral.setLayout(new BorderLayout());
		menuGeneral.setSize(350, 100);
	
		boutonTrue = new JButton("Oui je recupere");
		boutonTrue.setVisible(true);
		boutonTrue.addActionListener(this);
	
		boutonFalseRessource = new JButton("Non je ne veux pas");
		boutonFalseRessource.setVisible(true);
		boutonFalseRessource.addActionListener(this);
	
		menuGeneral.add(boutonTrue, BorderLayout.WEST);
		menuGeneral.add(boutonFalseRessource, BorderLayout.EAST);
		menuGeneral.setVisible(true);
	
	}

	private void afficheConstruction(ArrayList<String> listBuildable) {
		int nbConstructible = listBuildable.size();
		menuGeneral = new JDialog(this, "Que voulez vous construire ?");
		menuGeneral.setLayout(new GridBagLayout());
		GridBagConstraints buttonPos = new GridBagConstraints();
		buttonPos.fill = GridBagConstraints.BOTH;
		buttonPos.gridwidth = 1;
		buttonPos.gridheight = 1;
		buttonPos.gridy = 0;
		menuGeneral.setSize(350, 100);
		
		for(int i = 0 ; i < nbConstructible; i++) {
			buttonPos.gridx = i;
			listButtonConstruire.add(new JButton(listBuildable.get(i)));
			listButtonConstruire.get(i).setVisible(true);
			listButtonConstruire.get(i).addActionListener(this);
			
			
			menuGeneral.add(listButtonConstruire.get(i), buttonPos);
		}
		
		buttonPos.gridx = buttonPos.gridx+1; 
		menuGeneral.add(boutonFalseConstruire, buttonPos);
		menuGeneral.setVisible(true);
		menuGeneral.requestFocus();

	}

	/**
	 * affiche seulement les objets à effet du sac du joueur. prend en
	 * paramettre l'arraylist qui correspond au Sac du joueur.
	 */
	private void afficheObjetEffet(ArrayList<ObjetEffet> listObjet) {
		int nbObjetEffet = listObjet.size();
		System.out.println("TEST GROSSE PUTE");
		menuGeneral = new JDialog(this,"Quel objet a effet voulez vous utiliser?");
		menuGeneral.setLayout(new GridBagLayout());
		GridBagConstraints buttonPos = new GridBagConstraints();
		buttonPos.fill = GridBagConstraints.BOTH;
		buttonPos.gridwidth = 1;
		buttonPos.gridheight = 1;
		buttonPos.gridy = 0;
		menuGeneral.setSize(350, 100);

		listButtonEffet = new ArrayList<JButton>();

		for (int i = 0; i < nbObjetEffet; i++) {
			buttonPos.gridx = i;
			listButtonEffet.add(new JButton(listObjet.get(i).getType().getNom()));
			listButtonEffet.get(i).setVisible(true);
			listButtonEffet.get(i).addActionListener(this);

			menuGeneral.add(listButtonEffet.get(i), buttonPos);
		}
		System.out.println("TEST GROSSE PUTE DEUX");
		buttonPos.gridx = buttonPos.gridx+1; 
		boutonFalse = new JButton("Non je ne veux pas");
		boutonFalse.setVisible(true);
		boutonFalse.addActionListener(this);
		menuGeneral.add(boutonFalse, buttonPos);
		menuGeneral.setVisible(true);
	}

	public void afficheRentreChezToi() {
		menuGeneral = new JDialog(this, "Te voilà chez toi !");
		menuGeneral.setLayout(new BorderLayout());
		menuGeneral.setSize(350, 100);

		boutonTrue = new JButton("Oui je rentre");
		boutonTrue.setVisible(true);
		boutonTrue.addActionListener(this);

		boutonFalseConstruire = new JButton("Non je ne rentre pas");
		boutonFalseConstruire.setVisible(true);
		boutonFalseConstruire.addActionListener(this);

		menuGeneral.add(boutonTrue, BorderLayout.WEST);
		menuGeneral.add(boutonFalseConstruire, BorderLayout.EAST);
		menuGeneral.setVisible(true);

	}

	public void setCaseJoueur() {
		for (Joueur joueur : listJoueurs) {
			plateau.getCaseAtIndex(joueur.getPosition()).setBackground(
					couleursJoueurs.get(joueur));
			plateau.getCaseAtIndex(joueur.getPosition()).setForeground(
					Color.BLACK);
		}
	}

	// Keyboard event listener: dï¿½tecte les ï¿½lï¿½ments clavier.
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e); // appel ï¿½ la mï¿½thode mï¿½re qui gï¿½re les
								// ï¿½vï¿½nements ESC, F1, F3, F4
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
					afficheRentreChezToi();
				} 
				
				else if (!listJoueurs.get(qui).getPosition().getNom().equals("home")) {
					afficheRecupRessource();
				}
				else {
					afficheObjetEffet(Tool.recupObjetSpecial(listJoueurs.get(qui)));
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
	 * Pour modifier les couleurs de fond et de premier plan de la fenï¿½tre.
	 * Cette fonction est appelï¿½e par la fonction "changeColor" de la classe
	 * "Preferences" ï¿½ chaque fois que l'on presse F3
	 * 
	 **/
	@Override
	public void changeColor() {
		// on rï¿½cupï¿½re les couleurs de base dans la classe Preferences
		Preferences pref = Preferences.getData();

		// on change les couleurs de tous les ï¿½lï¿½ments

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
										// rï¿½gle du jeu
		return "../ressources/sons/aideF1.wav";
	}

	@Override
	protected String wavAide() { // renvoie le fichier wave contenant la rï¿½gle
									// du jeu
		return "../ressources/sons/aide.wav";
	}
}
