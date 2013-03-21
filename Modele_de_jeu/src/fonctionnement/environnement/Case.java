package fonctionnement.environnement;

import java.util.ArrayList;
import fonctionnement.objet.Objet;

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
	
	
}