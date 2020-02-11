package pobj.tme4.test;

import java.io.IOException;

import org.junit.Test;

import pobj.tme4.HashMultiSet;
import pobj.tme4.NaiveMultiSet;
import pobj.util.Chrono;

public class NaiveTest {
	@Test
	public void test() {
		Chrono chrono = new Chrono(); 

		try {
			pobj.tme4.WordCount.wordcount(new HashMultiSet());
		} catch (IOException e) {
			e.printStackTrace();
		}
		chrono.stop();
		System.out.println("hash fin");
		
		try {
			pobj.tme4.WordCount.wordcount(new NaiveMultiSet());
		} catch (IOException e) {
			e.printStackTrace();
		}
		chrono.stop();
		System.out.println("naive fin");

	}
	

}
