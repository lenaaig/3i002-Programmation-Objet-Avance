package pobj.motx.tme3.csp;

import java.util.Collections;
import java.util.List;

public class StratAleatoire implements IChoixValeur{

	@Override
	public List<String> orderValues(ICSP problem, IVariable v) {
		// TODO Auto-generated method stub
		Collections.shuffle(v.getDomain());
		
		return v.getDomain();
	}

}
