package ihm;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;



@SuppressWarnings("serial")
public class IHMPlateau extends JPanel{
	
	IHMPlateau() {
		setLayout(null);
		
		ArrayList<IHMCase> cases = new ArrayList<IHMCase>();
    	for(int i=0; i<10; i++){
    		cases.add(new IHMCase(40, 40, i));
    	}
    	
    	for(int i=0; i<cases.size(); i++){
    		this.add(cases.get(i));
    	}
    	
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		
		this.setBounds((screenWidth-screenHeight)/2, 0, screenHeight, screenHeight);
		this.setBackground(Color.BLUE);
		this.setOpaque(true);
	}
}
