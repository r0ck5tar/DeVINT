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
		super();
		Preferences pref = Preferences.getData();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(110, 110));
		setOpaque(false);
		setBorderPainted(false);
		setContentAreaFilled(false);
	}
}
