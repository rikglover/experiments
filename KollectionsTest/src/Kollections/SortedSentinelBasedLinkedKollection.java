package Kollections;

public class SortedSentinelBasedLinkedKollection<T extends Comparable> extends SentinelBasedLinkedKollection<T> {

	@Override
	public void add(T value) {

		Node<T> p = getSentinel();
		Node<T> newNode = new Node<>(value);

		while((p.getNext() != null) && (p.getNext().getValue().compareTo(newNode.getValue()) < 0)) {
			p = p.getNext();
		}

		newNode.setNext(p.getNext());
		p.setNext(newNode);
	}

	@Override
	public Node<T> findPredecessor(Object value) {

		Node<T> predecessor = getSentinel();

		while((predecessor.getNext() != null) && (predecessor.getNext().getValue().compareTo(value) < 0)) {
			predecessor = predecessor.getNext();
		}

		if((predecessor.getNext() != null) && predecessor.getNext().getValue().equals(value)) {
			return predecessor;
		}

		return null;
	}
}
