package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;


@SuppressWarnings("serial")
public class IHMCase extends JButton{

<<<<<<< HEAD
	IHMCase(ActionListener parent) {
=======
	IHMCase() {
		
>>>>>>> Modfication plateau de jeu
		super("Case");
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(61, 61));
		setBackground(Color.BLACK);
		setForeground(Color.YELLOW);
		setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		setOpaque(true);
<<<<<<< HEAD
		this.addActionListener((ActionListener) parent);
=======
		
>>>>>>> Modfication plateau de jeu
	}
	
}
