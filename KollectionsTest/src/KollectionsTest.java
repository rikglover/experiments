import Kollections.HuffmanCoder;

public class KollectionsTest {

	public static void main(String[] args) {

		try {
			String inputString = "go go gophers";

			HuffmanCoder coder = new HuffmanCoder(inputString);

			String encodedMessage = coder.encodeMessage();

			System.out.println("Encoded Message: " + encodedMessage);

			String decodedMessage = coder.decodeMessage(encodedMessage);

			System.out.println("Decoded Message: " + decodedMessage);

		} catch(Exception ex) {
			System.out.println("Unexpected error encountered: " + ex.getMessage());
		}
	}
}
