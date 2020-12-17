import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Tester {


	public static void main(String[] args) throws IOException {
		lexerTests();
	}

	private static void lexerTests() throws IOException {

		String in = "3 4 + (4 / 2) * 5 - 24534";
		Token[] arr = {
				  new NumberToken(34),
				  new Token(TokenType.ADD),
				  new Token(TokenType.OPEN_PAREN),
				  new NumberToken(4),
				  new Token(TokenType.DIVIDE),
				  new NumberToken(2),
				  new Token(TokenType.CLOSE_PAREN),
				  new Token(TokenType.MULTIPLY),
				  new NumberToken(5),
				  new Token(TokenType.SUBTRACT),
				  new NumberToken(24534),
		};
		List<Token> exp = new ArrayList<>(Arrays.asList(arr));
		Lexer lexer = new Lexer(in);
		test("Tests Lexer.getTokens on full expression ", lexerListEqual(lexer, exp));
	}

	private static boolean lexerListEqual(Lexer lexer, List<Token> list) {
		Token t = lexer.getNextToken();
		Iterator<Token> iter = list.iterator();

		boolean result = true;
		while (t != null && iter.hasNext()) {
			result = result && iter.next().equals(t);
			t = lexer.getNextToken();
		}
		return result && !iter.hasNext() && t == null;
	}

	private static void test(String test, boolean condition) {
		String s = (condition) ? "PASSED" : "FAILED";
		System.out.println(test + ": " + s);
	}
}
