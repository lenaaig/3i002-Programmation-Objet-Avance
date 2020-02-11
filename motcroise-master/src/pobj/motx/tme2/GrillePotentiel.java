package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.Case;
import pobj.motx.tme1.Emplacement;
import pobj.motx.tme1.GrillePlaces;

public class GrillePotentiel {

	private GrillePlaces grille;
	private Dictionnaire dico;
	private List<Dictionnaire> motsPot;
	
	private List<IContrainte> contraintes;
	
	public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet) {
		this.grille=grille;
		dico=dicoComplet;
		motsPot=new ArrayList<>();
		contraintes=new ArrayList<>();
		Dictionnaire dicocopie;
		
		for (Emplacement emp : grille.getPlaces()) {
			dicocopie = dico.copy();
			dicocopie.filtreLongueur(emp.size());
			//boucle pour filtrer par lettre
			for(int i = 0; i < emp.getLettres().size(); i++) {
				if(emp.getLettres().get(i).getChar() != '*' &&emp.getLettres().get(i).getChar() != ' ') {
					dicocopie.filtreParLettre(emp.getLettres().get(i).getChar(), i);
				}
			}
			motsPot.add(dicocopie);
		}
		
		for (int i=0; i<grille.getNbHorizontal(); i++) {
			for (int j=grille.getNbHorizontal(); j<grille.getPlaces().size(); j++) {

				int t1 = grille.getPlaces().get(i).getLettres().size();
				int t2 = grille.getPlaces().get(j).getLettres().size();
				
				for (int k=0; k<t1; k++) {
					for (int l=0; l<t2; l++) {
						
						Emplacement e1 = grille.getPlaces().get(i);
						Case c1= e1.getLettres().get(k);
						Case c2 = grille.getPlaces().get(j).getLettres().get(l);
						
						
						if (c1.equals(c2) && c1.isVide()) {
							contraintes.add(new CroixContrainte(i,k,j,l));
						}
					}
				}
			}
		}
		/*
		for (int i=0; i<contraintes.size(); i++) 
			contraintes.get(i).reduce(this);
		*/
		propage();
	}
	
	
	//TME3
	public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet, List<Dictionnaire> potentiel) {
		this.grille=grille;
		dico=dicoComplet;
		this.motsPot=new ArrayList<>();
		contraintes=new ArrayList<>();
		
		for (Emplacement emp : grille.getPlaces()) {
			Dictionnaire dp = potentiel.get(grille.getPlaces().indexOf(emp)).copy();
			//boucle pour filtrer par lettre
			for(int i = 0; i < emp.getLettres().size(); i++) {
				if(emp.getLettres().get(i).getChar() != '*' &&emp.getLettres().get(i).getChar() != ' ') {
					dp.filtreParLettre(emp.getLettres().get(i).getChar(), i);
				}
			}
			motsPot.add(dp);
		}
		
		for (int i=0; i<grille.getNbHorizontal(); i++) {
			for (int j=grille.getNbHorizontal(); j<grille.getPlaces().size(); j++) {

				int t1 = grille.getPlaces().get(i).getLettres().size();
				int t2 = grille.getPlaces().get(j).getLettres().size();
				
				for (int k=0; k<t1; k++) {
					for (int l=0; l<t2; l++) {
						
						Emplacement e1 = grille.getPlaces().get(i);
						Case c1= e1.getLettres().get(k);
						Case c2 = grille.getPlaces().get(j).getLettres().get(l);
						
						
						if (c1.equals(c2) && c1.isVide()) {
							contraintes.add(new CroixContrainte(i,k,j,l));
						}
					}
				}
			}
		}
		
		propage();
	}

	
	public List<Dictionnaire> getMotsPot() {
		return motsPot;
	}
	
	public boolean isDead() {
		for (int i=0; i<motsPot.size(); i++) {
			if (motsPot.get(i).size()==0) return true;
		}
		return false;
	}
	
	public GrillePotentiel fixer(int m, String soluce) {
		//TME2 :
		//return new GrillePotentiel(grille.fixer(m, soluce), dico);
		
		//TME3 :
		List<Dictionnaire> listD = motsPot;
		return new GrillePotentiel(grille.fixer(m, soluce), dico, listD);

	}
	
	
	private boolean propage() {
		int cpt;
		while(true) {
			cpt =0;
			for (int i=0; i<contraintes.size(); i++) {
				cpt+=contraintes.get(i).reduce(this);
			}
			if (this.isDead()) return false;
			if (cpt==0) return true;
		}
	}
	

	public List<IContrainte> getContraintes() {
		return contraintes;
	}
	
	public GrillePlaces getGrillePlaces() {
		return grille;
	}
	
}