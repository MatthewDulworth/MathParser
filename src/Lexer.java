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
			} else if (Character.isLetter(c)) {
				i = addFunction(i, input, tokens);
			} else if (c == '-') {
				tokens.add(subtractOrNegate(tokens));
			} else {
				tokens.add(getNonNumberToken(c));
			}
		}

		return tokens;
	}

	/**
	 * Determines if the '-' character is a NEGATION or SUBTRACT token and returns the correct
	 * token.
	 *
	 * @param tokens List of all tokens so far.
	 * @return A SUBTRACT ot NEGATION token.
	 */
	private static Token subtractOrNegate(List<Token> tokens) {

		TokenType prev;
		if (tokens.size() > 0) {
			prev = tokens.get(tokens.size() - 1).getType();
		} else {
			prev = TokenType.MULTIPLY;
		}

		// The token is subtract only if the previous token is a close parentheses, a number, or a
		// variable.
		if (prev == TokenType.CLOSE_PAREN || prev == TokenType.NUMBER ||
				  prev == TokenType.VARIABLE) {
			return new Token(TokenType.SUBTRACT);
		} else {
			return new Token(TokenType.NEGATION);
		}
	}

	/**
	 * Pulls a function from the input string and adds it to the list of tokens.
	 *
	 * @param i      The current index in the input.
	 * @param input  The input string.
	 * @param tokens The tokens found so far.
	 * @return The new index in the input.
	 * @throws IOException If the function is unrecognized.
	 */
	private static int addFunction(int i, String input, List<Token> tokens) throws IOException {
		StringBuilder value = new StringBuilder();

		while (i < input.length() && Character.isLetter(input.charAt(i))) {
			value.append(input.charAt(i));
			i++;
		}
		i--;

		String str = value.toString();

		TokenType type;
		if (str.equals("sin")) {
			type = TokenType.SIN;
		} else if (str.equals("cos")) {
			type = TokenType.COS;
		} else if (str.equals("tan")) {
			type = TokenType.TAN;
		} else {
			throw new IOException("Invalid function name: " + str);
		}

		tokens.add(new Token(type));
		return i;
	}

	/**
	 * Pulls a number from the input string and adds it to the given list of tokens.
	 *
	 * @param i      The current index in the input.
	 * @param input  The input string.
	 * @param tokens The tokens found so far.
	 * @return The new index in the input.
	 * @throws IOException If the number has tow many decimals or is only a decimal.
	 */
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

		if (value.length() == 1 && value.charAt(0) == '.') {
			throw new IOException("Floating decimal");
		}

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
		} else if (c == '*') {
			type = TokenType.MULTIPLY;
		} else if (c == '/') {
			type = TokenType.DIVIDE;
		} else if (c == '(') {
			type = TokenType.OPEN_PAREN;
		} else if (c == ')') {
			type = TokenType.CLOSE_PAREN;
		} else if (c == '^') {
			type = TokenType.EXPONENT;
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
	 * @return A string representation of the lexer.
	 */
	public String toString() {
		return tokens.toString();
	}
}