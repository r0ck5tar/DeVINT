package ihm;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import devintAPI.Preferences;
import fonctionnement.objet.Joueur;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import devintAPI.Preferences;


public class IHMInfoJoueur extends JTextArea{
	private Joueur joueur;
	
	public IHMInfoJoueur(Joueur joueur) {
		this.joueur = joueur;
		updateDisplay();

		Preferences pref = Preferences.getData();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(320, 410));
		setBackground(pref.getCurrentBackgroundColor());
		setForeground(pref.getCurrentForegroundColor());
		setFont(new Font("Tahoma", 1, 30));

		setBorder(BorderFactory.createLineBorder(Color.YELLOW));

		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setOpaque(true);
	}
	
	public void updateDisplay() {
		this.setText(joueur.getNom());
		
		if(joueur.getSac().getStock().size() > 0) {
			this.append("\n\nInventaire :");
			
			for(int i=0; i<joueur.getSac().getStock().size(); i++) {
				this.append("\n" + joueur.getSac().getStock().get(i));
			}
		}
		
		if(joueur.getCabane().getStock().getStock().size() > 0) {
			this.append("\n\nDans la cabane :");
			
			for(int i=0; i<joueur.getCabane().getStock().getStock().size(); i++) {
				this.append("\n" + joueur.getCabane().getStock().getStock().get(i));
			}
		}
	}
}
