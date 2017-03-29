package Kollections;

public class BinaryNode<T> {

	private T value;

	private BinaryNode<T> left;
	private BinaryNode<T> right;
	private BinaryNode<T> parent;

	public BinaryNode() {

	}

	public BinaryNode(T value) {

		this.value = value;
	}

	public BinaryNode(T value, BinaryNode<T> left, BinaryNode<T> right) {

		this.value = value;
		this.left = left;
		this.right = right;
	}

	public T getValue() {

		return value;
	}

	public void setValue(T value) {

		this.value = value;
	}

	public BinaryNode<T> getLeft() {

		return left;
	}

	public void setLeft(BinaryNode<T> left) {

		this.left = left;
	}

	public BinaryNode<T> getRight() {

		return right;
	}

	public void setRight(BinaryNode<T> right) {

		this.right = right;
	}

	public BinaryNode<T> getParent() {

		return parent;
	}

	public void setParent(BinaryNode<T> parent) {

		this.parent = parent;
	}
}
