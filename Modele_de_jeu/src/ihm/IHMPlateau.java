package ihm;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;



@SuppressWarnings("serial")
public class IHMPlateau extends JPanel{
	
	IHMPlateau() {
		setLayout(new BorderLayout());
		JPanel chemins = new JPanel();
		JPanel cabane1 = new JPanel();
		JPanel cabane2 = new JPanel();
		JPanel cabane3 = new JPanel();
		JPanel cabane4 = new JPanel();
		
		cabane1.setBackground(Color.ORANGE);
		cabane1.setOpaque(true);
		JLabel cabaneA = new JLabel("cabane");
		cabane1.setLayout(new FlowLayout(FlowLayout.CENTER));
		cabane1.add(cabaneA);
		
		cabane2.setBackground(Color.ORANGE);
		cabane2.setOpaque(true);
		cabane3.setBackground(Color.ORANGE);
		cabane3.setOpaque(true);
		cabane4.setBackground(Color.ORANGE);
		cabane4.setOpaque(true);
		this.add(cabane1, BorderLayout.NORTH);
		this.add(cabane2, BorderLayout.EAST);
		this.add(cabane3, BorderLayout.SOUTH);
		this.add(cabane4, BorderLayout.WEST);
		
		
		chemins.setLayout(new FlowLayout());
		
		ArrayList<IHMCase> cases = new ArrayList<IHMCase>();
    	for(int i=0; i<10; i++){
    		cases.add(new IHMCase());
    	}
    	
    	for(int i=0; i<cases.size(); i++){
    		chemins.add(cases.get(i));
    	}
    	
    	this.add(chemins, BorderLayout.CENTER);
    
		
		this.setBackground(Color.BLUE);
		this.setOpaque(true);
	}
}
