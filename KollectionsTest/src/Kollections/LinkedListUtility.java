package Kollections;

public class LinkedListUtility {

	public static <T extends Comparable> boolean hasCycle(Node<T> head) {

		return findStartOfCycle(head) != null;
	}

	public static <T extends Comparable> void breakCycle(Node<T> head) {

		Node<T> startOfCycle = findStartOfCycle(head);
		Node<T> predecessor = startOfCycle;

		while(predecessor.getNext() != startOfCycle) {
			predecessor = predecessor.getNext();
		}

		predecessor.setNext(null);
	}

	private static <T extends Comparable> Node<T> findStartOfCycle(Node<T> head) {

		if((head == null) || (head.getNext() == null)) {
			return null;
		} else {
			Node<T> fast = head;
			Node<T> slow = head;

			do {
				fast = fast.getNext().getNext();
				slow = slow.getNext();
			} while((fast != slow) && (fast != null) && (fast.getNext() != null));

			if((fast == null) || (fast.getNext() == null)) {
				return null;
			} else {
				Node<T> p = fast;
				Node<T> q = head;

				while(p != q) {
					p = p.getNext();
					q = q.getNext();
				}

				return p;
			}
		}
	}

	/*
	 * Assumes a linked list structure of the following form:
	 *
	 *    10-13-15-17
	 *              \
	 *  12-14-16-11-19-25-27-31-35
	 *
	 *  This method is less than optimal. A better approach is to use Floyd's algorithm above (tortoise and the haire algorithm)
	 */
	public static <T extends Comparable> Node<T> findIntersectingNode(Node<T> headOfLinkedList1, Node<T> headOfLinkedList2) {

		int lengthOfLinkedList1 = getLengthOfLinkedList(headOfLinkedList1);
		int lengthOfLinkedList2 = getLengthOfLinkedList(headOfLinkedList2);
		int differenceInLengths = Math.abs(lengthOfLinkedList1 - lengthOfLinkedList2);

		Node<T> cursor1 = headOfLinkedList1;
		Node<T> cursor2 = headOfLinkedList2;

		if (lengthOfLinkedList1 > lengthOfLinkedList2) {
			cursor1 = advanceNodes(cursor1, differenceInLengths);
		} else if (lengthOfLinkedList2 > lengthOfLinkedList1) {
			cursor2 = advanceNodes(cursor2, differenceInLengths);
		}

		while (cursor1 != cursor2) {
			cursor1 = cursor1.getNext();
			cursor2 = cursor2.getNext();
		}

		return cursor1;
	}

	private static <T extends Comparable> int getLengthOfLinkedList(Node<T> head) {

		int length = 0;

		Node<T> p = head;

		while (p != null) {
			length += 1;
			p = p.getNext();
		}

		return length;
	}

	private static <T extends Comparable> Node<T> advanceNodes(Node<T> node, int numberOfNodesToAdvance) {

		Node<T> cursor = node;

		for (int i = 0; i < numberOfNodesToAdvance; i++) {
			cursor = cursor.getNext();
		}

		return cursor;
	}
}
