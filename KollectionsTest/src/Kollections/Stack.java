package Kollections;

public interface Stack<T> extends Kollection<T> {

	void push(T value);

	T pop();
	T peak();
}
