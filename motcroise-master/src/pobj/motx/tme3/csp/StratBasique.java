package pobj.motx.tme3.csp;

import java.util.List;

public class StratBasique implements IChoixValeur {

	@Override
	public List<String> orderValues(ICSP problem, IVariable v) {
		// TODO Auto-generated method stub
		return v.getDomain();
	}

}
