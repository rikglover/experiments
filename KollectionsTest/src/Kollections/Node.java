package Kollections;

public class Node<T> {

	T value;

	Node<T> next;

	public Node() {

		this.value = null;
		this.next = null;
	}

	public Node(T value) {

		this.value = value;
		this.next = null;
	}

	public Node(T value, Node<T> next) {

		this.value = value;
		this.next = next;
	}

	public T getValue() {

		return value;
	}

	public void setValue(T value) {

		this.value = value;
	}

	public Node<T> getNext() {

		return next;
	}

	public void setNext(Node<T> next) {

		this.next = next;
	}

	public boolean equals(Object o) {

		if((o == null) || !(o instanceof Node)) {
			return false;
		} else {
			return value.equals(((Node) o).getValue());
		}
	}
}
