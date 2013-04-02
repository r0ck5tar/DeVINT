package fonctionnement.environnement;

import java.util.ArrayList;

public class Plateau {

	private ArrayList<Case> cases;

	public Plateau() {
		this.cases = new ArrayList<Case>();
		createPlateau();
	}

	
	public void createPlateau() {
		// create the first part of the plateau : lower row
		for (int i = 0; i < 11; i++) {
			cases.add(new Case(i, 0));
		}

		// 2nd part : higher row
		for (int i = 0; i < 11; i++) {
			cases.add(new Case(i, 10));
		}

		// 3rd part : left column
		for (int i = 1; i < 10; i++) {
			cases.add(new Case(0, i));
		}

		// 4th part : right column
		for (int i = 1; i < 10; i++) {
			cases.add(new Case(10, i));
		}

		// 5th part : middle column
		for (int i = 1; i < 10; i++) {
			cases.add(new Case(5, i));
		}

		// 6th part : left to the middle column
		for (int i = 1; i < 5; i++) {
			cases.add(new Case(i, 5));
		}

		// 7th part : right to the middle column
		for (int i = 6; i < 10; i++) {
			cases.add(new Case(i, 5));
		}
		
		linkAllCell();
	}
	
	private void linkAllCell() {
		for(Case c : cases) c.link(cases);
		this.initAllCase();
	}
	
	private void initAllCase() {
		String initer = "EBFBNHNBNPNEBEFEHPEPNPBBPBHEPENFNENHPBPFBECFEFCPBBBCFFCNB";
		for(Case c : cases) {
			c.init(initer.charAt(0));
			initer = initer.substring(1,initer.length()-1);
		}
		
	}


	public ArrayList<Case> getCases(){
		return cases;
	}
	
	public Case getCase(int i) {
		return cases.get(i);
	}

	public void printCases(){
		int i = 1;
		for (Case c : cases) {
			System.out.println("("+c.getPosX()+", " +c.getPosY()+")  " + i);
			i++;
		}
	}
}
