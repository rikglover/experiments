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
		} else if (nodeToDelete.getLeftChild() == null && nodeToDelete.getRightChild() == null) {
			if (nodeToDelete.getParent() == null) {
				root = null;
			} else if (nodeToDelete.getParent().getLeftChild() == nodeToDelete) {
				nodeToDelete.getParent().setLeftChild(null);
			} else {
				nodeToDelete.getParent().setRightChild(null);
			}
		} else if (nodeToDelete.getLeftChild() == null || nodeToDelete.getRightChild() == null) {
			BinaryNode<T> child = nodeToDelete.getLeftChild() != null ? nodeToDelete.getLeftChild() : nodeToDelete.getRightChild();

			if (nodeToDelete.getParent() == null) {
				root = child;
				child.setParent(null);
			} else if (nodeToDelete.getParent().getLeftChild() == nodeToDelete) {
				nodeToDelete.getParent().setLeftChild(child);
				child.setParent(nodeToDelete.getParent());
			} else {
				nodeToDelete.getParent().setRightChild(child);
				child.setParent(nodeToDelete.getParent());
			}
		} else {
			BinaryNode<T> successor = findSuccessor(nodeToDelete);

			if(successor.getParent().getRightChild() == successor) {
				successor.setLeftChild(nodeToDelete.getLeftChild());

				if(nodeToDelete.getParent() == null) {
                    root = successor;
					successor.setParent(null);
				} else if(nodeToDelete.getParent().getRightChild() == nodeToDelete) {
					nodeToDelete.getParent().setRightChild(successor);
					successor.setParent(nodeToDelete.getParent());
				} else {
					nodeToDelete.getParent().setLeftChild(successor);
					successor.setParent(nodeToDelete.getParent());
				}

				successor.setLeftChild(nodeToDelete.getLeftChild());
				successor.getLeftChild().setParent(successor);
			} else {

				if(successor.getRightChild() != null){
					successor.getRightChild().setParent(successor.getParent());
				}

				successor.getParent().setLeftChild(successor.getRightChild());

				if(nodeToDelete.getParent() == null) {
					root = successor;
					successor.setParent(null);
				} else if(nodeToDelete.getParent().getRightChild() == nodeToDelete) {
					nodeToDelete.getParent().setRightChild(successor);
					successor.setParent(nodeToDelete.getParent());
				} else {
					nodeToDelete.getParent().setLeftChild(successor);
					successor.setParent(nodeToDelete.getParent());
				}

				successor.setLeftChild(nodeToDelete.getLeftChild());
				successor.getLeftChild().setParent(successor);

				successor.setRightChild(nodeToDelete.getRightChild());
				successor.getRightChild().setParent(successor);

			}
		}

		return true;
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
			if (rootOfSubtree.getLeftChild() != null) {
				inorder(rootOfSubtree.getLeftChild());
			}

			System.out.print(rootOfSubtree.getValue() + " ");

			if (rootOfSubtree.getRightChild() != null) {
				inorder(rootOfSubtree.getRightChild());
			}
		}
	}

	public void depthFirstTraversal() {

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
}