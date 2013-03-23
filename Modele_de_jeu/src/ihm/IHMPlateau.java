package ihm;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;



@SuppressWarnings("serial")
public class IHMPlateau extends JPanel{
	
	IHMPlateau() {
		setLayout(new FlowLayout());
		
		ArrayList<IHMCase> cases = new ArrayList<IHMCase>();
    	for(int i=0; i<10; i++){
    		cases.add(new IHMCase());
    	}
    	
    	for(int i=0; i<cases.size(); i++){
    		this.add(cases.get(i));
    	}
    	
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		Dimension maxSize = new Dimension (screenHeight, screenHeight); 
		
		this.setBounds((screenWidth-screenHeight)/2, 0, screenHeight, screenHeight);
		
		this.setMaximumSize(maxSize);
		this.setPreferredSize(maxSize);
		
		this.setBackground(Color.BLUE);
		this.setOpaque(true);
	}
}
