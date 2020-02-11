package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.List;

public class Emplacement{
	
	private List<Case> lettres;
	
	public Emplacement(){
		this.lettres= new ArrayList<Case>();
	}
	
	public List<Case> getLettres(){
		return lettres;
	}
	
	public String toString() {
		String s="";
		for(int i=0; i<lettres.size(); i++) {
			s+=lettres.get(i).getChar();
		}
		return s;
	}
	
	public int size() {
		return lettres.size();
	}


	public void ajouterLettre(Case case1) {
		 lettres.add(case1)	;
	}


	
}