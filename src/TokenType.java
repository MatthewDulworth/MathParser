/**
 * Represent the possible types a token can be.
 */
public enum TokenType {
	NUMBER,

	// unary operators
	NEGATION,
	FACTORIAL,

	// binary operators
	ADD,
	SUBTRACT,
	MULTIPLY,
	DIVIDE,
	EXPONEN,

	// parentheses
	OPEN_PAREN,
	CLOSE_PAREN,

	// functions
	SIN,
	COS,
	TAN,
}