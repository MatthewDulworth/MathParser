/**
 * Represents a single token in an expression.
 */
public class Token {
	private TokenType type;

	/**
	 * Creates a new Token of the given type.
	 *
	 * @param type The type of the token.
	 */
	public Token(TokenType type) {
		this.type = type;
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
		return "Token{type=" + type + "}";
	}

	/**
	 * @param o The object to compare to.
	 * @return True equal if o is a Token with the same type, false otherwise.
	 */
	public boolean equals(Object o) {
		if (o instanceof Token) {
			Token t = (Token) o;
			return t.type.equals(this.type);
		} else {
			return false;
		}
	}
}