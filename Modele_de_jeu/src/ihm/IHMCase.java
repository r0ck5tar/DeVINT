package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import devintAPI.Preferences;


@SuppressWarnings("serial")
public class IHMCase extends JButton{

	IHMCase(ActionListener parent) {
		super("Case");
		Preferences pref = Preferences.getData();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(61, 61));
		setBackground(pref.getCurrentBackgroundColor());
		setForeground(pref.getCurrentForegroundColor());
<<<<<<< HEAD
		setBorder(BorderFactory.createLineBorder(Color.YELLOW));
=======
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
>>>>>>> origin/HakimIHM
		setOpaque(true);
		this.addActionListener((ActionListener) parent);
	}
	
	
}
