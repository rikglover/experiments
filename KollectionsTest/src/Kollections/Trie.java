package Kollections;

import java.util.HashMap;
import java.util.Map;

public class Trie<T> {

	private TrieNode<T> root = new TrieNode<>();

	public void addValue(String key, T value) {

		TrieNode<T> p = root;

		int i = 0;

		while(p.getChild(key.charAt(i)) != null && i < key.length()) {
			p = p.getChild(key.charAt(i));
			i += 1;
		}

		while(i < key.length()) {
			p.setChild(key.charAt(i));
            p = p.getChild(key.charAt(i));
			i += 1;
		}

		p.setValue(value);
	}

	public T getValue(String key) {

		TrieNode<T> p = root;

		int i = 0;

		while(p != null && i < key.length()) {
			TrieNode<T>  node = p.getChild(key.charAt(i));

			if(node != null) {
				p = p.getChild(key.charAt(i));
			} else {
				return null;
			}

			i += 1;
		}

		return p.getValue();
	}

	private static class TrieNode<T> {

		private T value;

		private Map<Character, TrieNode> children = new HashMap<>();

		public TrieNode() {

			this.value = null;
		}

		public T getValue() {

			return value;
		}

		public void setValue(T value) {

			this.value = value;
		}

		public Map<Character, TrieNode> getChildren() {

			return children;
		}

		public void setChildren(Map<Character, TrieNode> children) {

			this.children = children;
		}

		public TrieNode<T> getChild(Character character) {

			return this.children.get(character);
		}


		public void setChild(Character character) {

			this.children.put(character, new TrieNode<>());
		}
	}
}
