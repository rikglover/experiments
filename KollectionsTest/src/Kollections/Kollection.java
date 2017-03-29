package Kollections;

import java.util.Iterator;

public interface Kollection<T> extends Iterable<T> {

	void add(T value);
	void clear();

	boolean remove(Object o);
	boolean contains(Object o);
	boolean isEmpty();

	int size();

	Iterator<T> iterator();
}
