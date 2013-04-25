package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import devintAPI.Preferences;
import fonctionnement.environnement.Case;


@SuppressWarnings("serial")
public class IHMCase extends JButton{

	IHMCase(Case uneCase, ActionListener parent) {
		super(uneCase.getNom());
		Preferences pref = Preferences.getData();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(61, 61));
		setBackground(pref.getCurrentBackgroundColor());
		setForeground(pref.getCurrentForegroundColor());
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setOpaque(true);
		this.addActionListener((ActionListener) parent);
	}
	
	public void setCouleurCaseAccessible() {
		setBackground(Color.CYAN);
	}
}
