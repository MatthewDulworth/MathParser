/**
 * Represents a single token in an expression.
 */
public class Token {

	static int[] precedenceMap;
	static char[] symbolMap;

	static {
		precedenceMap = new int[7];

		// add & subtract have the same precedence
		precedenceMap[TokenType.ADD.ordinal()] = 2;
		precedenceMap[TokenType.SUBTRACT.ordinal()] = 2;

		// divide and multiply have the same precedence
		precedenceMap[TokenType.MULTIPLY.ordinal()] = 4;
		precedenceMap[TokenType.DIVIDE.ordinal()] = 4;

		// numbers always have the highest precedence
		precedenceMap[TokenType.NUMBER.ordinal()] = 100;

		symbolMap = new char[7];
		symbolMap[TokenType.ADD.ordinal()] = '+';
		symbolMap[TokenType.SUBTRACT.ordinal()] = '-';
		symbolMap[TokenType.MULTIPLY.ordinal()] = '*';
		symbolMap[TokenType.DIVIDE.ordinal()] = '/';
		symbolMap[TokenType.OPEN_PAREN.ordinal()] = '(';
		symbolMap[TokenType.CLOSE_PAREN.ordinal()] = ')';
	}

	private final TokenType type;
	private final int precedence;

	/**
	 * Creates a new Token of the given type.
	 *
	 * @param type The type of the token.
	 */
	public Token(TokenType type) {
		this.type = type;
		precedence = precedenceMap[type.ordinal()];
	}

	/**
	 * @return The type of the token.
	 */
	public TokenType getType() {
		return type;
	}

	/**
	 * @return The precedence of the type
	 */
	public int getPrecedence() {
		return precedence;
	}

	/**
	 * @return A string representation of the Token
	 */
	public String toString() {
		return "Token{type=" + type + ", precedence=" + getPrecedence() + "}";
	}

	/**
	 * @return
	 */
	public String smallString() {
		return symbolMap[type.ordinal()] + "";
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