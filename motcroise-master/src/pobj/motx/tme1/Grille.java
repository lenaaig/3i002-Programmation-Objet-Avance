package pobj.motx.tme1;


public class Grille {
	private Case[][] matrice;
	private int hauteur;
	private int largeur;
	public Grille(int hauteur, int largeur) {
		this.hauteur=hauteur;
		this.largeur=largeur;
		matrice=new Case[hauteur][largeur];
		for (int i=0; i<hauteur; i++) {
			for (int j=0; j<largeur; j++) {
				matrice[i][j]=new Case(i, j,' ');
			}
		}
	}
	
	public Case getCase(int lig, int col) {
		if (matrice[lig][col].getCol() ==col && matrice[lig][col].getLig()==lig)
			return matrice[lig][col];
		return null;
	}
	
	public String toString() {
		return GrilleLoader.serialize(this,false);
	}
	
	public int nbLig() {
		return hauteur;
	}
	
	public int nbCol() {
		return largeur;
	}
	
	public Grille copy() {
		Grille copie=new Grille(hauteur, largeur);
		for (int i=0; i<hauteur; i++) {
			for (int j=0; j<largeur; j++) {
				copie.matrice[i][j]=new Case(i,j,matrice[i][j].getChar());
			}
		}
		return copie;
	}
	
	
}