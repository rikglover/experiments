package Kollections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArrayUtility {

	public static long calculateArraySize(List<Integer> arrayDimensions) {

		long arraySize = 1;

		for(Integer i : arrayDimensions) {
            arraySize *= i;
		}

		return arraySize;
	}

	/*
	 * This method calculates the number of elements in a dimension PER element. So, for a 3 dimensional
	 * array of size M*N*L with array indices [i, j, k] and given a call of calculateSizeOfDimension([M,N,L], 0)
	 * the result would be M*N. In this case it would be the size of each "slice" of the cube making up the
	 * 3 dimensional array.
	 */
	public static long calculateSizeOfDimension(List<Integer> arrayDimensions, int dimensionIndex) {

		long totalSize = 1;

		for(int i = arrayDimensions.size() - 1; i > dimensionIndex; i--) {
			totalSize *= arrayDimensions.get(i);
		}

		return totalSize;
	}

	public static long convertCoordinatesIntoArrayOffset(List<Integer> arrayDimensions, List<Integer> arrayIndices) {

		long index = 0;

		for(int i = 0; i < arrayIndices.size(); i++) {
			long dimensionSize = calculateSizeOfDimension(arrayDimensions, i);
			long completeDimensions = arrayIndices.get(i);

			index += dimensionSize*completeDimensions;
		}

		return index;
	}

	private static <T> void swap(T[] array, int index1, int index2) {

		T temp = array[index1];

		array[index1] = array[index2];
		array[index2] = temp;
	}

	public static <T extends Comparable> void selectionSort(T[] array) {

		for(int i = 0; i < array.length; i++) {

			int indexOfSmallest = i;

			for(int j = i + 1; j < array.length; j++) {
				if(array[j].compareTo(array[indexOfSmallest]) < 0) {
					indexOfSmallest = j;
				}
			}

			swap(array, i, indexOfSmallest);
		}
	}

	public static <T extends Comparable> void insertionSort(T[] array) {

		for(int i = 1; i < array.length; i++) {
			int j = i;

			while(j > 0 && array[j].compareTo(array[j - 1]) < 1) {
                swap(array, j, j - 1);
				j -= 1;
			}
		}
	}

	public static int findMode(int[] array) {

		if((array == null) || (array.length == 0)) {
			throw new IllegalArgumentException();
		} else {
			int mode = array[0];
			int frequencyOfMode = 1;

			Map<Integer, Integer> frequencyMap = new HashMap<>();

			for(int i = 1; i < array.length; i++) {
				Integer frequencyOfNext = frequencyMap.get(array[i]);

				if (frequencyOfNext == null) {
					frequencyMap.put(array[i], 1);
				} else {
					frequencyOfNext += 1;

					if (frequencyOfNext > frequencyOfMode) {
						mode = array[i];
						frequencyOfMode = frequencyOfNext;
					}

					frequencyMap.put(array[i], frequencyOfNext);
				}
			}

			return mode;
		}
	}

	public static <T extends Comparable> T findKthLargest(T[] nums, int k) {

		for(int i = 0; i < k; i++) {
			int indexOfLargest = i;

			for(int j = i + 1; j < nums.length; j++) {
				if(nums[j].compareTo(nums[indexOfLargest]) > 0) {
					indexOfLargest = j;
				}
			}

			swap(nums, i, indexOfLargest);
		}

		return nums[k - 1];
	}

	public static <T extends Comparable> int removeDuplicates(T[] array) {

		Set<T> characters = new HashSet<>();

		int leftIndex = 0;
		int rightIndex = array.length - 1;

		while(leftIndex <= rightIndex) {
			if(characters.contains(array[leftIndex])) {
				array[leftIndex] = array[rightIndex];
				rightIndex -= 1;
			} else {
				characters.add(array[leftIndex]);
				leftIndex += 1;
			}
		}

		return leftIndex;
	}
}
