package pobj.tme4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class WordCount {

	public static void wordcount (MultiSet<String> ms) throws IOException {
		//CHARGEMENT FICHIER & DECOUPAGE EN MOTS
		String file = "data/WarAndPeace.txt"; 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String line; 
		while ((line = br.readLine())!=null) { 
			for (String word : line.split("\\P{L}+")) { 
				if (word.equals("")) continue; // ignore les mots vides 
				ms.add(word);
			} 
		} 
		br.close();

		List<String> keys = ms.elements();
		Comparator<String> comparator = ms.comparator();
		
		Collections.sort(keys,comparator);
		/*
		for (int i=0; i<10; i++) {
			System.out.println(keys.get(i)+ " : " +ms.count(keys.get(i)));
		}
		*/
	}
	
	public static void main (String[] args) {
		try {
			wordcount( new HashMultiSet<String>());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}
