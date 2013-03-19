package Environnement;

import java.util.ArrayList;
import Objet.Objet;

public class Case {
	private ArrayList<Objet> content;
	private Case nord;
	private Case sud;
	private Case ouest;
	private Case est;
	private int posX;
	private int posY;
	
	public Case(){
		content = new ArrayList<Objet>();
		nord = null;
		sud = null;
		ouest = null;
		est = null;
		posX = 0;
		posY= 0;
	}
	
	public Case(int x , int y){
		this();
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
	
	
	
}
