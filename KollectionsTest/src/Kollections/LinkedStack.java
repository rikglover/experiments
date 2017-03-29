package Kollections;

import java.util.NoSuchElementException;

public class LinkedStack<T extends Comparable> extends LinkedKollection<T> implements Stack<T> {

	@Override
	public void add(T value) {

		head = new Node<>(value, head);
	}

	@Override
	public void push(T value) {

		add(value);
	}

	@Override
	public T pop() {

		if(isEmpty()) {
			throw new NoSuchElementException();
		} else {
			T value = head.getValue();

			head = head.getNext();

			return value;
		}
	}

	@Override
	public T peak() {

		return isEmpty() ? null : head.getValue();
	}

}
