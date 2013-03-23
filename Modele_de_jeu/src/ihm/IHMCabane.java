package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class IHMCabane extends JButton {
	IHMCabane() {
		super("Cabane");
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(105, 105));
		setBackground(Color.BLACK);
		setForeground(Color.ORANGE);
		setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		setOpaque(true);
	}
}
