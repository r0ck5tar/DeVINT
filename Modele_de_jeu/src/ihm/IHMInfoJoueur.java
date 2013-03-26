package ihm;

<<<<<<< HEAD
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import devintAPI.Preferences;


=======

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import devintAPI.Preferences;


>>>>>>> f80616f3b7eb668c200c24d179732a01f7a0e3b6
public class IHMInfoJoueur extends JButton{
	
	public IHMInfoJoueur(ActionListener parent) {
		super("Info Joueur");
		Preferences pref = Preferences.getData();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(320, 410));
		setBackground(pref.getCurrentBackgroundColor());
		setForeground(pref.getCurrentForegroundColor());
<<<<<<< HEAD
		setBorder(BorderFactory.createLineBorder(Color.YELLOW));
=======
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
>>>>>>> f80616f3b7eb668c200c24d179732a01f7a0e3b6
		setOpaque(true);
		this.addActionListener((ActionListener) parent);
	}
}
