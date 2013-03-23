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
		ArrayList<JPanel> bords = new ArrayList<JPanel>();
		ArrayList<IHMCabane> cabanes = new ArrayList<IHMCabane>();
		
		
		for(int i=0; i<4; i++){
			cabanes.add(new IHMCabane());
			bords.add(new JPanel());
			bords.get(i).setLayout(new FlowLayout(FlowLayout.CENTER));
			bords.get(i).add(cabanes.get(i));
		}
		
		
		chemins.setLayout(new FlowLayout());
		
		ArrayList<IHMCase> cases = new ArrayList<IHMCase>();
    	for(int i=0; i<11; i++){
    		cases.add(new IHMCase());
    	}
    	
    	for(int i=0; i<cases.size(); i++){
    		chemins.add(cases.get(i));
    	}
    	
    	this.add(chemins, BorderLayout.CENTER);
    	this.add(bords.get(0), BorderLayout.NORTH);
		this.add(bords.get(2), BorderLayout.SOUTH);
		this.add(bords.get(1), BorderLayout.EAST);
		this.add(bords.get(3), BorderLayout.WEST);
	}
}
