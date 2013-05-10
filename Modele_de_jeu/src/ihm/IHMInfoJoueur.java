package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import Tool.Tool;

import devintAPI.Preferences;
import fonctionnement.objet.Joueur;
import fonctionnement.objet.Objet;
import fonctionnement.objet.TypeRessource;


public class IHMInfoJoueur extends JTextArea{
	private Joueur joueur;
	private ActionListener parent;
	private Font font;
	
	private JTextPane inventaire = new JTextPane();
	private JTextPane dansLaCabane = new JTextPane();
	private JTextPane constructible = new JTextPane();
	private JTextPane constructible2 = new JTextPane();
	
	//0-bois 1-eau 2-pierre 3-liane 4-nourriture
	private ArrayList<JTextPane> nbRessourceSac = new ArrayList<JTextPane>();
	private ArrayList<JTextPane> nbRessourceCabane = new ArrayList<JTextPane>();
	private ArrayList<JButton> boutonCabane = new ArrayList<JButton>();
	private ArrayList<JButton> boutonSac = new ArrayList<JButton>();
	private ArrayList<ImageIcon> icones = new ArrayList<ImageIcon>();
	
	public enum Objets {
		BOIS, EAU, PIERRE, LIANE, NOURRITURE, VOILE, FILET, LANCE, CATAPULTE, BOUSSOLE, SAC;
		
		public static Objets atValue(int n) {
			return values()[n];
		}
		
		public TypeRessource toTypeRessource() {
			switch(this) {
			case BOIS: return TypeRessource.BOIS;
			case EAU: return TypeRessource.EAU;
			case PIERRE: return TypeRessource.PIERRE;
			case LIANE: return TypeRessource.LIANE;
			case NOURRITURE: return TypeRessource.NOURRITURE;
			case VOILE: return TypeRessource.VOILE;
			case FILET: return TypeRessource.FILET;
			default: return null;
			}
		}
	}
	
	public IHMInfoJoueur(Joueur joueur, ActionListener parent) {
		super(joueur.getNom());
		this.joueur = joueur;
		this.parent = parent;

		Preferences pref = Preferences.getData();
		setLayout(new GridBagLayout());
		
		icones.add(new ImageIcon("../ressources/images//icons/bois.JPG"));
		icones.add(new ImageIcon("../ressources/images//icons/eau.JPG"));
		icones.add(new ImageIcon("../ressources/images//icons/pierre.JPG"));
		icones.add(new ImageIcon("../ressources/images//icons/liane.JPG"));
		icones.add(new ImageIcon("../ressources/images//icons/nourriture.JPG"));
		
		font = new Font("Tahoma", 1, 24);
		setPreferredSize(new Dimension(320, 410));
		setBackground(pref.getCurrentBackgroundColor());
		setForeground(pref.getCurrentForegroundColor());
		setFont(font);

		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setOpaque(true);
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		
		inventaire.setFont(font);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 5;
		this.add(inventaire, gridBagConstraints);
		
		inventoryIcons(1);
		displayInventoryNumbers(1);
		
		dansLaCabane.setFont(font);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.gridwidth = 5;
		this.add(dansLaCabane, gridBagConstraints);
		
		JTextPane constructibleLabel = new JTextPane();
		constructibleLabel.setFont(font);
		constructibleLabel.setText("Constructible :");
		gridBagConstraints.gridy = 6;
		gridBagConstraints.gridwidth = 5;
		this.add(constructibleLabel, gridBagConstraints);
		
		constructible.setFont(font);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.gridwidth = 5;
		this.add(constructible, gridBagConstraints);
		
		constructible2.setFont(font);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 8;
		gridBagConstraints.gridwidth = 5;
		this.add(constructible2, gridBagConstraints);
		
		
		updateDisplay();
		cabinIcons(4);
		displayCabinNumbers(4);
	}
	
	public void updateDisplay() {
		
		//0-bois 1-eau 2-pierre 3-liane 4-nourriture
		ArrayList<Integer> quantitySac = new ArrayList<Integer>();
		ArrayList<Integer> quantityCabane = new ArrayList<Integer>();
		for(int i=0; i<9; i++) {
			quantitySac.add(0);
			quantityCabane.add(0);
		}
		
		inventaire.setText("Inventaire ("+joueur.getSac().getStock().size()  +"/"+joueur.getSac().getLimite()+")");
		for(Objet objet : joueur.getSac().getStock()) {
			//0-bois 1-eau 2-pierre 3-liane 4-nourriture
			switch(objet.toString()) {
			case "bois" : quantitySac.set(0, quantitySac.get(0)+1); break;
			case "eau" : quantitySac.set(1, quantitySac.get(1)+1); break;
			case "pierre" : quantitySac.set(2, quantitySac.get(2)+1); break;
			case "liane" : quantitySac.set(3, quantitySac.get(3)+1); break;
			case "nourriture" : quantitySac.set(4, quantitySac.get(4)+1); break;
			
			case "lance" : quantitySac.set(5, quantitySac.get(5)+1); break;
			case "boussole" : quantitySac.set(6, quantitySac.get(6)+1); break;
			case "catapulte" : quantitySac.set(7, quantitySac.get(7)+1); break;
			case "sac" : quantitySac.set(8, quantitySac.get(8)+1); break;
			}
		}
		
		for(int i=0; i<5; i++) {
			nbRessourceSac.get(i).setText(" "+String.valueOf(quantitySac.get(i)));
		}
		
		
		dansLaCabane.setText("Dans la cabane ("+joueur.getCabane().getStock().getStock().size() +"/"
							+joueur.getCabane().getStock().getNiveau()+")");
		for(Objet objet : joueur.getCabane().getStock().getStock()) {
			switch(objet.toString()) {
			case "bois" : quantityCabane.set(0, quantityCabane.get(0)+1); break;
			case "eau" : quantityCabane.set(1, quantityCabane.get(1)+1); break;
			case "pierre" : quantityCabane.set(2, quantityCabane.get(2)+1); break;
			case "liane" : quantityCabane.set(3, quantityCabane.get(3)+1); break;
			case "nourriture" : quantityCabane.set(4, quantityCabane.get(4)+1); break;
			
			case "lance" : quantityCabane.set(5, quantityCabane.get(5)+1); break;
			case "boussole" : quantityCabane.set(6, quantityCabane.get(6)+1); break;
			case "catapulte" : quantityCabane.set(7, quantityCabane.get(7)+1); break;
			case "sac" : quantityCabane.set(8, quantityCabane.get(8)+1); break;
			}
		}
		
		for(int i=0; i<5; i++) {
			nbRessourceCabane.add(new JTextPane());
			nbRessourceCabane.get(i).setText(" "+String.valueOf(quantityCabane.get(i)));
		}
		
		String buildable;
		ArrayList<String> getBuildables = Tool.getBuildables(joueur);
		
		if (getBuildables.size() > 3) {			
			buildable = "";
			for (int i=0; i<3; i++) {
				buildable += " "+ getBuildables.get(i);
			}
			
			constructible.setText(buildable);

			buildable = "";
			for (int i=3; i<getBuildables.size(); i++) {
				buildable += " "+ getBuildables.get(i);
			}
			constructible2.setText(buildable);
		}
		else {
			buildable = "";
			for (int i=0; i<getBuildables.size(); i++) {
				buildable += " "+ getBuildables.get(i);
			}
			
			constructible.setText(buildable);
			constructible2.setText("");
		}
		
		this.repaint();
	}
	
	private void inventoryIcons(int gridy) {
		Dimension preferredSize = new Dimension(55, 55);
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.gridy = gridy;
		
		for(int i=0; i<5; i++) {
			gridBagConstraints.gridx = i;
			boutonSac.add(new JButton());
			boutonSac.get(i).setIcon(icones.get(i));
			boutonSac.get(i).setPreferredSize(preferredSize);
			boutonSac.get(i).addActionListener(parent);
			this.add(boutonSac.get(i), gridBagConstraints);
		}
	}
	
	private void cabinIcons(int gridy) {
		Dimension preferredSize = new Dimension(55, 55);
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.gridy = gridy;
		
		for(int i=0; i<5; i++) {
			gridBagConstraints.gridx = i;
			boutonCabane.add(new JButton());
			boutonCabane.get(i).setIcon(icones.get(i));
			boutonCabane.get(i).setPreferredSize(preferredSize);
			boutonCabane.get(i).addActionListener(parent);
			this.add(boutonCabane.get(i), gridBagConstraints);
		}
	}
	
	private void displayInventoryNumbers(int gridy) {
		Dimension preferredSize = new Dimension(35, 35);
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
		gridBagConstraints.gridy = gridy+1;
		
		for(int i=0; i<5; i++) {
			gridBagConstraints.gridx = i;
			nbRessourceSac.add(new JTextPane());
			nbRessourceSac.get(i).setFont(font);
			nbRessourceSac.get(i).setPreferredSize(preferredSize);
			this.add(nbRessourceSac.get(i), gridBagConstraints);
		}
	}
	
	private void displayCabinNumbers(int gridy) {
		Dimension preferredSize = new Dimension(35, 35);
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
		gridBagConstraints.gridy = gridy+1;
		
		for(int i=0; i<5; i++) {
			gridBagConstraints.gridx = i;
			nbRessourceCabane.add(new JTextPane());
			nbRessourceCabane.get(i).setFont(font);
			nbRessourceCabane.get(i).setPreferredSize(preferredSize);
			this.add(nbRessourceCabane.get(i), gridBagConstraints);
		}
	}
	
	//0-bois 1-eau 2-pierre 3-liane 4-nourriture
	
	public ArrayList<JButton> boutonSac() {
		return boutonSac;
	}
	
	public ArrayList<JButton> boutonCabane() {
		return boutonCabane;
	}
	
	public String nbRessourceSac(int i) {
		return String.valueOf(joueur.getSac().getQuantity(Objets.atValue(i).toTypeRessource()));
	}
	
	public String nbRessourceCabane(int i) {
		return String.valueOf(joueur.getCabane().getStock().getQuantity(Objets.atValue(i).toTypeRessource()));
	}
}
