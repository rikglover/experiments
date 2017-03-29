import Kollections.Trie;

public class KollectionsTest {

	public static void main(String[] args) {

		try {
			Trie<Integer> trie = new Trie<>();

			trie.addValue("Rik", 46);
			trie.addValue("Rikki", 50);
			trie.addValue("Happy", 51);

			System.out.println("Value of Rik =" + trie.getValue("Rik"));
			System.out.println("Value of Rikki =" + trie.getValue("Rikki"));
			System.out.println("Value of Happy =" + trie.getValue("Happy"));
			System.out.println("Value of Donkey =" + trie.getValue("Donkey"));
		} catch(Exception ex) {
			System.out.println("Unexpected error encountered: " + ex.getMessage());
		}
	}
}
