package ihm;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;


@SuppressWarnings("serial")
public class IHMCase extends JButton{

	IHMCase(int width, int height, int caseNumber) {
		super("Case");
		setBounds(40*caseNumber, 0, width, height);
		setBackground(Color.BLACK);
		setForeground(Color.YELLOW);
		setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		setOpaque(true);
	}
}
