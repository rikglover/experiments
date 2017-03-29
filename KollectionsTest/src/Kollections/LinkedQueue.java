package Kollections;

import java.util.NoSuchElementException;

public class LinkedQueue<T extends Comparable> extends LinkedKollection<T> implements Queue<T> {

	@Override
	public void enqueue(T value) {

		add(value);
	}

	@Override
	public T dequeue() {

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
