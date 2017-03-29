package Kollections;

public interface ListKollection<T> extends Kollection<T> {

	int indexOf(Object o);

	T get(int index);

	void set(T value, int index);
	void add(T value, int index);

	T remove(int index);
}
