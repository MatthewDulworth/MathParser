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

	/**
	 * @return The value of the token.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @return The type of the token.
	 */
	public TokenType getType() {
		return type;
	}
}