package Kollections;

public class KollectionUtility {

	public static <T extends Comparable> void insertionSort(SentinelBasedLinkedKollection linkedKollection) {

		Node<T> newSentinel = new Node<T>();
        Node<T> oldKollectionCursor = linkedKollection.getSentinel();

		oldKollectionCursor = oldKollectionCursor.getNext();

		while(oldKollectionCursor != null) {
            Node<T> newKollectionCursor = newSentinel;
			Node<T> next = oldKollectionCursor;

			oldKollectionCursor = next.getNext();

			while((newKollectionCursor.getNext() != null) && (next.getValue().compareTo(newKollectionCursor.getNext().getValue()) < 0)) {
                newKollectionCursor = newKollectionCursor.getNext();
			}

			next.setNext(newKollectionCursor.getNext());
			newKollectionCursor.setNext(next);
		}

		linkedKollection.setSentinel(newSentinel);
	}

	public static <T extends Comparable> void selectionSort(SentinelBasedLinkedKollection linkedKollection) {

		Node<T> oldSentinel = linkedKollection.getSentinel();
		Node<T> newSentinel = new Node<>();

		while(oldSentinel.getNext() != null) {
			Node<T> beforeLargest = oldSentinel;
			Node<T> largest = oldSentinel.getNext();
			Node<T> next = oldSentinel.getNext();

			while(next.getNext() != null) {
				if(next.getNext().getValue().compareTo(largest.getValue()) > 0) {
					beforeLargest = next;
					largest = beforeLargest.getNext();
				}

				next = next.getNext();
			}

			beforeLargest.setNext(largest.getNext());
			largest.setNext(newSentinel.getNext());
			newSentinel.setNext(largest);
		}

		linkedKollection.setSentinel(newSentinel);
	}

	public static <T extends Comparable> void insertionSort(LinkedKollection<T> linkedKollection) {

		Node<T> oldHead = linkedKollection.getHead();
		Node<T> newHead = null;

		while(oldHead != null) {
			Node<T> nodeToInsert = oldHead;

			oldHead = oldHead.getNext();

			if((newHead == null) || (nodeToInsert.getValue().compareTo(newHead.getValue()) < 0)) {
				nodeToInsert.setNext(newHead);
				newHead = nodeToInsert;
			} else {
				Node<T> p = newHead.getNext();
				Node<T> q = newHead;

				while((p != null) && (p.getValue().compareTo(nodeToInsert.getValue()) < 0)) {
					q = p;
					p = p.getNext();
				}

				nodeToInsert.setNext(p);
				q.setNext(nodeToInsert);
			}
		}

		linkedKollection.setHead(newHead);
	}

	public static <T extends Comparable> void selectionSort(LinkedKollection<T> linkedKollection) {

		Node<T> oldHead = linkedKollection.getHead();
		Node<T> newHead = null;

		while(oldHead != null) {
			Node<T> largest = oldHead;
			Node<T> beforeLargest = null;
			Node<T> p = oldHead.getNext();
			Node<T> q = oldHead;

			while(p != null) {
				if(p.getValue().compareTo(largest.getValue()) > 0) {
					largest = p;
					beforeLargest = q;
				}

				q = p;
				p = p.getNext();
			}

			if(beforeLargest != null) {
				beforeLargest.setNext(largest.getNext());
			} else {
				oldHead = oldHead.getNext();
			}

			largest.setNext(newHead);
			newHead = largest;
		}

		linkedKollection.setHead(newHead);
	}
}
