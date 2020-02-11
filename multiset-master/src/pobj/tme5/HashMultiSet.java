package pobj.tme5;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * 
 * Class HashMultiSet<T> represente un multi-ensemble
 *
 * @param <T>
 */
public class HashMultiSet<T> extends AbstractCollection<T> implements MultiSet<T>, Iterable<T>{
	private HashMap<T, Integer> multiE;
	
	public HashMultiSet() {
		multiE = new HashMap<>();
	}
	
	/**
	 * Constructeur d'une HashMultiSet
	 * @param coll: Collention <T>
	 */
	@SuppressWarnings("unchecked")
	public HashMultiSet(Collection<T> coll) {
		multiE = new HashMap<>();
		Object[] elements = coll.toArray();
		for(int i=0; i<elements.length;i++) {
			multiE.put((T) elements[i], new Integer(1));	
		}
		
	}
	

	@Override
	public boolean add(T e, int count) {
		if ((e==null) || (count<0))
			throw new IllegalArgumentException();
		if(multiE.containsKey(e)) 
			multiE.put(e, multiE.get(e)+count);
		else 
			multiE.put(e, new Integer(count));
		
		if(!multiE.containsKey(e)) return false;
		assert isConsistent();
		return true;
	}

	@Override
	public boolean add(T e) {
		if (e==null)
			throw new IllegalArgumentException();
		if(multiE.containsKey(e)) 
			multiE.put(e, multiE.get(e)+1);
		else 
			multiE.put(e, new Integer(1));
		
		if(!multiE.containsKey(e)) return false;
		
		assert isConsistent();
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object e) {
		if (e==null) 
			throw new IllegalArgumentException();
		if(!multiE.containsKey(e)) return false;
		
		Integer count = multiE.remove(e);
		if(count>1)
			multiE.put((T) e, count-1);
		assert isConsistent();
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object e, int count) {
		if ((e==null) || (count<0))
			throw new IllegalArgumentException();
		
		if(!multiE.containsKey(e)) return false;
		
		Integer val = multiE.remove(e);
		if(val>count)
			multiE.put((T) e, val-count);
		assert isConsistent();
		return true;
	}

	@Override
	public int count(T o) {
		if(!multiE.containsKey(o)) return 0;
		return multiE.get(o);
	}

	@Override
	public void clear() {
		multiE.clear();
	}

	@Override
	public int size() {
		int size=0;
		for (T key : multiE.keySet()) {
			size+=multiE.get(key);
		}
		return size;
	}
	
	@Override
	public Iterator<T> iterator() {
		return  new HashMultiSetIterator<T>(this);
	}
	

	/**
	 * Retourne la liste des keys 
	 * @return List<T>
	 */
	@Override
	public List<T> elements() {
		
		List<T> elmts = new ArrayList<>();
		for (T key : multiE.keySet()) {
			elmts.add(key);
		}
		return elmts;
	}

	
	/**
	 * Recupere le nombre d'occurence de l'objet
	 * @param o: Object
	 * @return int 
	 */
	public int get(Object o) {
		return multiE.get(o);
	}

	
	@Override
	public String toString() {
		StringBuilder res=new StringBuilder();
		res.append("[");
		
		for (T key : multiE.keySet()){
			res.append(key+":"+get(key)+"; ");
		}
		
		res.append("]");
		return res.toString();
	}
	
	
	/**
	 * Verifie les cohérences interne
	 * @return true or false
	 */
	@Override
	public boolean isConsistent(){
		int res=0;
		for (T key : multiE.keySet()){
			res+= multiE.get(key);
			if(res<0)
				return false;
		}
		return res == size();
	}
	
	
	/**
	 * 
	 * Class HashMultiSetIterator<T> represente un iterateur de multi-ensemble
	 *
	 */
	@SuppressWarnings("hiding")
	public class HashMultiSetIterator<T> implements Iterator<T>{
		private HashMultiSet<T> ensemble;
		private int nbElem, cptVal;
		private Iterator<Map.Entry<T, Integer>> iter;
		private Object objCourant;
		private Integer occur;
		
		public HashMultiSetIterator(HashMultiSet<T> hashMultiSet) {
			ensemble = hashMultiSet;
			cptVal = 0;
			nbElem=0;
			iter= ensemble.multiE.entrySet().iterator();
		}

		@Override
		public boolean hasNext() {
			return nbElem < multiE.size();
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			if(!hasNext())
				throw new NoSuchElementException(); 
			if(cptVal == 0) {
				@SuppressWarnings("rawtypes")
				Map.Entry pair = (Map.Entry) iter.next();
				objCourant= pair.getKey();
				occur= (Integer) pair.getValue();
			}
			cptVal++;
			//System.out.println("occurence: "+occur+"  cptVal: "+cptVal);
			if(cptVal == occur) {
				cptVal=0;
				nbElem++;
			}
			
			return (T) objCourant;
		}
	}



	/**
	 * 
	 * Classe representant le comparator:
	 * 
	 */
	@Override
	public Comparator<T> comparator() {
		return new MultiSetComparator<T>(this);
	}

	private class MultiSetComparator<T> implements Comparator<T> {
		private MultiSet<T> ms;
		public MultiSetComparator(MultiSet<T> ms) {
			this.ms = ms;
		}
		
		@Override
		public int compare(T o1, T o2) {
			if (ms.count(o1)>ms.count(o2))
    			return -1;
    		if (ms.count(o1)<ms.count(o2))
    			return 1;
    		return 0;
		}
	
	}

}
