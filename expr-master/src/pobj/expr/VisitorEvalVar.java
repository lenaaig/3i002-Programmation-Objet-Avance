package pobj.expr;

import java.util.Map;

public class VisitorEvalVar extends VisitorEval{
	
	private Map<String, Integer> m;
	
	public VisitorEvalVar(Map<String, Integer> m) {
		this.m=m;
	}
	
	@Override
	public Integer visit(Var v) {
		return m.get(v.getName());
	}
}
