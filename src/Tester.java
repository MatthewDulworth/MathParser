import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tester {


	public static void main(String[] args) throws IOException {
		lexerTests();
	}

	private static void lexerTests() throws IOException {
		String in = "3 4 + (4 / 2) * 5 - 24534";
		Token[] arr = {
				  new Token(TokenType.NUMBER, "34"),
				  new Token(TokenType.ADD, "+"),
				  new Token(TokenType.OPEN_PAREN, "("),
				  new Token(TokenType.NUMBER, "4"),
				  new Token(TokenType.DIVIDE, "/"),
				  new Token(TokenType.NUMBER, "2"),
				  new Token(TokenType.CLOSE_PAREN, ")"),
				  new Token(TokenType.MULTIPLY, "*"),
				  new Token(TokenType.NUMBER, "5"),
				  new Token(TokenType.SUBTRACT, "-"),
				  new Token(TokenType.NUMBER, "24534"),
		};
		List<Token> exp = new ArrayList<>(Arrays.asList(arr));
		List<Token> res = Lexer.getTokens(in);
		System.out.println(exp);
		System.out.println(res);
		test("Tests Lexer.getTokens on full expression ", exp.equals(res));
	}

	private static void test(String test, boolean condition) {
		String s = (condition) ? "PASSED" : "FAILED";
		System.out.println(test + ": " + s);
	}
}
