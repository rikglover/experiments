import Kollections.BinaryTree;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class KollectionProcessor {

	private static final int MENU_SELECTION_ADD              = 1;
	private static final int MENU_SELECTION_REMOVE           = 2;
	private static final int MENU_SELECTION_DISPLAY          = 3;
	private static final int MENU_SELECTION_DISPLAY_REVERSE  = 4;
	private static final int MENU_SELECTION_DISPLAY_HEIGHT   = 5;
	private static final int MENU_SELECTION_CONTAINS_ITEM    = 6;
	private static final int MENU_SELECTION_FIND_SUCCESSOR   = 7;
	private static final int MENU_SELECTION_FIND_PREDECESSOR = 8;
	private static final int MENU_SELECTION_DISPLAY_MIN      = 9;
	private static final int MENU_SELECTION_DISPLAY_MAX      = 10;
	private static final int MENU_SELECTION_CLEAR            = 11;
	private static final int MENU_SELECTION_QUIT             = 12;

	private BinaryTree<Integer> binaryTree = new BinaryTree<>();

	public void processList() {

		Integer selection = 0;

		String menuString = getMenu();

		while (selection != MENU_SELECTION_QUIT) {
			selection = promptForValue(menuString);

			switch (selection) {
				case MENU_SELECTION_ADD:
					processAdd();
					break;

				case MENU_SELECTION_REMOVE:
					processRemove();
					break;

				case MENU_SELECTION_DISPLAY:
					processDisplay();
					break;

				case MENU_SELECTION_DISPLAY_REVERSE:
					processDisplayReverse();
					break;

				case MENU_SELECTION_DISPLAY_HEIGHT:
					processDisplayHeight();
					break;

				case MENU_SELECTION_CONTAINS_ITEM:
					processContainsItem();
					break;

				case MENU_SELECTION_FIND_SUCCESSOR:
					processFindSuccessor();
					break;

				case MENU_SELECTION_FIND_PREDECESSOR:
					processFindPredecessor();
					break;

				case MENU_SELECTION_DISPLAY_MIN:
					processDisplayMinimum();
					break;

				case MENU_SELECTION_DISPLAY_MAX:
					processDisplayMaximum();
					break;

				case MENU_SELECTION_CLEAR:
					processClear();
					break;

				case MENU_SELECTION_QUIT:
					processQuit();
					break;

				default:
					System.out.println("\nMenu selection out of range");
					break;
			}
		}
	}

	private void processAdd() {

		int value = promptForValue("\nEnter value: ");

		binaryTree.add(value);

	}

	private void processRemove() {

		if (binaryTree.isEmpty()) {
			System.out.println("\nThe binaryTree is empty");
		} else {
			int value = promptForValue("\nEnter value to remove: ");

			System.out.println("\n");

			for (Integer value2 : binaryTree) {
				System.out.print(value2 + " ");
			}

			System.out.println();

			if (binaryTree.remove(value)) {
				System.out.println("\nRemoved " + value);
			} else {
				System.out.println("\nItem " + value + " not found in the binary tree");
			}
		}
	}

	private void processDisplay() {

		if (binaryTree.isEmpty()) {
			System.out.println("\nThe binary tree is empty");
		} else {
			System.out.print("[ ");

			binaryTree.inorder();

			System.out.println(" ]");
		}
	}

	private void processDisplayReverse() {

		if (binaryTree.isEmpty()) {
			System.out.println("\nThe binary tree is empty");
		} else {
			System.out.println();

			Iterator<Integer> descendingIterator = binaryTree.descendingIterator();

			while(descendingIterator.hasNext()) {
				System.out.print(descendingIterator.next() + " ");
			}

			System.out.println();
		}
	}

	private void processDisplayMinimum() {

		if (binaryTree.isEmpty()) {
			System.out.println("\nThe binary tree is empty");
		} else {
			System.out.println("The minimum value of the binary tree is " + binaryTree.findMinimum());
		}
	}

	private void processDisplayMaximum() {

		if (binaryTree.isEmpty()) {
			System.out.println("\nThe binary tree is empty");
		} else {
			System.out.println("The maximum value of the binary tree is " + binaryTree.findMaximum());
		}
	}

	private void processDisplayHeight() {

//		System.out.println("\nThe binary tree contains " + binaryTree.getHeight() + " items");
	}

	private void processContainsItem() {

		Integer value = promptForValue("\nEnter value to check for: ");

		if (binaryTree.contains(value)) {
			System.out.println("\nItem " + value + " found in the binary tree");
		} else {
			System.out.println("\nItem " + value + " not found in the binary tree");
		}
	}

	private void processFindSuccessor() {

		Integer value = promptForValue("\nEnter value to check for: ");

		if (!binaryTree.contains(value)) {
			System.out.println("\nItem " + value + " not found in the binary tree");
		} else {
			Integer successorValue = binaryTree.findSuccessor(value);

			if (successorValue != null) {
				System.out.println("\nThe successor of " + value + " is " + successorValue);
			} else {
				System.out.println("\n" + value + " has no successor in this binary tree");
			}
		}
	}

	private void processFindPredecessor() {

		Integer value = promptForValue("\nEnter value to check for: ");

		if (!binaryTree.contains(value)) {
			System.out.println("\nItem " + value + " not found in the binary tree");
		} else {
			Integer predecessorValue = binaryTree.findPredecessor(value);

			if (predecessorValue != null) {
				System.out.println("\nThe predecessor of " + value + " is " + predecessorValue);
			} else {
				System.out.println("\n" + value + " has no predecessor in this binary tree");
			}
		}
	}

	private void processClear() {

		if (binaryTree.isEmpty()) {
			System.out.println("\nThe binary tree is empty");
		} else {
			binaryTree.clear();
		}
	}

	private void processQuit() {

		binaryTree.clear();
		System.out.println("\nQuitting\n");
	}

	private static String getMenu() {

		StringBuilder builder = new StringBuilder();

		builder.append("\n1) Add an element to the binary tree");
		builder.append("\n2) Remove an element from the binary tree");
		builder.append("\n3) Display the binary tree");
		builder.append("\n4) Display the binary tree in reverse");
		builder.append("\n5) Display the height of the binary tree");
		builder.append("\n6) Check if the binaryTree contains an item");
		builder.append("\n7) Display the successor of an item");
		builder.append("\n8) Display the predecessor of an item");
		builder.append("\n9) Display the min item of the binary tree");
		builder.append("\n10) Display the max item of an binary tree");
		builder.append("\n11) Clear the binary tree");
		builder.append("\n12) Quit");
		builder.append("\n");
		builder.append("\nEnter selection: ");

		return builder.toString();
	}

	private static int promptForValue(String prompt) {

		int value = 0;

		while (value == 0) {
			try {
				Scanner scanner = new Scanner(System.in);

				System.out.print(prompt);

				value = scanner.nextInt();

			} catch (InputMismatchException inputMismatchException) {
				System.out.println("\nInvalid input");
				value = 0;
			}
		}

		return value;
	}
}
