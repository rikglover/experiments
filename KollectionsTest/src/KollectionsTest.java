public class KollectionsTest {

	public static void main(String[] args) {

		try {
			KollectionProcessor processor = new KollectionProcessor();

			processor.processList();
		} catch(Exception ex) {
			System.out.println("Unexpected error encountered: " + ex.getMessage());
		}
	}
}
