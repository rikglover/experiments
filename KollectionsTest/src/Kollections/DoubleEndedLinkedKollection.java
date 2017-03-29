package Kollections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.UnaryOperator;

public class DoubleEndedLinkedKollection<T> implements DoubleEndedKollection<T>, Queue<T>, Stack<T> {

	private DoubleyLinkedNode<T> head;
	private DoubleyLinkedNode<T> tail;


	@Override
	public void addFirst(T value) {

		if(isEmpty()) {
			head = tail = new DoubleyLinkedNode<>(value);
		} else {
			head = new DoubleyLinkedNode<>(value, head, null);
			head.getNext().setPrevious(head);
		}
	}

	@Override
	public void addLast(T value) {

		if(isEmpty()) {
			head = tail = new DoubleyLinkedNode<>(value);
		} else {
			tail = new DoubleyLinkedNode<>(value, null, tail);
			tail.getPrevious().setNext(tail);
		}
	}

	@Override
	public T removeFirst() {

		if(isEmpty()) {
			throw new NoSuchElementException();
		} else {
			T value = head.getValue();

			if(head == tail) {
				tail = head = null;
			} else {
				head = head.getNext();
				head.setPrevious(null);
			}

			return value;
		}
	}

	@Override
	public T removeLast() {

		if(isEmpty()) {
			throw new NoSuchElementException();
		} else {
			T value = tail.getValue();

			if(head == tail) {
				head = tail = null;
			} else {
				tail = tail.getPrevious();
				tail.setNext(null);
			}

			return value;
		}
	}

	@Override
	public T peakFirst() {

		if(isEmpty()) {
			return null;
		} else {
			return head.getValue();
		}
	}

	@Override
	public T peakLast() {

		if(isEmpty()) {
			return null;
		} else {
			return tail.getValue();
		}
	}

	@Override
	public void enqueue(T value) {

		addLast(value);
	}

	@Override
	public T dequeue() {

		return removeFirst();
	}

	@Override
	public T peak() {

		return peakFirst();
	}


	@Override
	public void push(T value) {

		addFirst(value);
	}

	@Override
	public T pop() {

		return removeFirst();
	}

	@Override
	public void clear() {

		head = tail = null;
	}

	@Override
	public void add(T value) {

		addLast(value);
	}

	protected boolean removeNode(DoubleyLinkedNode<T> node) {

		if(node == null) {
			return false;
		} else {
			if(node == head) {
				removeFirst();
			} else if(node == tail) {
				removeLast();
			} else {
				DoubleyLinkedNode<T> predecessor = node.getPrevious();
				DoubleyLinkedNode<T> successor = node.getNext();

				predecessor.setNext(successor);
				successor.setPrevious(predecessor);
			}

			return true;
		}
	}

	protected DoubleyLinkedNode findNode(Object o) {

		DoubleyLinkedNode<T> p = head;

		while((p != null) && !p.getValue().equals(o)) {
			p = p.getNext();
		}

		return p;
	}

	@Override
	public boolean remove(Object o) {

		DoubleyLinkedNode<T> node = findNode(o);

		return removeNode(node);
	}

	@Override
	public boolean contains(Object o) {

		return findNode(o) != null;
	}

	@Override
	public boolean isEmpty() {

		return head == null;
	}

	@Override
	public int size() {

		int length = 0;

		for(T t : this) {
			length += 1;
		}

		return length;
	}

	@Override
	public Iterator<T> iterator() {

		return getIterator(head, DoubleyLinkedNode::getNext);
	}

	@Override
	public Iterator<T> descendingIterator() {

		return getIterator(tail, DoubleyLinkedNode::getPrevious);
	}

	protected Iterator<T> getIterator(DoubleyLinkedNode<T> startingNode, UnaryOperator<DoubleyLinkedNode<T>> operator) {

		return new Iterator<T>() {

			DoubleyLinkedNode<T> p = null;

			@Override
			public boolean hasNext() {

				if (startingNode == null) {
					return false;
				} else if (p == null) {
					return true;
				} else {
					return operator.apply(p) != null;
				}
			}

			@Override
			public T next() {

				if (startingNode == null) {
					throw new NoSuchElementException();
				} else if (p == null) {
					p = startingNode;
				} else {
					p = operator.apply(p)  ;
				}

				return p.getValue();
			}

			@Override
			public void remove() {

				if (p == null) {
					throw new IllegalStateException();
				} else {
					if (!removeNode(p)) {
						throw new IllegalStateException();
					}
				}
			}
		};
	}
}
