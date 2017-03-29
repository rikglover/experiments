package Kollections;

public interface Queue<T> extends Kollection<T> {

	void enqueue(T value);

	T dequeue();
	T peak();
}
