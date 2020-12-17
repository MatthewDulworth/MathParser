import java.io.IOException;
import java.util.Iterator;

public class Parser {

	/**
	 * Takes in a string representing a mathematical expression and returns the result of the
	 * expression as a string.
	 *
	 * Ignores whitespace.
	 *
	 * @param input The string to parse.
	 * @return The result of the given math expression.
	 * @throws IOException If an invalid character is encountered.
	 */
	public static String parse(String input) throws IOException {
		Lexer lexer = new Lexer(input);
		TreeNode root = buildSyntaxTree(lexer.iterator());
		return evaluateSyntaxTree(root);
	}

	/**
	 * Builds an abstract Syntax Tree from the given tokens.
	 *
	 * @param tokens An iterator of a list of tokens.
	 * @return A syntax tree build from the given tokens.
	 */
	private static TreeNode buildSyntaxTree(Iterator<Token> tokens) {
		TreeNode currentNode = new TreeNode(null, tokens.next());
		TreeNode root = currentNode;

		while (tokens.hasNext()) {
			Token t = tokens.next();

		}

		return root;
	}

	/**
	 * Evaluates the math expresion represented by Abstract Syntax Tree rooted at the given node.
	 *
	 * @param root The root of the tree.
	 * @return The result of the expression.
	 */
	private static String evaluateSyntaxTree(TreeNode root) {
		throw new UnsupportedOperationException("evaluateSyntaxTree has not yet been implemented");
	}

	/**
	 * Nodes of a syntax tree.
	 */
	private static class TreeNode {

		private Token token;
		private TreeNode parent;
		private TreeNode left;
		private TreeNode right;

		/**
		 * Creates a new TreeNode with the given parent node and token.
		 *
		 * @param parent The parent of the node.
		 * @param token  The token to be stored in the node.
		 */
		public TreeNode(TreeNode parent, Token token) {
			this.parent = parent;
			this.token = token;
		}
	}
}
