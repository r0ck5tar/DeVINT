package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import devintAPI.Preferences;

@SuppressWarnings("serial")
public class IHMCabane extends JButton {
	IHMCabane() {
		super("Cabane");
		Preferences pref = Preferences.getData();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(105, 105));
		setBackground(pref.getCurrentBackgroundColor());
		setForeground(pref.getCurrentForegroundColor());

		setFont(new Font("Tahoma", 1, 36));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		setOpaque(true);
	}
}
