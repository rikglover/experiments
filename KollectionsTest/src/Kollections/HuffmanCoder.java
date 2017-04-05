package Kollections;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.groupingBy;

public class HuffmanCoder {

	String inputString = null;

	BinaryNode<HuffmanNode> huffmanTree = null;

	Map<Character, String> codeTable = new HashMap<>();

	public HuffmanCoder(String inputString) {

		this.inputString = inputString;
		buildHuffmanTree();
		buildCodeTable(huffmanTree, "");
	}

	private void buildHuffmanTree() {

		PriorityQueue<BinaryNode<HuffmanNode>> priorityQueue = new PriorityQueue<>(comparingLong(node -> node.getValue().getFrequency()));

        inputString.chars()
			.mapToObj(x -> (char) x)
			.collect(groupingBy(Function.identity(), Collectors.counting()))
			.entrySet()
			.forEach(entry -> priorityQueue.add(new BinaryNode<>(new HuffmanNode(entry.getKey(), entry.getValue()))));

		while(priorityQueue.size() != 1) {
			BinaryNode<HuffmanNode> firstNode = priorityQueue.remove();
			BinaryNode<HuffmanNode> secondNode = priorityQueue.remove();

			Long frequency = firstNode.getValue().getFrequency() + secondNode.getValue().getFrequency();

			priorityQueue.add(new BinaryNode<>(new HuffmanNode(null, frequency), firstNode, secondNode));
		}

		huffmanTree = priorityQueue.remove();
	}

	private  void buildCodeTable(BinaryNode<HuffmanNode> huffmanSubTree, String bitString) {

		if(huffmanSubTree != null) {
			if (huffmanSubTree.getLeftChild() != null) {
				buildCodeTable(huffmanSubTree.getLeftChild(), bitString + "0");
			}

			if (huffmanSubTree.getValue().getCharacter() != null) {
				codeTable.put(huffmanSubTree.getValue().getCharacter(), bitString);
			}

			if (huffmanSubTree.getRightChild() != null) {
				buildCodeTable(huffmanSubTree.getRightChild(), bitString + "1");
			}
		}
	}

	public String encodeMessage() {

		StringBuilder builder = new StringBuilder();

		for(int i = 0; i < inputString.length(); i++) {

			Character character = inputString.charAt(i);

            builder.append(codeTable.get(character));
		}

		return builder.toString();
	}

	public String decodeMessage(String bitString) {

		BinaryNode<HuffmanNode> node = huffmanTree;

		StringBuilder builder = new StringBuilder();

		for(int i = 0; i < bitString.length(); i++) {
			Character nextBit = bitString.charAt(i);

			if(nextBit.equals('0')) {
				node = node.getLeftChild();
			} else {
				node = node.getRightChild();
			}

			if(node.getValue().getCharacter() != null) {
				builder.append(node.getValue().getCharacter());
				node = huffmanTree;
			}
		}

		return builder.toString();
	}

	public static class HuffmanNode {

		private Character character;
		private long frequency;

		public HuffmanNode(Character character, long frequency) {

			this.character = character;
			this.frequency = frequency;
		}

		public Character getCharacter() {

			return character;
		}

		public void setCharacter(Character character) {

			this.character = character;
		}

		public long getFrequency() {

			return frequency;
		}

		public void setFrequency(long frequency) {

			this.frequency = frequency;
		}
	}
}