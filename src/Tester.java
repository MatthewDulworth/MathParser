import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Tester {


	public static void main(String[] args) throws IOException {

//		lexerTests();
		System.out.println(Parser.parse("3 ^ 5! * 3 ^3 - 2 + cos(3! ^2)"));
	}

	private static void lexerTests() throws IOException {
		String in;
		Lexer lexer;

		in = "3 4. 56 + (-4 / 2) * 5 - -24534 ^ 12.3 + tan(3^-5. 0) * .   0";
		Token[] arr = {
				  new NumberToken(34.56),
				  new Token(TokenType.ADD),
				  new Token(TokenType.OPEN_PAREN),
				  new Token(TokenType.NEGATION),
				  new NumberToken(4),
				  new Token(TokenType.DIVIDE),
				  new NumberToken(2),
				  new Token(TokenType.CLOSE_PAREN),
				  new Token(TokenType.MULTIPLY),
				  new NumberToken(5),
				  new Token(TokenType.SUBTRACT),
				  new Token(TokenType.NEGATION),
				  new NumberToken(24534),
				  new Token(TokenType.EXPONEN),
				  new NumberToken(12.3),
				  new Token(TokenType.ADD),
				  new Token(TokenType.TAN),
				  new Token(TokenType.OPEN_PAREN),
				  new NumberToken(3),
				  new Token(TokenType.EXPONEN),
				  new Token(TokenType.NEGATION),
				  new NumberToken(5.0),
				  new Token(TokenType.CLOSE_PAREN),
				  new Token(TokenType.MULTIPLY),
				  new NumberToken(0.0),
		};
		List<Token> exp = new ArrayList<>(Arrays.asList(arr));
		lexer = new Lexer(in);
		System.out.println(lexer);
		test("Tests Lexer.getTokens on full expression ", lexerListEqual(lexer, exp));
	}

	private static boolean lexerListEqual(Lexer lexer, List<Token> list) {

		Iterator<Token> i = lexer.iterator();
		Iterator<Token> j = list.iterator();

		boolean result = true;
		while (i.hasNext() && j.hasNext() && result) {
			result = j.next().equals(i.next());
		}
		return result && !i.hasNext() && !j.hasNext();
	}

	private static void test(String test, boolean condition) {
		String s = (condition) ? "PASSED" : "FAILED";
		System.out.println(test + ": " + s);
	}
}
