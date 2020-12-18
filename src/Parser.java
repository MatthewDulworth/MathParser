import java.io.IOException;
import java.util.Iterator;

/**
 * Parses math expressions into abstract input trees.
 */
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

		return Double.toString(evaluateSyntaxTree(root));
	}

	/**
	 * Builds an abstract Syntax Tree from the given tokens.
	 *
	 * @param tokens An iterator of a list of tokens.
	 * @return A syntax tree build from the given tokens.
	 * @throws IOException If the parentheses
	 */
	private static TreeNode buildSyntaxTree(Iterator<Token> tokens) throws IOException {

		// init the tree with the first token in tokens
		TreeNode currentNode = new TreeNode(null, tokens.next());

		// for all the remaining tokens in tokens
		while (tokens.hasNext()) {
			Token token = tokens.next();

			// traverse up the list if the token is not an open parentheses
			TreeNode oldRight = null;
			if (token.isRightAssociative()) {
				// the currentNode to the highest node with a precedence greater than the token
				while (currentNode != null && currentNode.token.getPrecedence() > token.getPrecedence()) {
					oldRight = currentNode;
					currentNode = currentNode.parent;
				}
			} else if (token.getType() != TokenType.OPEN_PAREN) {
				// the currentNode to the highest node with a precedence greater than or equal to the
				// token
				while (currentNode != null && currentNode.token.getPrecedence() >= token.getPrecedence()) {
					oldRight = currentNode;
					currentNode = currentNode.parent;
				}
			}

			if (token.getType() == TokenType.CLOSE_PAREN) {
				currentNode = removeOpenParen(currentNode);
			} else {
				currentNode = insertToken(token, currentNode, oldRight);
			}
		}

		return findRoot(currentNode);
	}

	/**
	 * @param currentNode The current node in the AST.
	 * @return The new current node i.e. the parent of the open parentheses that was removed.
	 * @throws IOException If an opening parentheses cannot be found.
	 */
	private static TreeNode removeOpenParen(TreeNode currentNode) throws IOException {
		if (currentNode == null || currentNode.token.getType() != TokenType.OPEN_PAREN) {
			throw new IOException("Invalid Syntax: no opening parentheses found.");
		}
		TreeNode parent = currentNode.parent;
		parent.setRight(currentNode.right);
		currentNode.reset();
		return parent;
	}

	/**
	 * Inserts a token into the AST as the right child of the current node if it exists, otherwise
	 * as the new root. The right child of the current node becomes the left child of the new node.
	 * The new current node is the new node.
	 *
	 * @param token       The token to insert.
	 * @param currentNode The current node in the AST.
	 * @param oldRight    The old right child of the current node.
	 * @return The new current node i.e. the new node.
	 */
	private static TreeNode insertToken(Token token, TreeNode currentNode, TreeNode oldRight) {
		// set the left child of the new node to be the right child of the current node
		TreeNode newNode = new TreeNode(currentNode, token);
		if (currentNode != null) {
			newNode.setLeft(currentNode.right);
			currentNode.setRight(newNode);
		} else {
			newNode.setLeft(oldRight);
		}

		return newNode;
	}

	/**
	 * @param node A node in an AST.
	 * @return The root of AST that the given node is a part of.
	 */
	private static TreeNode findRoot(TreeNode node) {
		TreeNode root = node;
		while (root.parent != null) {
			root = root.parent;
		}
		return root;
	}

	/**
	 * Evaluates the math expresion represented by Abstract Syntax Tree rooted at the given node.
	 *
	 * @param node The root of the tree.
	 * @return The result of the expression.
	 */
	private static double evaluateSyntaxTree(TreeNode node) {
		if (node == null) {
			return 0;
		}

		double leftVal = evaluateSyntaxTree(node.left);
		double rightVal = evaluateSyntaxTree(node.right);

		Token token = node.token;
		if (token.getType() == TokenType.NUMBER) {
			return ((NumberToken) token).getValue();
		} else if (token.getType() == TokenType.ADD) {
			return leftVal + rightVal;
		} else if (token.getType() == TokenType.SUBTRACT) {
			return leftVal - rightVal;
		} else if (token.getType() == TokenType.MULTIPLY) {
			return leftVal * rightVal;
		} else if (token.getType() == TokenType.DIVIDE) {
			return leftVal / rightVal;
		} else if (token.getType() == TokenType.EXPONEN) {
			return Math.pow(leftVal, rightVal);
		} else {
			return 0;
		}
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

		/**
		 * Nulls out all references to parents and children.
		 */
		public void reset() {
			right = null;
			left = null;
			parent = null;
		}

		// debug methods
		public TreePrinter.PrintableNode getLeft() {
			return left;
		}

		public TreePrinter.PrintableNode getRight() {
			return right;
		}

		public String getText() {
			return token.toString();
		}
	}
}
