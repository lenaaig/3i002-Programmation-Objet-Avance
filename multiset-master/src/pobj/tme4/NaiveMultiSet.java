package pobj.tme4;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class NaiveMultiSet<T> extends AbstractCollection<T> implements MultiSet<T>{
	private List<T> list = new ArrayList<>();
	
	@Override
	public boolean add(T e, int count) {
		if(count<0)
			throw new IllegalArgumentException();
		
		if(count==0)
			return false;
		for(int i = 0;i<count;i++) {
			list.add(e);
		}
		return true;
	}
	
	public boolean add(T e) {
		list.add(e);
		return true;
	}

	@Override
	public boolean remove(Object e, int count) {
		if(count<1)
			throw new IllegalArgumentException("ERREUR ");
		
		if(list.contains(e)) {
			Iterator<T> iter = list.iterator();
			int  cpt = count;
			while(iter.hasNext() && cpt >0) {
				T elem= iter.next();
				if(elem.equals(e))
					iter.remove();
			}
			return true;
		}	
		return false;
	}

	@Override
	public int count(T o) {
		int cpt=0;
		for(int i=0,len=list.size();i<len;i++)
			if(list.get(i).equals(o))
				cpt++;	
		return cpt;
	}

	@Override
	public List<T> elements() {		
		List<T> copy = new ArrayList<>();
		for(T el: list)
			copy.add(el);
		return copy;
	}


	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

	@Override
	public int size() {
		return list.size();
	}
	
	
	public MultiSetComparator<T> comparator() {
		return new MultiSetComparator<T>(this);
	}
	
	private class MultiSetComparator<T> implements Comparator<T> {
		
		MultiSet<T> ms;
		public MultiSetComparator(MultiSet<T> ms) {
			this.ms = ms;
		}
		public int compare(T o1, T o2) {
			return Integer.compare(ms.count(o2), ms.count(o1));
		}
	}
	
	
}
