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

	/**
	 * @return A string representation of the Token
	 */
	public String toString() {
		return "Token{type=" + type + ", value=\"" + value + "\"}";
	}

	/**
	 * @param o The object to compare to.
	 * @return True equal if o is a Token with the same type and value, false otherwise.
	 */
	public boolean equals(Object o) {
		if (o instanceof Token) {
			Token t = (Token) o;
			return t.type.equals(this.type) && t.value.equals(this.value);
		} else {
			return false;
		}
	}
}