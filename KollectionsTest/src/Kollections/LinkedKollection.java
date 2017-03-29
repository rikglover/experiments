package Kollections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedKollection<T extends Comparable> implements Kollection<T> {

	Node<T> head = null;


	public Node<T> getHead() {

		return head;
	}

	public void setHead(Node<T> head) {

		this.head = head;
	}

	@Override
	public void add(T value) {

		Node<T> newNode = new Node<T>(value);

		if(head == null) {
		    head = newNode;
		} else {
			Node<T> p = head;

			while(p.getNext() != null) {
				p = p.getNext();
			}

			p.setNext(newNode);
		}
	}

	@Override
	public boolean remove(Object value) {

		if((head != null) && head.getValue().equals(value)) {
            head = head.getNext();

			return true;
		} else {
			Node<T> p = head;
			Node<T> q = null;

			while((p != null) && !p.getValue().equals(value)) {
				q = p;
				p = p.getNext();
			}

			if(p != null) {
				q.setNext(p.getNext());

				return true;
			}

			return false;
		}
	}

	@Override
	public void clear() {

		head = null;
	}

	@Override
	public boolean contains(Object value) {

		Node<T> p = head;

		while((p != null) && !p.getValue().equals(value)) {
			p = p.getNext();
		}

		return p != null;
	}

	@Override
	public boolean isEmpty() {

		return head == null;
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

			private Node<T> p = null;
			private Node<T> q = null;

			@Override
			public void remove() {

               if(p == null) {
               	   throw new IllegalStateException();
			   } else {
			   	   q.setNext(p.getNext());
				   p = q.getNext();
			   }
			}

			@Override
			public boolean hasNext() {

				if(head == null) {
					return false;
				} else if(p == null) {
					return true;
				} else {
                    return p.getNext() != null;
				}
			}

			@Override
			public T next() {

				if((head == null) || ((p != null) && (p.getNext() == null))) {
					throw new NoSuchElementException();
				} else {
					q = p;
					p = (p == null) ? head : p.getNext();

					return p.getValue();
				}
			}
		};

	}
}
