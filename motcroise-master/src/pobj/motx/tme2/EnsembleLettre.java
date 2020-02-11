package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.List;

public class EnsembleLettre {
	private List<Character> li=new ArrayList<>();

	
	public void add(char c) {
		if (!li.contains(c))
			li.add(c);
	}
	
	public int size() {
		return li.size();
	}
	
	public boolean contains(char c) {
		return li.contains(c);
	}

	public EnsembleLettre intersection(EnsembleLettre ens) {
		EnsembleLettre res=new EnsembleLettre();
		for (char c : li) {
			if (ens.contains(c))
				res.add(c);
		}
		return res;
	}

}


