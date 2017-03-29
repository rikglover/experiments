package Kollections;

import java.util.Iterator;

public interface DoubleEndedKollection<T> extends Kollection<T> {

	void addFirst(T value);
	void addLast(T value);

	T removeFirst();
	T removeLast();

	T peakFirst();
	T peakLast();

	Iterator<T> descendingIterator();
}
