package pobj.expr;

public class VisitorSimplify implements IVisitor<Expression>{

	@Override
	public Expression visit(Constant c) {
		return c;
	}

	@Override
	public Expression visit(Add e) {
		if(e.accept(new VisitorConstant())){
			return new Constant(e.accept(new VisitorEval()));
		}
		Expression exprL =e.getLeft().accept(this);
		Expression exprR =e.getRight().accept(this);
		Constant zero= new Constant();
		//Cas: 0 + e
		if(exprL.equals(zero))
			return exprR;
		//Cas: e + 0
		if(exprR.equals(zero))
			return exprL;
		return new Add(exprL, exprR);
	}

	@Override
	public Expression visit(Mult e) {
		if(e.accept(new VisitorConstant())){
			return new Constant(e.accept(new VisitorEval()));
		}
		Expression exprL =e.getLeft().accept(this);
		Expression exprR =e.getRight().accept(this);
		//Cas: e * 0
		if(exprL.equals(new Constant()) || exprR.equals(new Constant()))
			return new Constant();
		//Cas: e * 1
		if(exprL.equals(new Constant(1)))
			return exprR;
		if(exprR.equals(new Constant(1)))
			return exprL;
		
		return new Mult(exprL, exprR);
	}

	@Override
	public Expression visit(Var v) {
		return v;
	}

}
