package pobj.tme5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MultiSetParser {

	public static MultiSet<String> parse(String fileName) throws InvalidMultiSetFormat {
		MultiSet<String> ms = new HashMultiSet<String>();
		try {
			//CHARGEMENT FICHIER & DECOUPAGE EN MOTS
			BufferedReader br = new BufferedReader(new FileReader(fileName)); 
			String line; 
			while ((line = br.readLine())!=null) { 
				if (line.equals("") || line.equals(":")) continue;
				if(!line.contains(":"))
					throw new InvalidMultiSetFormat("Erreur MultiSetParser: la line ne contient pas de : ");
				String [] chaine = line.split(":");
				
				if (Integer.parseInt(chaine[1]) <0 )
					throw new InvalidMultiSetFormat("Erreur MultiSetParser: caractère : suivi d’une chaîne ne représentant pas un entier strictement positif ");
				
				ms.add(chaine[0], Integer.parseInt(chaine[1]));
			}
			
			br.close();		
		} catch(IOException e) {
			throw new InvalidMultiSetFormat("IOErreur MultiSetParser", e);
		}
		return ms;	
	}
}
