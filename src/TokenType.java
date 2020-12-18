/**
 * Represent the possible types a token can be.
 */
public enum TokenType {
	NUMBER,
	VARIABLE,

	// unary operators
	NEGATION,

	// binary operators
	ADD,
	SUBTRACT,
	MULTIPLY,
	DIVIDE,
	EXPONENT,

	// parentheses
	OPEN_PAREN,
	CLOSE_PAREN,

	// functions
	SIN,
	COS,
	TAN,
}