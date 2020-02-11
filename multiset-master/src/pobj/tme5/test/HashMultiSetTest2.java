package pobj.tme5.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import pobj.tme5.HashMultiSet;
import pobj.tme5.MultiSet;

public class HashMultiSetTest2 {
	
	private MultiSet<String> m;
	
	@Before
	public void before() {
		m = new HashMultiSet<>();
	}
	@Test
	public void testRemove1() {
		m.add("a", 6);
		m.remove("a");
		m.remove("a", 2);
		assertEquals(3, m.count("a"));
	}
	@Test(expected = IllegalArgumentException.class) 
	public void testRemove2() {
		m.add("a", 6);
		m.remove("a");
		m.remove("a", -2);
	}
	
	@Test
	public void testSize() {
		m.add("a");
		m.add("b", 2);
		m.add("c");
		assertEquals(4, m.size());
	}
	
	@Test
	public void testToString() {
		m.add("a");
		m.add("b", 2);
		String res=m.toString();
		assertEquals(res, "[a:1; b:2; ]");
	}
	
	@Test
	public void testClear() {
		m.add("a");
		m.clear();
		assertEquals(0, m.size());
	}
	
	@Test
	public void testComplexe() {
		String[] tab = {"a", "b", "c", "d"};
		for (int i=1; i<8; i++) {
			m.add(tab[i%4], i*5);
		}
		assertEquals(m.count("a"), 20);
		m.remove("d", 3);
		m.remove("a", 5);
		m.add("a", 10);
		m.add("b", 7);
		assertEquals(m.count("a"), 25);
	}
	
	@Test
	public void testParticulier1() {
		m.add("a", 3);
		m.add("a",0);
		m.remove("a", 0);	

		assertEquals(m.count("a"), 3);
		
		m.add("a",2);
		m.remove("a", 2);
		assertEquals(m.count("a"), 3);
		assertEquals(m.count("b"), 0);
	}
	

}
