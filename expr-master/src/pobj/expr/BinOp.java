package pobj.expr;

public abstract class BinOp {
	protected final Expression left;
	protected final Expression right;
	
	public BinOp(Expression left, Expression right) {
		this.left=left;
		this.right=right;
	}
	
	public Expression getLeft() {
		return left;
	}
	
	public Expression getRight() {
		return right;
	}
	
	public abstract String toString();
	
}
