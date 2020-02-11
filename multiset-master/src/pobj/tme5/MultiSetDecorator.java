package pobj.tme5;

import java.util.AbstractCollection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MultiSetDecorator<T>  extends AbstractCollection<T> implements MultiSet<T>{
	private MultiSet<T> decorated;
	
	public MultiSetDecorator(MultiSet<T> multiS) {
		decorated=multiS;
	}
	
	@Override
	public boolean add(T e, int count){
		decorated.add(e, count);
		if(! isConsistent())
			throw new InternalError();
		return true;
	}
	
	public boolean add(T e){
		decorated.add(e);
		if(! isConsistent())
			throw new InternalError();
		return true;
	}

	@Override
	public boolean remove(Object e, int count){
		decorated.remove(e, count);
		if(! isConsistent())
			throw new InternalError();
		return true;
	}
	
	public boolean remove(Object e){
		decorated.remove(e);
		if(! isConsistent())
			throw new InternalError();
		return true;
	}

	@Override
	public int count(T o) {
		return decorated.count(o);
	}

	@Override
	public List<T> elements() {
		return decorated.elements();
	}

	@Override
	public Comparator<T> comparator() {
		return decorated.comparator();
	}

	@Override
	public Iterator<T> iterator() {
		return decorated.iterator();
	}

	@Override
	public int size() {
		return decorated.size();
	}

	@Override
	public boolean isConsistent() {
		return decorated.isConsistent();
	}
	
	@Override
	public String toString() {
		return decorated.toString();
	}
}
