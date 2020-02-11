package pobj.motx.tme3.csp;

public class StratMin implements IChoixVar {

	@Override
	public IVariable chooseVar(ICSP problem) {
		//taille du domaine du premier problem
		int taille = problem.getVars().get(0).getDomain().size();
		int index = 0;
		for(IVariable i : problem.getVars()) {
			if(i.getDomain().size() < taille) {
				taille = i.getDomain().size();
				index = problem.getVars().indexOf(i);
			}
		}
		return problem.getVars().get(index);
	}

}
