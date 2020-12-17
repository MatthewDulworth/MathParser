import java.util.ArrayList;
import java.util.List;


public final class Lexer {

	private Lexer() {
		throw new RuntimeException("Instantiation of this class is not allowed" +
				  ".");
	}


	public static List<Token> getTokens(String input) {

		List<Token> tokens = new ArrayList<>();
		String value = "";

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
		}

		return tokens;
	}

	private enum TokenType {
		NUMBER,
		ADD,
		SUBTRACT,
		MULTIPLY,
		DIVIDE,
		OPEN_PAREN,
		CLOSE_PAREN,
	}

	private static class Token {
		private TokenType type;
		private String value;

		public Token(TokenType type, String value) {
			this.type = type;
			this.value = value;
		}
	}
}
