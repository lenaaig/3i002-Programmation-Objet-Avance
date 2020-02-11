package pobj.motx.tme3.csp;

import java.util.List;

import pobj.motx.tme2.GrillePotentiel;

public class DicoVariable implements IVariable {
	
	private int index;
	private GrillePotentiel gp;
	
	public DicoVariable(int index, GrillePotentiel gp) {
		this.index=index;
		this.gp=gp;
	}
	@Override
	public List<String> getDomain() {
		return gp.getMotsPot().get(index).getMots();
	}
	
	public String toString() {
		return gp.getGrillePlaces().getGrille().toString();
	}
	public int getIndex() {
		// TODO Auto-generated method stub
		return index;
	}

}
