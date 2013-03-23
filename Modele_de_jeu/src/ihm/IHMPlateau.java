package ihm;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;



@SuppressWarnings("serial")
public class IHMPlateau extends JPanel{
	
	IHMPlateau() {
		setLayout(new GridBagLayout());
		GridBagConstraints casePos = new GridBagConstraints();
		GridBagConstraints cabanePos = new GridBagConstraints();
		
		ArrayList<IHMCase> cases = new ArrayList<IHMCase>();
		ArrayList<IHMCabane> cabanes = new ArrayList<IHMCabane>();
		
		
		for(int i=0; i<4; i++) {
			cabanes.add(new IHMCabane());
		}
		
		cabanePos.fill = GridBagConstraints.BOTH;
		cabanePos.gridwidth = 3;
		cabanePos.gridheight = 3;
		casePos.fill = GridBagConstraints.BOTH;
		
		/*
		 * Emplacement des cabanes
		 */
		
		//Top cabane
		cabanePos.gridx = 7;
		cabanePos.gridy = 0;
		
		this.add(cabanes.get(0), cabanePos);
		
		//Right cabane
		cabanePos.gridx = 14;
		cabanePos.gridy = 7;
		
		this.add(cabanes.get(1), cabanePos);
		
		//Bottom cabane
		cabanePos.gridx = 7;
		cabanePos.gridy = 14;
		this.add(cabanes.get(2), cabanePos);
		
		//Left cabane
		cabanePos.gridx = 0;
		cabanePos.gridy = 7;
		this.add(cabanes.get(3), cabanePos);
		
		/*
		 * Emplacement des cases
		 */
			
		//Top row
		for(int i=0; i<11; i++) {
			cases.add(new IHMCase());
			casePos.gridx = i+3;
			casePos.gridy = 3;
			this.add(cases.get(i), casePos);
		}
		
		//Right Column
		for(int i=0; i<9; i++) {
			cases.add(new IHMCase());
			casePos.gridx = 13;
			casePos.gridy = i+4;
			this.add(cases.get(i+11), casePos);
		}
		
		//Bottom Row
		for(int i=0; i<11; i++) {
			cases.add(new IHMCase());
			casePos.fill = GridBagConstraints.HORIZONTAL;
			casePos.gridx = i+3;
			casePos.gridy = 13;
			this.add(cases.get(i+20), casePos);
		}
		
		//Left Column
		for(int i=0; i<9; i++) {
			cases.add(new IHMCase());
			casePos.gridx = 3;
			casePos.gridy = i+4;
			this.add(cases.get(i+31), casePos);
		}
		
		//Middle Column
		for(int i=0; i<9; i++) {
			cases.add(new IHMCase());
			casePos.gridx = 8;
			casePos.gridy = i+4;
			this.add(cases.get(i+40), casePos);
		}
		
		//Middle Left-half Row
		for(int i=0; i<4; i++) {
			cases.add(new IHMCase());
			casePos.gridx = i+4;
			casePos.gridy = 8;
			this.add(cases.get(i+49), casePos);
		}
		
		//Middle Right-half Row
		for(int i=0; i<4; i++) {
			cases.add(new IHMCase());
			casePos.gridx = i+9;
			casePos.gridy = 8;
			this.add(cases.get(i+53), casePos);
		}
	}
}
