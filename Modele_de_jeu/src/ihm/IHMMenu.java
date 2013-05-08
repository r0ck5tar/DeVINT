package ihm;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import t2s.SIVOXDevint;

public class IHMMenu {
	private JButton boutonDe;
	private JButton boutonTrue;
	private JButton boutonFalse;
	private JDialog menuDe;
	private JDialog menu;
	private SIVOXDevint voix;
	private JFrame parent;
	
	public IHMMenu(SIVOXDevint voix, JFrame parent) {
		this.voix = voix;
		this.parent = parent;
	}

	public void lancerDe(int qui) {
		voix.playShortText("joueur" + (qui + 1) + ", lance le dé");
		menuDe = new JDialog(parent, "lancer le dé");
		menuDe.setLayout(new BorderLayout());
		menuDe.setSize(200, 100);
		switch (qui) {
		case 0:
			menuDe.setLocation(570, 220);
			break;
		case 1:
			menuDe.setLocation(570, 620);
			break;
		case 2:
			menuDe.setLocation(870, 220);
			break;
		case 3:
			menuDe.setLocation(870, 620);
			break;
		}
		boutonDe = new JButton("lancer le dé");
		boutonDe.setVisible(true);
		boutonDe.addActionListener((ActionListener) parent);
		menuDe.add(boutonDe);
		menuDe.setVisible(true);
	}

	public JButton boutonDe() {
		return boutonDe;
	}

	public JDialog menuDe() {
		return menuDe;
	}
	
	
}
