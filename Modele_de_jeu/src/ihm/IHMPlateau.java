package ihm;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import fonctionnement.objet.Joueur;
import fonctionnement.environnement.*;
import fonctionnement.objet.Cabane;



@SuppressWarnings("serial")
public class IHMPlateau extends JPanel{
	private IHMCase[] cases;
	private IHMCabane[] cabanes;
	private ArrayList<Case> listCases;
	private ArrayList<Cabane> listCabanes;
	private ArrayList<Integer> indiceCaseAccessibles;
	private Map<Joueur, Color> couleursJoueurs;
 	private ActionListener parent; 
	private Plateau plateauJeu;
	
	private Image backgroundImage;
	
	IHMPlateau(Plateau plateauJeu, ArrayList<Cabane> cabanes, Map<Joueur, Color> couleursJoueurs, ActionListener parent) {
		setLayout(new GridBagLayout());
		try {
			backgroundImage = ImageIO.read(new File("../ressources/images/plateau.JPG"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.parent = parent;
		this.plateauJeu = plateauJeu;
		this.couleursJoueurs = couleursJoueurs;
		GridBagConstraints casePos = new GridBagConstraints();
		GridBagConstraints cabanePos = new GridBagConstraints();
		indiceCaseAccessibles = new ArrayList<Integer>();
		
		cabanePos.fill = GridBagConstraints.BOTH;
		cabanePos.gridwidth = 3;
		cabanePos.gridheight = 3;
		casePos.fill = GridBagConstraints.BOTH;
		
		cases = new IHMCase[57];
		this.cabanes = new IHMCabane[4];
		
		listCases = new ArrayList<Case>();
		listCabanes = cabanes;
		
		initialiseCabanes(cabanePos);
		initialiseCases(casePos);
	}
	
	public IHMCase getCase(int i) {
		return cases[i];
	}
	
	public int getIndexOfCase(Case uneCase) {
		return listCases.indexOf(uneCase);
	}

	public IHMCase getCaseAtIndex(Case uneCase) {
		return cases[listCases.indexOf(uneCase)];
	}
	
	public IHMCabane getCabane(int i) {
		return cabanes[i];
	}
	
	public ArrayList<Integer> getIndiceCaseAccessibles() {
		return indiceCaseAccessibles;
	}

	public void afficheChoixDeplacement(ArrayList<Case> caseAccessibles) {
		for (Case uneCase : caseAccessibles) {
			getCaseAtIndex(uneCase).setCouleurCaseAccessible();
			indiceCaseAccessibles.add(listCases.indexOf(uneCase));
		}
	}
	
	//Remettre aux couleurs normales touts les cases aux indiceCaseAccessible sauf la caseAIgnorer
	public void masquerChoixDeplacement(int caseAIgnorer) {
		for (int indice: indiceCaseAccessibles) {
			if (indice != caseAIgnorer) {
				cases[indice].setCouleurCaseNormale();
			}
		}
		indiceCaseAccessibles.clear();
	}
	
	private void initialiseCabanes(GridBagConstraints cabanePos) {
		
		for(int i=0; i<4; i+=2) {
			cabanes[i] = new IHMCabane(listCabanes.get(i), "horizontal", couleursJoueurs.get(listCabanes.get(i).getJoueur()), parent);
			cabanes[i+1] = new IHMCabane(listCabanes.get(i+1), "vertical", couleursJoueurs.get(listCabanes.get(i+1).getJoueur()), parent);
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
	
	private void initialiseCases(GridBagConstraints casePos){
		/*
		 * Emplacement des cases
		 */
		
		listCases = plateauJeu.getCases();
		
		
		//Bottom Row
		for(int i=0; i<11; i++) {
			cases[i] = new IHMCase(listCases.get(i), parent);
			casePos.fill = GridBagConstraints.HORIZONTAL;
			casePos.gridx = i+3;
			casePos.gridy = 13;
			this.add(cases[i], casePos);
		}

				
		//Top row
		for(int i=11; i<22; i++) {
			cases[i] = new IHMCase(listCases.get(i), parent);
			casePos.gridx = i-8;
			casePos.gridy = 3;
			this.add(cases[i], casePos);
		}

		
		//Left Column
		for(int i=30, y=26; i>21; i--, y-=2) {
			cases[i]= new IHMCase(listCases.get(i), parent);
			casePos.gridx = 3;
			casePos.gridy = i-y;
			this.add(cases[i], casePos);
		}
		
		
		//Right Column
		for(int i=39, y=35; i>30; i--, y-=2) {
			cases[i] = new IHMCase(listCases.get(i), parent);
			casePos.gridx = 13;
			casePos.gridy = i-y;
			this.add(cases[i], casePos);
		}
		
		
		//Middle Column
		for(int i=48, y=44; i>39; i--, y-=2) {
			cases[i] = new IHMCase(listCases.get(i), parent);
			casePos.gridx = 8;
			casePos.gridy = i-y;
			this.add(cases[i], casePos);
		}
		
		
		//Middle Left-half Row
		for(int i=49; i<53; i++) {
			cases[i] = new IHMCase(listCases.get(i), parent);
			casePos.gridx = i-45;
			casePos.gridy = 8;
			this.add(cases[i], casePos);
		}
		
		//Middle Right-half Row
		for(int i=53; i<57; i++) {
			cases[i] = new IHMCase(listCases.get(i), parent);
			casePos.gridx = i-44;
			casePos.gridy = 8;
			this.add(cases[i], casePos);
		}
	}
	
    @Override
    public void paint(Graphics g) {
    	super.paint(g);
    	g.drawImage(backgroundImage, 0, 0, null);
    	this.printComponents(g);
    }
}
