package pobj.motx.tme2;

public class CroixContrainte implements IContrainte {
	private int m1, c1, m2, c2;
	
	public CroixContrainte(int m1, int c1, int m2, int c2) {
		this.m1=m1;
		this.c1=c1;
		this.m2=m2;
		this.c2=c2;
	}
	
	
	@Override
	public int reduce(GrillePotentiel grille) {
		EnsembleLettre l1 = grille.getMotsPot().get(m1).ensPossiblePositionDonnee(c1);
		EnsembleLettre l2 = grille.getMotsPot().get(m2).ensPossiblePositionDonnee(c2);
		
		EnsembleLettre s = l1.intersection(l2);
		
		int cpt=0;
		
		if (l1.size()>s.size()) {
			cpt+=grille.getMotsPot().get(m1).filtrerDico(c1, s);
		} else if (l2.size() > s.size()) {
			cpt+=grille.getMotsPot().get(m2).filtrerDico(c2, s);
		}
		return cpt;
	}
	
	
	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
        if (this.getClass() != other.getClass()) 
            return false;
        CroixContrainte obj = (CroixContrainte) other;
        if ((obj.m1 == this.m1) && (obj.c1 == this.c1) && (obj.m2 == this.m2) && (obj.c2 == this.c2))
            return true;
        else
            return false;
	}


	public int getM1() {
		return m1;
	}


	public int getC1() {
		return c1;
	}


	public int getM2() {
		return m2;
	}


	public int getC2() {
		return c2;
	}
	

	
}

