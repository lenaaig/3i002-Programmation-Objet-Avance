package pobj.expr;

public class VisitorEval implements IVisitor<Integer>{


	
	@Override
	public Integer visit(Constant c) {
		return c.getValue();
	}

	@Override
	public Integer visit(Add e) {
		return e.getLeft().accept(this)+e.getRight().accept(this);
	}

	@Override
	public Integer visit(Mult e) {
		return e.getLeft().accept(this)*e.getRight().accept(this);
	}

	@Override
	public Integer visit(Var v) {
		throw new UnsupportedOperationException();
	}

}
