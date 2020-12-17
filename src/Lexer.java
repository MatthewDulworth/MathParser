import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public final class Lexer {

	private Lexer() {
		throw new RuntimeException("Instantiation of this class is not allowed" +
				  ".");
	}


	public static List<Token> getTokens(String input) throws IOException {

		List<Token> tokens = new ArrayList<>();
		String value = "";

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);

			if (Character.isDigit(c)) {

			} else if (c != ' ') {
				tokens.add(getNonNumberToken(c));
			}
		}

		return tokens;
	}

	private static Token getNonNumberToken(char c) throws IOException {
		TokenType type;

		if (c == '+') {
			type = TokenType.ADD;
		} else if (c == '-') {
			type = TokenType.SUBTRACT;
		} else if (c == '*') {
			type = TokenType.MULTIPLY;
		} else if (c == '/') {
			type = TokenType.DIVIDE;
		} else if (c == '(') {
			type = TokenType.OPEN_PAREN;
		} else if (c == ')') {
			type = TokenType.CLOSE_PAREN;
		} else {
			throw new IOException("Encountered unknown character. Unicode value: \\u" +
					  Integer.toHexString(c | 0x10000).substring(1));
		}
		return new Token(type, c + "");
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
