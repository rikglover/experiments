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

	@Override
	public boolean equals(Object other) {

		if(other != null && other instanceof BinaryNode) {
			BinaryNode<T> otherNode = (BinaryNode<T>) other;

			boolean leftChildrenEqual = this.leftChild == otherNode.leftChild || this.leftChild != null && this.leftChild.equals(otherNode.leftChild);
			boolean rightChildrenEqual = this.rightChild == otherNode.rightChild || this.rightChild != null && this.rightChild.equals(otherNode.rightChild);
			boolean valuesEqual = this.value == otherNode.value || this.value != null && this.value.equals(otherNode.value);

			return valuesEqual && leftChildrenEqual && rightChildrenEqual;
		}

		return false;
	}
}
