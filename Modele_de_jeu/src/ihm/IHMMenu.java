package ihm;

import java.awt.BorderLayout;
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
	
	private JDialog menuDe;
	private JDialog menuGeneral;
	
	private JButton boutonDe;
	private JButton boutonTrue;
	private JButton boutonFalseEffet;
	private JButton boutonFalse;
	private ArrayList<JButton> listButtonConstruire;
	private ArrayList<JButton> listButtonEffet;

	
	
	public IHMMenu(SIVOXDevint voix, JFrame parent) {
		this.voix = voix;
		this.parent = parent;
		listButtonConstruire = new ArrayList<JButton>();
		listButtonEffet = new ArrayList<JButton>();
	}

	public void afficheLancerDe(int qui) {
		voix.playShortText("joueur" + (qui + 1) + ", lance le dé");
		menuDe = new JDialog(parent, "lancer le dé");
		menuDe.setLayout(new BorderLayout());
		menuDe.setSize(200, 100);
		switch (qui) {
		case 0:
			menuDe.setLocation(570, 220);
			break;
		case 1:
			menuDe.setLocation(570, 620);
			break;
		case 2:
			menuDe.setLocation(870, 220);
			break;
		case 3:
			menuDe.setLocation(870, 620);
			break;
		}
		boutonDe = new JButton("lancer le dé");
		boutonDe.setVisible(true);
		boutonDe.addActionListener((ActionListener) parent);
		menuDe.add(boutonDe);
		menuDe.setVisible(true);
	}

	public void afficheRecupRessource() {
		
		menuGeneral = new JDialog(parent, "Recupere ressource !");
		menuGeneral.setLayout(new BorderLayout());
		menuGeneral.setSize(350, 100);
	
		boutonTrue = new JButton("Oui je recupere");
		boutonTrue.setVisible(true);
		boutonTrue.addActionListener((ActionListener) parent);
	
		boutonFalse = new JButton("Non je ne veux pas");
		boutonFalse.setVisible(true);
		boutonFalse.addActionListener((ActionListener) parent);
	
		menuGeneral.add(boutonTrue, BorderLayout.WEST);
		menuGeneral.add(boutonFalse, BorderLayout.EAST);
		menuGeneral.setVisible(true);
	
	}
	
	public void afficheConstruction(ArrayList<String> listBuildable) {
		int nbConstructible = listBuildable.size();
		menuGeneral = new JDialog(parent, "Que voulez vous construire ?");
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
			listButtonConstruire.get(i).addActionListener((ActionListener) parent);
			
			
			menuGeneral.add(listButtonConstruire.get(i), buttonPos);
		}
		
		buttonPos.gridx = buttonPos.gridx+1; 
		menuGeneral.add(boutonFalse, buttonPos);
		menuGeneral.setVisible(true);
		menuGeneral.requestFocus();
	}
	
	public void afficheRentreChezToi() {
		menuGeneral = new JDialog(parent, "Te voilà chez toi !");
		menuGeneral.setLayout(new BorderLayout());
		menuGeneral.setSize(350, 100);

		boutonTrue = new JButton("Oui je rentre");
		boutonTrue.setVisible(true);
		boutonTrue.addActionListener((ActionListener) parent);

		boutonFalse = new JButton("Non je ne rentre pas");
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
		int nbObjetEffet = listObjet.size();
		System.out.println("TEST GROSSE PUTE");
		menuGeneral = new JDialog(parent,"Quel objet a effet voulez vous utiliser?");
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
			listButtonEffet.get(i).addActionListener((ActionListener) parent);

			menuGeneral.add(listButtonEffet.get(i), buttonPos);
		}
		System.out.println("TEST GROSSE PUTE DEUX");
		buttonPos.gridx = buttonPos.gridx+1; 
		boutonFalseEffet = new JButton("Non je ne veux pas");
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

	public JDialog menuDe() {
		return menuDe;
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
