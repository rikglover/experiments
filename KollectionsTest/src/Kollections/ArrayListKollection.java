package Kollections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayListKollection<T> implements ListKollection<T> {

	private static final int DEFAULT_SIZE = 10;
	private static final int MAX_SIZE     = Integer.MAX_VALUE - 8;

	int size = 0;

	private Object[] array = new Object[DEFAULT_SIZE];

	public ArrayListKollection() {

	}

	public ArrayListKollection(int capacity) {

		resizeArray(capacity);
	}

	private void resizeArray(int capacity) {

		if(capacity > MAX_SIZE) {
			throw new IllegalArgumentException("newSize exceeds maximum size value of " + MAX_SIZE);
		}

		this.array = Arrays.copyOf(this.array, capacity);
	}

	private void verifyAndResizeArrayIfNeeded(int newSize) {

		if(newSize > array.length) {
			int newCapacity = array.length + (array.length >> 1);

			resizeArray(newCapacity);
		}
	}

	private void assertIndexBounds(int index) {

		if(index > size - 1 || index < 0) {
			throw new IndexOutOfBoundsException();
		}
	}

	private void assertIndexBoundsForAdd(int index) {

		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
	}

	private T getValueByIndex(int index) {

		return (T) array[index];
	}

	private void putValueByIndex(T value, int index) {

		array[index] = value;
	}

	@Override
	public T get(int index) {

        assertIndexBounds(index);
		return getValueByIndex(index);
	}

	@Override
	public void set(T value, int index) {

		assertIndexBounds(index);
		putValueByIndex(value, index);

	}

	@Override
	public void add(T value) {

		verifyAndResizeArrayIfNeeded(size + 1);
		putValueByIndex(value, size++);

	}

	@Override
	public void add(T value, int index) {

		assertIndexBoundsForAdd(index);
		verifyAndResizeArrayIfNeeded(size + 1);
		System.arraycopy(array, index, array, index + 1, size - index);
		size += 1;
		putValueByIndex(value, index);
	}

	@Override
	public boolean remove(Object o) {

		int index = indexOf(o);

		if(index != -1) {
			System.arraycopy(array, index + 1, array, index, size - index + 1);
			size -= 1;

			return true;
		}

		return false;
	}

	@Override
	public T remove(int index) {

		assertIndexBounds(index);

		T value = getValueByIndex(index);

		System.arraycopy(array, index + 1, array, index, size - index);
		size -= 1;

		return value;
	}

	@Override
	public boolean contains(Object o) {

		return indexOf(o) != -1;
	}

	@Override
	public int indexOf(Object o) {

		if(o == null) {
			for(int i = 0; i < size; i++) {
				if(array[i] == null) {
					return i;
				}
			}
		} else {
			for(int i = 0; i < size; i++) {
                if(array[i].equals(o)) {
                	return i;
				}
			}
		}

		return -1;
	}

	@Override
	public void clear() {

		for(int i = 0; i < size; i++) {
			array[i] = null;
		}

		size = 0;
	}

	@Override
	public boolean isEmpty() {

		return size() == 0;
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public Iterator<T> iterator() {

		return new Iterator<T>() {

			private int nextIndex = 0;

			@Override
			public boolean hasNext() {

				return nextIndex < size;
			}

			@Override
			public T next() {

				if(nextIndex == size) {
					throw new NoSuchElementException();
				}

				return getValueByIndex(nextIndex++);
			}
		};
	}
}
