package ihm;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fonctionnement.environnement.*;

@SuppressWarnings("serial")
public class IHMPlateau extends JPanel{
	private IHMCase[] cases;
	private IHMCabane[] cabanes;
	private ActionListener parent; 
	private Plateau plateau;  //Pour pouvoir recuperer les informations sur chaque case du plateau de jeu
	
	IHMPlateau(ActionListener parent, Plateau plateau) {
		setLayout(new GridBagLayout());
		this.parent = parent;
		GridBagConstraints casePos = new GridBagConstraints();
		GridBagConstraints cabanePos = new GridBagConstraints();
		
		cabanePos.fill = GridBagConstraints.BOTH;
		cabanePos.gridwidth = 3;
		cabanePos.gridheight = 3;
		casePos.fill = GridBagConstraints.BOTH;
		
		cases = new IHMCase[57];
		cabanes = new IHMCabane[4];
		
		initialiseCabanes(cabanePos);
		initialiseCases(casePos,plateau);
	}
	
	public IHMCase getCase(int i) {
		return cases[i];
	}
	
	public IHMCabane getCabane(int i) {
		return cabanes[i];
	}
	
	private void initialiseCabanes(GridBagConstraints cabanePos) {
		
		for(int i=0; i<4; i++) {
			cabanes[i] = new IHMCabane();
		}
		
		/*
		 * Emplacement des cabanes
		 */
		
		//Top cabane
		cabanePos.gridx = 7;
		cabanePos.gridy = 0;
		
		this.add(cabanes[0], cabanePos);
		
		//Right cabane
		cabanePos.gridx = 14;
		cabanePos.gridy = 7;
		
		this.add(cabanes[1], cabanePos);
		
		//Bottom cabane
		cabanePos.gridx = 7;
		cabanePos.gridy = 14;
		this.add(cabanes[2], cabanePos);
		
		//Left cabane
		cabanePos.gridx = 0;
		cabanePos.gridy = 7;
		this.add(cabanes[3], cabanePos);
	}
	
	private void initialiseCases(GridBagConstraints casePos, Plateau plateau){
		/*
		 * Emplacement des cases
		 */
		
		//Top row
		for(int i=0; i<11; i++) {
			cases[i] = new IHMCase(parent,plateau.getCases().get(12+i).getRessource(1));
			casePos.gridx = i+3;
			casePos.gridy = 3;
			this.add(cases[i], casePos);
		}
		
		//Right Column
		for(int i=11; i<20; i++) {
			cases[i] = new IHMCase(parent, plateau.getCases().get(32+i).getRessource(1));
			casePos.gridx = 13;
			casePos.gridy = i-7;
			this.add(cases[i], casePos);
		}
		
		//Bottom Row
		for(int i=20; i<31; i++) {
			cases[i] = new IHMCase(parent, plateau.getCases().get(41+i).getRessource(1)); //NON FAIT
			casePos.fill = GridBagConstraints.HORIZONTAL;
			casePos.gridx = 33-i;
			casePos.gridy = 13;
			this.add(cases[i], casePos);
		}
		
		//Left Column
		for(int i=31; i<40; i++) {
			cases[i]= new IHMCase(parent, plateau.getCases().get(23+i).getRessource(1));
			casePos.gridx = 3;
			casePos.gridy = 43-i;
			this.add(cases[i], casePos);
		}
		
		//Middle Column
		for(int i=40; i<49; i++) {
			cases[i] = new IHMCase(parent, plateau.getCases().get(41+i).getRessource(1));
			casePos.gridx = 8;
			casePos.gridy = i-36;
			this.add(cases[i], casePos);
		}
		
		//Middle Left-half Row
		for(int i=49; i<53; i++) {
			cases[i] = new IHMCase(parent, plateau.getCases().get(41+i).getRessource(1));//NON FAIT
			casePos.gridx = i-45;
			casePos.gridy = 8;
			this.add(cases[i], casePos);
		}
		
		//Middle Right-half Row
		for(int i=53; i<57; i++) {
			cases[i] = new IHMCase(parent,plateau.getCases().get(41+i).getRessource(1)); //NON FAIT
			casePos.gridx = i-44;
			casePos.gridy = 8;
			this.add(cases[i], casePos);
		}
	}
}
