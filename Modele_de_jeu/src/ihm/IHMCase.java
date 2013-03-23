package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;


@SuppressWarnings("serial")
public class IHMCase extends JButton{

	IHMCase() {
		super("Case");
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(61, 61));
		setBackground(Color.BLACK);
		setForeground(Color.YELLOW);
		setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		setOpaque(true);
	}
}
