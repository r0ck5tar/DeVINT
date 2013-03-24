package fonctionnement.environnement;

import java.util.ArrayList;

import fonctionnement.Tool.Tool;
import fonctionnement.objet.Objet;
import fonctionnement.objet.ObjetEffet;
import fonctionnement.objet.Ressource;

public class Case {
	private ArrayList<Objet> content;
	private Case nord;
	private Case sud;
	private Case ouest;
	private Case est;
	private int posX;
	private int posY;
	
	public Case(){
		this(-1,-1);
	}
	
	public Case(int x , int y){
		content = new ArrayList<Objet>();
		nord = null;
		sud = null;
		ouest = null;
		est = null;
		posX=x;
		posY=y;
	}

	public void setNord(Case c){
		this.nord = c;
	}
	
	public void setSud(Case c){
		this.sud = c;
	}
	
	public void setOuest(Case c){
		this.ouest = c;
	}
	
	public void setEst(Case c){
		this.est = c;
	}

	public Case getNord() { return this.nord;}
	public Case getSud() { return this.sud;}
	public Case getOuest() { return this.ouest;}
	public Case getEst() { return this.est;}
	
	
	public Objet getRessource(int positionDansArray){
		return this.content.get(positionDansArray);
	}
	
	public void setPosX(int x){
		this.posX = x;
	}
	
	public void setPosY(int y){
		this.posY = y;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}
	
	private boolean isPos(int x, int y){
		return (this.posX == x  && this.posY == y);
	}
	
	public void link(ArrayList<Case> cases){
		for (Case c: cases ){
			if(c.isPos(posX+1,posY)){
				this.setEst(c);
			}
			
			if(c.isPos(posX-1,posY)){
				this.setOuest(c);
			}
			
			if(c.isPos(posX,posY+1)){
				this.setNord(c);
			}
			
			if(c.isPos(posX,posY-1)){
				this.setSud(c);
			}
			
			
		}
	}
	
	public Objet recupererObjet() {
		Objet o = this.content.get(Tool.lancerDe(this.content.size()));
		if(o instanceof Ressource) {
			o = ((Ressource)o).copy();
		}
		else o = ((ObjetEffet)o).copy();
		return o;
	}

	public ArrayList<Case> getChoixCase(int de) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
