import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Lexer to tokenize strings representing mathematical expressions.
 */
public class Lexer implements Iterable<Token> {
	private List<Token> tokens;

	/**
	 * Creates a new lexer for the given input.
	 *
	 * @param input The input to tokenize.
	 * @throws IOException If an unknown character is encountered.
	 */
	public Lexer(String input) throws IOException {
		tokens = getTokens(input);
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
	private static List<Token> getTokens(String input) throws IOException {

		// remove all whitespace from the input string
		input = input.replaceAll("\\s", "");

		List<Token> tokens = new ArrayList<>();

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);

			if (Character.isDigit(c) || c == '.') {
				i = addNumberToken(i, input, tokens);
			} else {
				tokens.add(getNonNumberToken(c));
			}
		}

		return tokens;
	}

	private static int addNumberToken(int i, String input, List<Token> tokens) throws IOException {
		// Creates a number Token from the digit at the given index in the input string and
		// all digits immediately following it.
		StringBuilder value = new StringBuilder();
		boolean foundDecimal = false;
		while (i < input.length() && (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.')) {
			if (input.charAt(i) == '.' && !foundDecimal) {
				foundDecimal = true;
			} else if (input.charAt(i) == '.') {
				throw new IOException("Too many decimals in the number");
			}

			value.append(input.charAt(i));
			i++;
		}
		i--;
		tokens.add(new NumberToken(Double.parseDouble(value.toString())));
		return i;
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
		} else if (c == '^') {
			type = TokenType.EXPONEN;
		} else {
			throw new IOException("Encountered unknown character. Unicode value: \\u" +
					  Integer.toHexString(c | 0x10000).substring(1));
		}
		return new Token(type);
	}

	/**
	 * @return An iterator over the tokens in the lexer.
	 */
	public Iterator<Token> iterator() {
		return tokens.iterator();
	}

	/**
	 * @return The number of tokens in the lexer.
	 */
	public int size() {
		return tokens.size();
	}

	/**
	 * @return
	 */
	public String toString() {
		return tokens.toString();
	}
}