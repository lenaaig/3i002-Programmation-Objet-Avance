package pobj.expr;

public class VisitorDerive implements IVisitor<Expression>{
	private Var var;
	
	public VisitorDerive(Var var){
		this.var=var;
	}
	
	@Override
	public Expression visit(Constant c) {
		return new Constant();
	}

	@Override
	public Expression visit(Add e) {
		Expression exprL = e.getLeft().accept(this);
		Expression exprR = e.getRight().accept(this);
		return new Add(exprL, exprR);
	}

	@Override
	public Expression visit(Mult e) {
		Expression exprL = e.getLeft().accept(this);
		Expression exprR = e.getRight().accept(this);
		return new Add(new Mult(e.getLeft(), exprR), new Mult(exprL, e.getRight()));
	}

	@Override
	public Expression visit(Var v) {
		//Cas: (x)' = 1
		if(v.equals(var))
			return new Constant(1);
		//Cas: (y)' = 0
		return new Constant(0);
	}

}
