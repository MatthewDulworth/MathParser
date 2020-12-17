/**
 * Represents a single token in an expression.
 */
public class Token {
	private TokenType type;
	private String value;

	/**
	 * Creates a new token with the given value and type.
	 *
	 * @param type  The type of the token.
	 * @param value The value of the token.
	 */
	public Token(TokenType type, String value) {
		this.type = type;
		this.value = value;
	}
}