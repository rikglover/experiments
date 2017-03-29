package Kollections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SentinelBasedLinkedKollection<T extends Comparable> implements Kollection<T> {

	private Node<T> sentinel = new Node<T>();


	@Override
	public void add(T value) {

		Node<T> newNode = new Node<>(value);
		Node<T> afterMe = sentinel;

        while(afterMe.getNext() != null) {
        	afterMe = afterMe.getNext();
		}

		afterMe.setNext(newNode);
	}

	@Override
	public void clear() {

		sentinel.setNext(null);
	}

	protected Node<T> findPredecessor(Object o) {

		Node<T> predecessor = sentinel;

		while((predecessor.getNext() != null) && !predecessor.getNext().getValue().equals(o)) {
			predecessor = predecessor.getNext();
		}

		if(predecessor.getNext() != null) {
			return predecessor;
		} else {
			return null;
		}
	}

	@Override
	public boolean remove(Object o) {

		Node<T> predecessor = findPredecessor(o);

		if(predecessor != null) {
			predecessor.setNext(predecessor.getNext().getNext());

			return true;
		}

		return false;
	}

	@Override
	public boolean contains(Object o) {

		return findPredecessor(o) != null;
	}

	@Override
	public boolean isEmpty() {

		return sentinel.getNext() == null;
	}

	@Override
	public int size() {

		int length = 0;

		for(T value : this) {
			length += 1;
		}

		return length;
	}

	@Override
	public Iterator<T> iterator() {

		return new Iterator<T>() {

			private Node<T> p = sentinel;

			@Override
			public void remove() {

				if(p == null) {
					throw new IllegalStateException();
				} else {
					Node<T> predecessor = findPredecessor(p.getValue());

					predecessor.setNext(p.getNext());
				}
			}

			@Override
			public boolean hasNext() {

				return p.getNext() != null;
			}

			@Override
			public T next() {

				if(hasNext()) {
					p = p.getNext();

					return p.getValue();
				} else {
					throw new NoSuchElementException();
				}
			}
		};
	}

	public Node<T> getSentinel() {

		return sentinel;
	}

	public void setSentinel(Node<T> sentinel) {

		this.sentinel = sentinel;
	}
}