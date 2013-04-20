package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import devintAPI.Preferences;
import fonctionnement.environnement.Case;


@SuppressWarnings("serial")
public class IHMCase extends JButton{
	private Preferences pref;

	IHMCase(Case uneCase, ActionListener parent) {
		super(uneCase.getNom().substring(0, 1));
		pref = Preferences.getData();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(61, 61));
		setBackground(pref.getCurrentBackgroundColor());
		setForeground(Color.BLACK);
		setFont(new Font("Tahoma", 1, 56));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setOpaque(true);
		this.addActionListener((ActionListener) parent);		
	}
	
	public void setCouleurCaseAccessible() {
		setBackground(Color.CYAN);
	}
	
	public void setCouleurCaseNormale() {
		setBackground(pref.getCurrentBackgroundColor());
		setForeground(Color.BLACK);
	}
	
	public void setCouleurCaseJoueur(int qui) {
		setForeground(Color.BLACK);
		switch(qui) {
		case 0:
			setBackground(Color.BLUE);
			break;
		case 1:
			setBackground(Color.GREEN);
			break;
		case 2:
			setBackground(Color.MAGENTA);
			break;
		case 3:
			setBackground(Color.ORANGE);
			break;
		}
		
	}
}
