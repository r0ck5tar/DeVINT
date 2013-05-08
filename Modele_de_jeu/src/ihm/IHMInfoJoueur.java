package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import devintAPI.Preferences;
import fonctionnement.objet.Joueur;
import fonctionnement.objet.Objet;


public class IHMInfoJoueur extends JTextArea{
	private Joueur joueur;
	
	private JTextPane inventaire = new JTextPane();
	private JTextPane dansLaCabane = new JTextPane();
	private JTextPane nbBois = new JTextPane();
	private JTextPane nbEau = new JTextPane();
	private JTextPane nbLiane = new JTextPane();
	private JTextPane nbPierre = new JTextPane();
	private JTextPane nbNourriture = new JTextPane();
	
	private JTextPane nbBoisCabane = new JTextPane();
	private JTextPane nbEauCabane = new JTextPane();
	private JTextPane nbLianeCabane = new JTextPane();
	private JTextPane nbPierreCabane = new JTextPane();
	private JTextPane nbNourritureCabane = new JTextPane();
	
	public IHMInfoJoueur(Joueur joueur) {
		super(joueur.getNom());
		this.joueur = joueur;

		Preferences pref = Preferences.getData();
		setLayout(new GridBagLayout());
		
		Font font = new Font("Tahoma", 1, 28);
		setPreferredSize(new Dimension(320, 410));
		setBackground(pref.getCurrentBackgroundColor());
		setForeground(pref.getCurrentForegroundColor());
		setFont(font);

		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setOpaque(true);
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();


		inventaire.setFont(font);
		inventaire.setText("Inventaire (0/4)");
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 5;
		this.add(inventaire, gridBagConstraints);
		
		inventoryIcons(1);
		displayInventoryNumbers(1);
		
		dansLaCabane.setFont(font);
		dansLaCabane.setText("Dans la cabane (0/4)");
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.gridwidth = 5;
		this.add(dansLaCabane, gridBagConstraints);
		
		
		updateDisplay();
		inventoryIcons(4);
		displayCabinNumbers(4);
	}
	
	public void updateDisplay() {
		
		int bois =0, eau =0, liane =0, pierre = 0, nourriture = 0, lance = 0, boussole = 0, catapulte = 0, sac = 0;
		int boisC =0, eauC =0, lianeC =0, pierreC = 0, nourritureC = 0, lanceC = 0, boussoleC = 0, catapulteC = 0, sacC = 0;
		
		for(Objet objet : joueur.getSac().getStock()) {
			switch(objet.toString()) {
			case "eau" : eau++; break;
			case "bois" : bois++; break;
			case "pierre" : pierre++; break;
			case "nourriture" : nourriture++; break;
			case "liane" : liane++; break;
			case "lance" : lance++; break;
			case "boussole" : boussole++; break;
			case "catapulte" : catapulte++; break;
			case "sac" : sac++; break;
			}
		}
		
		nbBois.setText(" "+String.valueOf(bois));
		nbEau.setText(" "+String.valueOf(eau));
		nbLiane.setText(" "+String.valueOf(liane));
		nbPierre.setText(" "+String.valueOf(pierre));
		nbNourriture.setText(" "+String.valueOf(nourriture));
		
		for(Objet objet : joueur.getCabane().getStock().getStock()) {
			switch(objet.toString()) {
			case "eau" : eauC++; break;
			case "bois" : boisC++; break;
			case "pierre" : pierreC++; break;
			case "nourriture" : nourritureC++; break;
			case "liane" : lianeC++; break;
			case "lance" : lanceC++; break;
			case "boussole" : boussoleC++; break;
			case "catapulte" : catapulteC++; break;
			case "sac" : sacC++; break;
			}
		}
		
		nbBoisCabane.setText(" "+String.valueOf(boisC));
		nbEauCabane.setText(" "+String.valueOf(eauC));
		nbLianeCabane.setText(" "+String.valueOf(lianeC));
		nbPierreCabane.setText(" "+String.valueOf(pierreC));
		nbNourritureCabane.setText(" "+String.valueOf(nourritureC));
	}
	
	private void inventoryIcons(int gridy) {
		Font font = new Font("Tahoma", 1, 28);
		Dimension preferredSize = new Dimension(55, 55);
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = gridBagConstraints.NORTHWEST;
		gridBagConstraints.gridwidth = 1;
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = gridy;
		this.add(new JLabel(new ImageIcon("../ressources/images//icons/bois.JPG"), JLabel.CENTER), gridBagConstraints);
		
	
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = gridy;
		this.add(new JLabel(new ImageIcon("../ressources/images//icons/eau.JPG"), JLabel.CENTER), gridBagConstraints);
		
		
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = gridy;
		this.add(new JLabel(new ImageIcon("../ressources/images//icons/pierre.JPG"), JLabel.CENTER), gridBagConstraints);
		
		
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = gridy;
		this.add(new JLabel(new ImageIcon("../ressources/images//icons/liane.JPG"), JLabel.CENTER), gridBagConstraints);
		

		gridBagConstraints.gridx = 4;
		gridBagConstraints.gridy = gridy;
		this.add(new JLabel(new ImageIcon("../ressources/images//icons/nourriture.JPG"), JLabel.CENTER), gridBagConstraints);
		
		
	}
	
	private void displayInventoryNumbers(int gridy) {
		Font font = new Font("Tahoma", 1, 28);
		Dimension preferredSize = new Dimension(55, 55);
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = gridBagConstraints.NORTHWEST;
		
		nbBois.setFont(font);
		nbBois.setPreferredSize(preferredSize);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = gridy+1;
		this.add(nbBois, gridBagConstraints);
		
		nbEau.setFont(font);
		nbEau.setPreferredSize(preferredSize);
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = gridy+1;
		this.add(nbEau, gridBagConstraints);
		
		nbPierre.setFont(font);
		nbPierre.setPreferredSize(preferredSize);
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = gridy+1;
		this.add(nbPierre, gridBagConstraints);
		
		nbLiane.setFont(font);
		nbLiane.setPreferredSize(preferredSize);
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = gridy+1;
		this.add(nbLiane, gridBagConstraints);
		
		nbNourriture.setFont(font);
		nbNourriture.setPreferredSize(preferredSize);
		gridBagConstraints.gridx = 4;
		gridBagConstraints.gridy = gridy+1;
		this.add(nbNourriture, gridBagConstraints);
	}
	
	private void displayCabinNumbers(int gridy) {
		Font font = new Font("Tahoma", 1, 28);
		Dimension preferredSize = new Dimension(55, 55);
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = gridBagConstraints.NORTHWEST;
		
		nbBoisCabane.setFont(font);
		nbBoisCabane.setPreferredSize(preferredSize);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = gridy+1;
		this.add(nbBoisCabane, gridBagConstraints);
		
		nbEauCabane.setFont(font);
		nbEauCabane.setPreferredSize(preferredSize);
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = gridy+1;
		this.add(nbEauCabane, gridBagConstraints);
		
		nbPierreCabane.setFont(font);
		nbPierreCabane.setPreferredSize(preferredSize);
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = gridy+1;
		this.add(nbPierreCabane, gridBagConstraints);
		
		nbLianeCabane.setFont(font);
		nbLianeCabane.setPreferredSize(preferredSize);
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = gridy+1;
		this.add(nbLianeCabane, gridBagConstraints);
		
		nbNourritureCabane.setFont(font);
		nbNourritureCabane.setPreferredSize(preferredSize);
		gridBagConstraints.gridx = 4;
		gridBagConstraints.gridy = gridy+1;
		this.add(nbNourritureCabane, gridBagConstraints);
	}
}
