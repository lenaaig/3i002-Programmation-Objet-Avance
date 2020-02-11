package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.List;

public class GrillePlaces {
	private Grille g;
	private List<Emplacement> places;
	private int nbHorizon;
	private int nbVerticaux;
	public GrillePlaces(Grille grille) {
		g = grille;
		places = new ArrayList<>();
		for(int i = 0; i<g.nbLig(); i++) {
			cherchePlaces(getLig(i));
		}
		nbHorizon=places.size();
		for(int j = 0; j<g.nbCol(); j++) {
			cherchePlaces(getCol(j));
		}
		nbVerticaux=places.size()-nbHorizon;
		
	}
		
	public List<Emplacement> getPlaces(){
		return places;
	}
	
	public Grille getGrille() {
		return g;
	}
	
	public int getNbHorizontal() {
		return nbHorizon;
	}
	public int getNbVerticaux() {
		return nbVerticaux;
	}
	
	private List<Case> getLig(int lig){
		List<Case> res = new ArrayList<>();
		for(int i = 0; i < g.nbCol(); i++) {
			res.add(g.getCase(lig, i));
		}
		return res;
	}

	private List<Case> getCol(int col){
		List<Case> res = new ArrayList<>();
		for(int i = 0; i < g.nbLig(); i++) {
			res.add(g.getCase(i, col));
		}
		return res;
	}
	
	private void cherchePlaces(List<Case> cases) {
		Emplacement buff = new Emplacement();
		for (int i=0; i<cases.size(); i++) {
			if(!cases.get(i).isPleine()) {
				buff.ajouterLettre(cases.get(i));
			}else {
				if(buff.size() > 1) {
					places.add(buff);
					buff =  new Emplacement();
				}else 
					buff = new Emplacement();	
			}
		}
		if(buff.size() > 1) {
			places.add(buff);
		}
		
	}
	
	public String toString() {
		String s="";
		for (int i=0; i<g.nbLig(); i++) {
			for (int j=0; j<g.nbCol(); j++) {
				System.out.print(g.getCase(i,j).getChar());
			}
			System.out.println("");

		}
		return s;
	}
	
	
	public GrillePlaces fixer(int m, String soluce) {
		Grille grille2=g.copy();
		GrillePlaces gp2=new GrillePlaces(grille2);
		Emplacement emp2 = gp2.getPlaces().get(m);
		//boucle pour setchar() pour case
		for (int i=0; i<emp2.size(); i++)
			emp2.getLettres().get(i).setChar(soluce.charAt(i));
		return gp2;
	}
	
	
}
