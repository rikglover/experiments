package Kollections;


public class DoubleyLinkedNode<T> {

	T value = null;

	private DoubleyLinkedNode<T> next;
	private DoubleyLinkedNode<T> previous;

	public DoubleyLinkedNode(T value, DoubleyLinkedNode<T> next, DoubleyLinkedNode<T> previous) {

		this.setValue(value);
		this.setPrevious(previous);
		this.setNext(next);
	}

	public DoubleyLinkedNode(T value) {

		this(value, null, null);
	}

	public T getValue() {

		return value;
	}

	public void setValue(T value) {

		this.value = value;
	}

	public DoubleyLinkedNode<T> getNext() {

		return next;
	}

	public void setNext(DoubleyLinkedNode<T> next) {

		this.next = next;
	}

	public DoubleyLinkedNode<T> getPrevious() {

		return previous;
	}

	public void setPrevious(DoubleyLinkedNode<T> previous) {

		this.previous = previous;
	}
}
