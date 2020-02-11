package pobj.motx.tme3.csp;

public class StratFirst implements IChoixVar {

	
	@Override
	public IVariable chooseVar(ICSP problem) {
		return problem.getVars().get(0);
	}

}
