package pobj.expr;

public class Constant implements Expression {
	private int value;
	
	
	
	public Constant() {
		value=0;
	}

	public Constant(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return ""+value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Constant other = (Constant) obj;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public <T> T accept(IVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public int eval() {
		return value;
	}
	

	
	
}
