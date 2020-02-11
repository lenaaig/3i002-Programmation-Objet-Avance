package pobj.motx.tme3.csp;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.Case;
import pobj.motx.tme1.Emplacement;
import pobj.motx.tme2.GrillePotentiel;

public class MotX implements ICSP {

	private GrillePotentiel gp;
	private List<IVariable> dicoV;
	
	public MotX(GrillePotentiel gp) {
		this.gp=gp;
		dicoV=new ArrayList<>();
		int index = 0;
		for (Emplacement e: gp.getGrillePlaces().getPlaces()) {
			for(Case c : e.getLettres()) {
				if(c.isVide())
					dicoV.add(new DicoVariable(index, gp));
			}
			index++;
		}
	}
	@Override
	public List<IVariable> getVars() {
		return dicoV;
	}

	@Override
	public boolean isConsistent() {
		return (!gp.isDead());
	}

	@Override
	public ICSP assign(IVariable vi, String val) {
		System.out.println("Valeure assignée :"+val);
		if (!(vi instanceof DicoVariable)) 
			return null;
		int i =((DicoVariable) vi).getIndex();
		GrillePotentiel g = gp.fixer(i,val);
		return new MotX(g);
	}

}
