/**
 * A token that represents a number.
 */
public class NumberToken extends Token {

	private double value;

	/**
	 * Creates a new NumberToken with the given value.
	 *
	 * @param value The value of the token
	 */
	public NumberToken(double value) {
		super(TokenType.NUMBER);
		this.value = value;
	}

	/**
	 * @return The value of this token.
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @return A string representation of the NumberToken
	 */
	public String toString() {
		return value + "";
	}

	/**
	 * @param o The object to compare to.
	 * @return True if o is a NumberToken with the same value, false otherwise.
	 */
	public boolean equals(Object o) {
		if (o instanceof NumberToken) {
			return ((NumberToken) o).value == this.value;
		} else {
			return false;
		}
	}
}
