package pobj.expr;

public class Var implements Expression{
	private final String nom;
	
	public Var(String nom) {
		this.nom=nom;
	}
	
	public String getName() {
		return nom;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Var other = (Var) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return nom;
	}


	@Override
	public <T> T accept(IVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public int eval() {
		throw new UnsupportedOperationException(); 
	}
}
