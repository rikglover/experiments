package Kollections;

public class BinaryNode<T> {

	private T value;
	private BinaryNode<T> leftChild;
	private BinaryNode<T> rightChild;
	private BinaryNode<T> parent;

	public BinaryNode(T value) {

		this.value = value;
	}

	public BinaryNode(T value, BinaryNode<T> leftChild, BinaryNode<T> rightChild) {

		this.value = value;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}

	public T getValue() {

		return value;
	}

	public void setValue(T value) {

		this.value = value;
	}

	public BinaryNode<T> getLeftChild() {

		return leftChild;
	}

	public void setLeftChild(BinaryNode<T> leftChild) {

		this.leftChild = leftChild;
	}

	public BinaryNode<T> getRightChild() {

		return rightChild;
	}

	public void setRightChild(BinaryNode<T> rightChild) {

		this.rightChild = rightChild;
	}

	public BinaryNode<T> getParent() {

		return parent;
	}

	public void setParent(BinaryNode<T> parent) {

		this.parent = parent;
	}
}
