import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Utility class to tokenize strings representing mathematical expressions.
 */
public final class Lexer {

	/**
	 * Private constructor to prevent instantiations of Lexer.
	 *
	 * @throws RuntimeException In the case that anyone attempts to instantiate Lexer using
	 *                          reflection.
	 */
	private Lexer() throws RuntimeException {
		throw new RuntimeException("Instantiation of this class is not allowed" +
				  ".");
	}


	/**
	 * Converts an input string into a list of tokens
	 *
	 * Valid characters are:
	 * - Digits from 0 to 9.
	 * - '+', '-', '*', '/', '(', and ')'.
	 *
	 * @param input The string to tokenize.
	 * @return The to
	 * @throws IOException If an unrecognized character is encountered.
	 */
	public static List<Token> getTokens(String input) throws IOException {

		// remove all whitespace from the input string
		input = input.replaceAll("\\s", "");

		List<Token> tokens = new ArrayList<>();

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);

			if (Character.isDigit(c)) {
				// Creates a number Token from the digit at the given index in the input string and
				// all digits immediately following it.
				StringBuilder value = new StringBuilder();
				while (i < input.length() && Character.isDigit(input.charAt(i))) {
					value.append(input.charAt(i));
					i++;
				}
				i--;
				tokens.add(new NumberToken(Double.parseDouble(value.toString())));
			} else {
				tokens.add(getNonNumberToken(c));
			}
		}

		return tokens;
	}

	/**
	 * Creates a non-number Token from the given character.
	 *
	 * pre: c is not a digit
	 * is checked by getTokens
	 *
	 * @param c The character to create the token from.
	 * @return A non-number token.
	 * @throws IOException If an unknown character is encountered.
	 */
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
		return new Token(type);
	}
}