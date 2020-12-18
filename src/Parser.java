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

		System.out.println(input);
		TreePrinter.print(root);

		return "";
		//return evaluateSyntaxTree(root);
	}

	/**
	 * Builds an abstract Syntax Tree from the given tokens.
	 *
	 * @param tokens An iterator of a list of tokens.
	 * @return A syntax tree build from the given tokens.
	 */
	private static TreeNode buildSyntaxTree(Iterator<Token> tokens) {

		// init the tree with the first token in tokens
		TreeNode currentNode = new TreeNode(null, tokens.next());

		// for all the remaining tokens in tokens
		while (tokens.hasNext()) {
			Token newItem = tokens.next();

			// move the current node up the tree while the currentNode's precedence is higher than
			// the precedence of token
			TreeNode oldRight = null;
			while (currentNode != null && currentNode.token.getPrecedence() >= newItem.getPrecedence()) {
				oldRight = currentNode;
				currentNode = currentNode.parent;
			}

			// token now has a precedence strictly less than currentNode
			TreeNode newNode = new TreeNode(currentNode, newItem);

			// set the left child of the new node to be the right child of the current node
			if (currentNode != null) {
				newNode.setLeft(currentNode.right);
				currentNode.setRight(newNode);
			} else {
				newNode.setLeft(oldRight);
			}

			currentNode = newNode;
		}

		// find the root
		TreeNode root = currentNode;
		while (root.parent != null) {
			root = root.parent;
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
	private static class TreeNode implements TreePrinter.PrintableNode {

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

		/**
		 * Sets the left child to the given node
		 *
		 * @param newLeft The new left child.
		 */
		public void setLeft(TreeNode newLeft) {
			left = newLeft;
			if (newLeft != null) {
				newLeft.parent = this;
			}
		}

		/**
		 * Sets the right child to the given node.
		 *
		 * @param newRight The new right child.
		 */
		public void setRight(TreeNode newRight) {
			right = newRight;
			if (newRight != null) {
				newRight.parent = this;
			}
		}

		// debug methods
		public TreePrinter.PrintableNode getLeft() {
			return left;
		}

		public TreePrinter.PrintableNode getRight() {
			return right;
		}

		public String getText() {
			return token.smallString();
		}
	}
}
