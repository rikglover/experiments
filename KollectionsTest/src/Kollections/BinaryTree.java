package Kollections;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class BinaryTree<T extends Comparable> implements Iterable<T> {

	private BinaryNode<T> root = null;

	public void add(T value) {

		BinaryNode<T> newNode = new BinaryNode<>(value);

		if(root == null) {
            root = newNode;
		} else {
			add(root, newNode);
		}
	}

	private void attachParent(BinaryNode<T> destination, BinaryNode<T> node) {

		if(destination == root) {
			root = node;
		} else if(destination.getParent().getLeft() == destination) {
			destination.getParent().setLeft(node);
		} else {
			destination.getParent().setRight(node);
		}

		if(node != null) {
			node.setParent(destination.getParent());
		}
	}

	public boolean remove(T value) {

		BinaryNode<T> nodeToRemove = find(root, value);

		if(nodeToRemove == null) {
			return false;
		} else if(nodeToRemove.getLeft() == null) {
			attachParent(nodeToRemove, nodeToRemove.getRight());
		} else if(nodeToRemove.getRight() == null) {
			attachParent(nodeToRemove, nodeToRemove.getLeft());
		} else {
			BinaryNode<T> successor = getMinimum(nodeToRemove.getRight());

			if(successor.getParent() != nodeToRemove) {
				attachParent(successor, successor.getRight());
				successor.setRight(nodeToRemove.getRight());
				successor.getRight().setParent(successor);
			}

			attachParent(nodeToRemove, successor);
			successor.setLeft(nodeToRemove.getLeft());
			successor.getLeft().setParent(successor);
		}

		return true;
	}

	public T findSuccessor(T value) {

		BinaryNode<T> node = find(root, value);

		T successorValue = null;

		if(node != null) {
			BinaryNode<T> successor = getSuccessor(node);

			if(successor != null) {
				successorValue = successor.getValue();
			}
		}

		return successorValue;
	}

	public T findPredecessor(T value) {

		BinaryNode<T> node = find(root, value);

		T predecessorValue = null;

		if(node != null) {
			BinaryNode<T> predecessor = getPredecessor(node);

			if(predecessor != null) {
				predecessorValue = predecessor.getValue();
			}
		}

		return predecessorValue;
	}

	public boolean contains(T value) {

		return find(root, value) != null;
	}

	public boolean isEmpty() {

		return root == null;
	}

	public void clear() {

		root = null;
	}

	public T getMinimum() {

		BinaryNode<T> node = getMinimum(root);

		if(node != null) {
			return node.getValue();
		} else {
            return null;
		}
	}

	public T getMaximum() {

		BinaryNode<T> node = getMaximum(root);

		if(node != null) {
			return node.getValue();
		} else {
			return null;
		}
	}

	public int getHeight() {
        return 0;
	}

	private void add(BinaryNode<T> rootOfSubTree, BinaryNode<T> newNode) {

		if(newNode.getValue().compareTo(rootOfSubTree.getValue()) < 0) {
			if(rootOfSubTree.getLeft() == null) {
				newNode.setParent(rootOfSubTree);
				rootOfSubTree.setLeft(newNode);
			} else {
				add(rootOfSubTree.getLeft(), newNode);
			}
		} else {
			if(rootOfSubTree.getRight() == null) {
				newNode.setParent(rootOfSubTree);
				rootOfSubTree.setRight(newNode);
			} else {
				add(rootOfSubTree.getRight(), newNode);
			}
		}
	}

	private BinaryNode<T> find(BinaryNode<T> rootOfSubTree, T value) {

		if(rootOfSubTree == null || rootOfSubTree.getValue().equals(value)) {
			return rootOfSubTree;
		} else if(value.compareTo(rootOfSubTree.getValue()) < 0) {
            return find(rootOfSubTree.getLeft(), value);
		} else {
            return find(rootOfSubTree.getRight(), value);
		}

	}

	private BinaryNode<T> getMaximum(BinaryNode<T> rootOfSubTree) {

		BinaryNode<T> cursor = rootOfSubTree;

		while(cursor.getRight() != null) {
			cursor = cursor.getRight();
		}

		return cursor;
	}

	private BinaryNode<T> getMinimum(BinaryNode<T> rootOfSubTree) {

		BinaryNode<T> cursor = rootOfSubTree;

		while(cursor.getLeft() != null) {
			cursor = cursor.getLeft();
		}

		return cursor;
	}

	private BinaryNode<T> getPredecessor(BinaryNode<T> node) {

		if(node.getLeft() == null) {
			BinaryNode<T> child = node;
			BinaryNode<T> parent = child.getParent();

			while((parent != null) && (parent.getRight() != child)) {
				child = parent;
				parent = parent.getParent();
			}

			return parent;
		} else {
			return getMaximum(node.getLeft());
		}
	}

	private BinaryNode<T> getSuccessor(BinaryNode<T> node) {

		if(node.getRight() != null) {
			return getMinimum(node.getRight());
		} else {
			BinaryNode<T> child = node;
			BinaryNode<T> parent = child.getParent();

			while((parent != null) && (parent.getLeft() != child)) {
                child = parent;
				parent = parent.getParent();
			}

			return parent;

		}
	}

	private Iterator<T> getIterator(boolean descending) {

		return new Iterator<T>() {

			BinaryNode<T> nextNode = descending ? getMaximum(root) : getMinimum(root);

			@Override
			public boolean hasNext() {

				return nextNode != null;
			}

			@Override
			public T next() {

				T value = nextNode.getValue();

				nextNode = descending ? getPredecessor(nextNode) : getSuccessor(nextNode);

				return value;
			}
		};
	}

	public Iterator<T> iterator() {

        return getIterator(false);
	}

	public Iterator<T> descendingIterator() {

        return getIterator(true);
	}

	public void breadthFirstTraversal() {

		java.util.Queue<BinaryNode<T>> queue = new ArrayDeque<>();

		if(root == null) {
			return;
		}

		queue.add(root);

		do {
			BinaryNode<T> node = queue.remove();

			System.out.println(node.getValue());

			if(node.getLeft() != null) {
				queue.add(node.getLeft());
			}

			if(node.getRight() != null) {
				queue.add(node.getRight());
			}
		} while(!queue.isEmpty());
	}

	public void depthFirstTraversal() {

		Deque<BinaryNode<T>> stack = new ArrayDeque<>();

		if(root == null) {
			return;
		}

		stack.push(root);

		do {
            BinaryNode<T> node = stack.pop();

			System.out.println(node.getValue());

			if(node.getRight() != null) {
				stack.push(node.getRight());
			}

			if(node.getLeft() != null) {
				stack.push(node.getLeft());
			}

		} while(!stack.isEmpty());
	}
}
