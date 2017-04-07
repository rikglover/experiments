package Kollections;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class BinaryTree<T extends Comparable> implements Iterable<T> {

	private BinaryNode<T> root;

	public void add(T value) {

		BinaryNode<T> newNode = new BinaryNode<>(value);
		BinaryNode<T> p = root;
		BinaryNode<T> q = null;

		while (p != null) {

			if (value.compareTo(p.getValue()) <= 0) {
				q = p;
				p = p.getLeftChild();
			} else if (value.compareTo(p.getValue()) > 0) {
				q = p;
				p = p.getRightChild();
			}
		}

		if (root == null) {
			root = newNode;
		} else if (value.compareTo(q.getValue()) <= 0) {
			q.setLeftChild(newNode);
			newNode.setParent(q);
		} else {
			q.setRightChild(newNode);
			newNode.setParent(q);
		}
	}

	public boolean remove(T value) {

		BinaryNode<T> nodeToDelete = findNode(value);

		if (nodeToDelete == null) {
			return false;
		} if (nodeToDelete.getLeftChild() == null) {
			transplant(nodeToDelete, nodeToDelete.getRightChild());
		} else if (nodeToDelete.getRightChild() == null) {
			transplant(nodeToDelete, nodeToDelete.getLeftChild());
		} else {
			BinaryNode<T> successor = findMinimum(nodeToDelete.getRightChild());

            if(successor.getParent() != nodeToDelete) {
				transplant(successor, successor.getRightChild());
				successor.setRightChild(nodeToDelete.getRightChild());
				successor.getRightChild().setParent(successor);
			}

			transplant(nodeToDelete, successor);
			successor.setLeftChild(nodeToDelete.getLeftChild());
			successor.getLeftChild().setParent(successor);
		}

		return true;
	}

	public void transplant(BinaryNode<T> destination, BinaryNode<T> source) {

		if(destination.getParent() == null) {
			root = source;
		} else if(destination.getParent().getLeftChild() == destination) {
			destination.getParent().setLeftChild(source);
		} else {
			destination.getParent().setRightChild(source);
		}

		if(source != null) {
			source.setParent(destination.getParent());
		}
	}

	public boolean isEmpty() {

		return root == null;
	}

	public void clear() {

		root = null;
	}

	public boolean contains(T value) {

		return findNode(value) != null;
	}

	private BinaryNode<T> findNode(T value) {

		BinaryNode<T> p = root;

		while (p != null && value.compareTo(p.getValue()) != 0) {
			if (value.compareTo(p.getValue()) <= 0) {
				p = p.getLeftChild();
			} else if (value.compareTo(p.getValue()) > 0) {
				p = p.getRightChild();
			}
		}

		return p;
	}

	public void inorder() {

		inorder(root);
	}

	private void inorder(BinaryNode<T> rootOfSubtree) {

		if (rootOfSubtree != null) {
			inorder(rootOfSubtree.getLeftChild());
			System.out.print(rootOfSubtree.getValue() + " ");
			inorder(rootOfSubtree.getRightChild());
		}
	}

	public void depthFirstTraversalPreorder() {

		Deque<BinaryNode<T>> stack = new ArrayDeque<>();

		if (isEmpty()) {
			return;
		}

		stack.push(root);

		while (!stack.isEmpty()) {
			BinaryNode<T> nextNode = stack.pop();

			System.out.println(nextNode.getValue());

			if (nextNode.getLeftChild() != null) {
				stack.push(nextNode.getLeftChild());
			}

			if (nextNode.getRightChild() != null) {
				stack.push(nextNode.getRightChild());
			}
		}
	}

	public void breadthFirstTraversal() {

		java.util.Queue<BinaryNode<T>> queue = new ArrayDeque<>();

		if (isEmpty()) {
			return;
		}

		queue.add(root);

		while (!queue.isEmpty()) {
			BinaryNode<T> nextNode = queue.remove();

			System.out.println(nextNode.getValue());

			if (nextNode.getLeftChild() != null) {
				queue.add(nextNode);
			}

			if (nextNode.getRightChild() != null) {
				queue.remove(nextNode);
			}
		}
	}

	public T findMinimum() {

		if (root == null) {
			return null;
		} else {
			return findMinimum(root).getValue();
		}
	}

	private BinaryNode<T> findMinimum(BinaryNode<T> rootOfSubtree) {

		BinaryNode<T> p = rootOfSubtree;

		while (p.getLeftChild() != null) {
			p = p.getLeftChild();
		}

		return p;
	}

	public T findMaximum() {

		if (root == null) {
			return null;
		} else {
			return findMaximum(root).getValue();
		}
	}

	private BinaryNode<T> findMaximum(BinaryNode<T> rootOfSubtree) {

		BinaryNode<T> p = rootOfSubtree;

		while (p.getRightChild() != null) {
			p = p.getRightChild();
		}

		return p;
	}

	public T findSuccessor(T value) {

		BinaryNode<T> node = findNode(value);
		BinaryNode<T> successor = findSuccessor(node);

		if (successor == null) {
			return null;
		} else {
			return successor.getValue();
		}
	}

	private BinaryNode<T> findSuccessor(BinaryNode<T> node) {

		if (node.getRightChild() != null) {
			return findMinimum(node.getRightChild());
		} else {
			BinaryNode<T> p = node.getParent();
			BinaryNode<T> q = node;

			while (p != null && p.getLeftChild() != q) {
				q = p;
				p = p.getParent();
			}

			return p;
		}
	}

	public T findPredecessor(T value) {

		BinaryNode<T> node = findNode(value);
		BinaryNode<T> predecessor = findPredecessor(node);

		if (predecessor == null) {
			return null;
		} else {
			return predecessor.getValue();
		}
	}

	private BinaryNode<T> findPredecessor(BinaryNode<T> node) {

		if (node.getLeftChild() != null) {
			return findMaximum(node.getLeftChild());
		} else {
			BinaryNode<T> p = node.getParent();
			BinaryNode<T> q = node;

			while (p != null && p.getRightChild() != q) {
				q = p;
				p = p.getParent();
			}

			return p;
		}
	}

	public int computeHeight() {

		if(root == null) {
			return -1;
		} else {
			return computeHeight(root);
		}
	}

	private int computeHeight(BinaryNode<T> subtreeRoot) {

		if(subtreeRoot == null) {
			return 0;
		} else {
			int leftHeight = computeHeight(subtreeRoot.getLeftChild()) + 1;
			int rightHeight = computeHeight(subtreeRoot.getRightChild()) + 1;

			return (leftHeight > rightHeight) ? leftHeight : rightHeight;
		}
	}

	private Iterator<T> getIterator(boolean descending) {

		return new Iterator<T>() {

			BinaryNode<T> nextNode = descending ? findMaximum(root) : findMinimum(root);

			public T next() {

				T value = nextNode.getValue();

				nextNode = descending ? findPredecessor(nextNode) : findSuccessor(nextNode);

				return value;
			}

			public boolean hasNext() {

				return nextNode != null;
			}
		};
	}

	@Override
	public Iterator<T> iterator() {

		return getIterator(false);
	}

	public Iterator<T> descendingIterator() {

		return getIterator(true);
	}

	@Override
	public boolean equals(Object other) {

		if(other != null && other instanceof BinaryTree) {
			BinaryTree<T> otherTree = (BinaryTree<T>) other;

			if(otherTree.root == this.root) {
				return true;
			} else if(otherTree.root == null || this.root == null) {
                return false;
			} else {
				return otherTree.root.equals(this.root);
			}

		}

		return false;
	}
}