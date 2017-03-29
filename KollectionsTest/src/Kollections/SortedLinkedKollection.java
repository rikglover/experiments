package Kollections;

public class SortedLinkedKollection<T extends Comparable> extends LinkedKollection<T> {

	@Override
	public void add(T value) {

		if((head == null) || (value.compareTo(head.getValue()) < 0)) {
			head = new Node<>(value, head);
		} else {
			Node<T> p = head.getNext();
			Node<T> q = head;

			while((p != null) && (p.getValue().compareTo(value) < 0)) {
				q = p;
				p = p.getNext();
			}

			q.setNext(new Node<>(value, p));
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

			while((p != null) && (p.getValue().compareTo(value) < 0)) {
				q = p;
				p = p.getNext();
			}

			if((p != null) && p.getValue().equals(value)) {
				q.setNext(p.getNext());

				return true;
			}

			return false;
		}
	}

	@Override
	public boolean contains(Object value) {

		Node<T> p = head;

		while((p != null) && (p.getValue().compareTo(value) < 0)) {
			p = p.getNext();
		}

		if((p != null) && p.getValue().equals(value)) {
			return true;
		}

		return false;
	}
}
