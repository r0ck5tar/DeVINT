package ihm;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import fonctionnement.objet.ObjetEffet;

import t2s.SIVOXDevint;

public class IHMMenu {
	private SIVOXDevint voix;
	private JFrame parent;
	
	private JDialog menuGeneral; //utilisé pour le menu récupération de ressource, construction et choix de rentrer dans la cabane.
	
	private JButton boutonDe; //utilisé dans le menu pour lancer le dé
	private JButton boutonTrue;  //utilisé dans le menu de récupération de ressource et pour le choix de rentrer dans la cabane.
	private JButton boutonFalseEffet; //utilisé dans le menu présentant le choix d'utiliser un objet
	private JButton boutonFalse; //utilisé dans le menu de récupération de ressource, construction et pour le choix de rentrer.
	private ArrayList<JButton> listButtonConstruire;
	private ArrayList<JButton> listButtonEffet;

	private Font font = new Font("Tahoma", 1, 26);
	
	public IHMMenu(SIVOXDevint voix, JFrame parent) {
		this.voix = voix;
		this.parent = parent;
		listButtonConstruire = new ArrayList<JButton>();
		listButtonEffet = new ArrayList<JButton>();
	}

	public void afficheLancerDe(int qui) {
		voix.playShortText("joueur" + (qui + 1) + ", lance le dé");
		menuGeneral = new JDialog(parent, "lancer le dé");
		menuGeneral.setLayout(new BorderLayout());
		menuGeneral.setSize(220, 100);
		switch (qui) {
		case 0:
			menuGeneral.setLocation(570, 220);
			break;
		case 1:
			menuGeneral.setLocation(570, 620);
			break;
		case 2:
			menuGeneral.setLocation(870, 220);
			break;
		case 3:
			menuGeneral.setLocation(870, 620);
			break;
		}
		boutonDe = new JButton("lancer le dé");
		boutonDe.setFont(font);
		boutonDe.setVisible(true);
		boutonDe.addActionListener((ActionListener) parent);
		menuGeneral.add(boutonDe);
		menuGeneral.setVisible(true);
	}

	public void afficheRecupRessource() {
		voix.playShortText("Récupère la ressource?");
		menuGeneral = new JDialog(parent, "Recupere ressource !");
		menuGeneral.setLayout(new BorderLayout());
		menuGeneral.setSize(350, 120);
	
		boutonTrue = new JButton("Oui je recupere");
		boutonTrue.setFont(font);
		boutonTrue.setVisible(true);
		boutonTrue.addActionListener((ActionListener) parent);
	
		boutonFalse = new JButton("Non je ne veux pas");
		boutonFalse.setFont(font);
		boutonFalse.setVisible(true);
		boutonFalse.addActionListener((ActionListener) parent);
	
		menuGeneral.add(boutonTrue, BorderLayout.NORTH);
		menuGeneral.add(boutonFalse, BorderLayout.SOUTH);
		menuGeneral.setVisible(true);
	
	}
	
	public void afficheConstruction(ArrayList<String> listBuildable) {
		voix.playShortText("Que veux-tu construire ?");
		int nbConstructible = listBuildable.size();
		menuGeneral = new JDialog(parent, "Que veux-tu construire?");
		menuGeneral.setLayout(new GridBagLayout());
		GridBagConstraints buttonPos = new GridBagConstraints();
		buttonPos.fill = GridBagConstraints.BOTH;
		buttonPos.gridwidth = 1;
		buttonPos.gridheight = 1;
		buttonPos.gridy = 0;
		menuGeneral.setSize(350, 110 * (nbConstructible+1));
		
		for(int i = 0 ; i < nbConstructible; i++) {
			buttonPos.gridy = i;
			listButtonConstruire.add(new JButton(listBuildable.get(i)));
			listButtonConstruire.get(i).setFont(font);
			listButtonConstruire.get(i).setVisible(true);
			listButtonConstruire.get(i).addActionListener((ActionListener) parent);
			
			
			menuGeneral.add(listButtonConstruire.get(i), buttonPos);
		}
		
		boutonFalse.setFont(font);
		boutonFalse.setText("Rien construire");
		buttonPos.gridy = buttonPos.gridy+1; 
		menuGeneral.add(boutonFalse, buttonPos);
		menuGeneral.setVisible(true);
		menuGeneral.requestFocus();
	}
	
	public void afficheRentreChezToi() {
		voix.playShortText("Te voilà chez toi !");
		menuGeneral = new JDialog(parent, "Te voilà chez toi!");
		menuGeneral.setLayout(new BorderLayout());
		menuGeneral.setSize(350, 220);

		boutonTrue = new JButton("Oui je rentre");
		boutonTrue.setFont(font);
		boutonTrue.setVisible(true);
		boutonTrue.addActionListener((ActionListener) parent);

		boutonFalse = new JButton("Non je ne rentre pas");
		boutonFalse.setFont(font);
		boutonFalse.setVisible(true);
		boutonFalse.addActionListener((ActionListener) parent);

		menuGeneral.add(boutonTrue, BorderLayout.WEST);
		menuGeneral.add(boutonFalse, BorderLayout.EAST);
		menuGeneral.setVisible(true);
	}
	
	/**
	 * affiche seulement les objets à effet du sac du joueur. prend en
	 * paramettre l'arraylist qui correspond au Sac du joueur.
	 */
	public void afficheObjetEffet(ArrayList<ObjetEffet> listObjet) {
		voix.playShortText("Quel objet a effet veux-tu utiliser?");
		int nbObjetEffet = listObjet.size();
		menuGeneral = new JDialog(parent,"Quel objet a effet veux-tu utiliser?");
		menuGeneral.setLayout(new GridBagLayout());
		GridBagConstraints buttonPos = new GridBagConstraints();
		buttonPos.fill = GridBagConstraints.BOTH;
		buttonPos.gridwidth = 1;
		buttonPos.gridheight = 1;
		buttonPos.gridy = 0;
		menuGeneral.setSize(350, 110 * (nbObjetEffet+1));

		listButtonEffet = new ArrayList<JButton>();

		for (int i = 0; i < nbObjetEffet; i++) {
			buttonPos.gridy = i;
			listButtonEffet.add(new JButton(listObjet.get(i).getType().getNom()));
			listButtonEffet.get(i).setFont(font);
			listButtonEffet.get(i).setVisible(true);
			listButtonEffet.get(i).addActionListener((ActionListener) parent);

			menuGeneral.add(listButtonEffet.get(i), buttonPos);
		}
		buttonPos.gridy = buttonPos.gridy+1; 
		boutonFalseEffet = new JButton("Non je ne veux pas");
		boutonFalseEffet.setFont(font);
		boutonFalseEffet.setVisible(true);
		boutonFalseEffet.addActionListener((ActionListener) parent);
		menuGeneral.add(boutonFalseEffet, buttonPos);
		menuGeneral.setVisible(true);
	}
	
	/*
	 * Getters
	 */
	
	public JButton boutonDe() {
		return boutonDe;
	}

	public JDialog menuGeneral() {
		return menuGeneral;
	}

	public JButton boutonTrue() {
		return boutonTrue;
	}
	
	public JButton boutonFalseEffet() {
		return boutonFalseEffet;
	}

	public JButton boutonFalse() {
		return boutonFalse;
	}

	public ArrayList<JButton> listButtonConstruire() {
		return listButtonConstruire;
	}

	public ArrayList<JButton> listButtonEffet() {
		return listButtonEffet;
	}
}
