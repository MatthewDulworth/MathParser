/**
 * Represents a single token in an expression.
 */
public class Token {

	static int[] precedenceMap;
	static char[] symbolMap;

	static {
		precedenceMap = new int[TokenType.values().length];
		precedenceMap[TokenType.OPEN_PAREN.ordinal()] = 1;
		precedenceMap[TokenType.CLOSE_PAREN.ordinal()] = 1;
		precedenceMap[TokenType.ADD.ordinal()] = 2;
		precedenceMap[TokenType.SUBTRACT.ordinal()] = 2;
		precedenceMap[TokenType.MULTIPLY.ordinal()] = 4;
		precedenceMap[TokenType.DIVIDE.ordinal()] = 4;
		precedenceMap[TokenType.EXPONEN.ordinal()] = 5;
		precedenceMap[TokenType.NUMBER.ordinal()] = 100;

		symbolMap = new char[TokenType.values().length];
		symbolMap[TokenType.NUMBER.ordinal()] = '.';
		symbolMap[TokenType.ADD.ordinal()] = '+';
		symbolMap[TokenType.SUBTRACT.ordinal()] = '-';
		symbolMap[TokenType.MULTIPLY.ordinal()] = '*';
		symbolMap[TokenType.DIVIDE.ordinal()] = '/';
		symbolMap[TokenType.EXPONEN.ordinal()] = '^';
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
	 * @return True if the the token is a right associative operator.
	 */
	public boolean isRightAssociative() {
		return type == TokenType.EXPONEN || type == TokenType.CLOSE_PAREN;
	}

	/**
	 * @return A string representation of the Token
	 */
	public String toString() {
		return "Token{type=" + type + ", precedence=" + getPrecedence() + "}";
	}

	/**
	 * A small string used for debugging.
	 *
	 * @return The symbol of the token.
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