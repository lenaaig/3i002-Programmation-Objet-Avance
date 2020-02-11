package pobj.expr;

import java.util.HashMap;
import java.util.Map;

public class Question8 {

	public static Map<String, Integer> env1() {
		return new HashMap<>();
	}

	public static Map<String, Integer> env2() {
		Map<String, Integer> m= new HashMap<>();
		m.put("x", 10);
		m.put("y", 20);
		return m;
	}

	public static Map<String, Integer> env3() {
		Map<String, Integer> m= new HashMap<>();
		m.put("z", 9);
		return m;
	}

}
