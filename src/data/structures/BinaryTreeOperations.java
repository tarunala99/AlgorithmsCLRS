package data.structures;

class BinaryTree {

	BinaryTree parent;
	BinaryTree left;
	BinaryTree right;
	int data;

	public BinaryTree(BinaryTree parent, BinaryTree left, BinaryTree right, int data) {
		super();
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.data = data;
	}

}

public class BinaryTreeOperations {

	public static void main(String args[]) {
		int dataArray[] = { 6, 18, 3, 7, 17, 20, 2, 4, 13, 9 };
		BinaryTree root = new BinaryTree(null, null, null, 15);
		// pass by reference after creating the root node
		for (int i : dataArray) {
			insertNode(root, i);
		}
		BinaryTree searchNode = searchTree(root, 7);
		System.out.println("The successor value is at" + successorNode(searchNode).data);
		deleteNode(root, searchNode);
		// System.out.println(maxTree(root));
		// System.out.println(minTree(root));
		printInOrder(root);

	}

	static void insertNode(BinaryTree root, int data) {
		if (root == null) {
			root = new BinaryTree(null, null, null, data);

		} else {
			BinaryTree parent = null;
			BinaryTree node = root;
			// storing the parent info to complete the tree
			while (node != null) {
				// traversing the tree till you get the exact match
				parent = node;
				if (node.data > data) {
					node = node.left;
				} else {
					node = node.right;
				}
			}
			node = new BinaryTree(parent, null, null, data);
			if (data > parent.data) {
				parent.right = node;
			} else {
				parent.left = node;
			}
		}
	}

	public static BinaryTree searchTree(BinaryTree root, int searchData) {
		BinaryTree node = root;
		int count = 1; // Height at which the value is found
		while (node != null && node.data != searchData) {
			if (node.data > searchData) {
				node = node.left;
			} else {
				node = node.right;
			}
			count++;
		}
		if (node == null) {
			System.out.println(-1); // return when no match is found
		} else {
			System.out.println(count);
			;
		}
		return node;
	}

	public static int minTree(BinaryTree root) {
		BinaryTree node = root;
		while (node.left != null) {
			node = node.left;
		}
		return node.data;
	}

	public static int maxTree(BinaryTree root) {
		BinaryTree node = root;
		while (node.right != null) {
			node = node.right;
		}
		return node.data;
	}

	public static void printInOrder(BinaryTree root) {
		BinaryTree node = root;
		if (node == null) {
			return;
		}
		printInOrder(node.left);
		System.out.print(node.data + " ");
		printInOrder(node.right);

	}

	public static BinaryTree successorNode(BinaryTree nodeIndex) {
		BinaryTree node = nodeIndex;
		if (nodeIndex.right != null) {
			node = node.right;
			while (node.left != null) {
				node = node.left;
			}
			return node;
		}
		BinaryTree temp = node.parent;
		while (node != null && node != temp.left) {
			System.out.println("debugging value" + node.data);
			node = temp;
			temp = temp.parent;
			if (temp == null) {
				return null; // Case when it goes to the roots parent
			}
		}
		return temp;
	}

	public static void deleteNode(BinaryTree root, BinaryTree node) {
		if (node.left == null) // handles the case where left is null
		// or both are null
		{
			transplantNode(root, node, node.right);
			return;
		}
		if (node.right == null) // handles the case where right is null
		// or both are null
		{
			transplantNode(root, node, node.left);
			return;
		}
		// handles the case where both are not null
		// Case 1 where the successor of the node is the right child
		BinaryTree successorNode = successorNode(node);
		if (node.right == successorNode) {
			// implies the left child of the successor is null
			// as it would have been the successor else
			transplantNode(root, node, node.right);
			node.right.left = node.left;// node remains unchanged here
			node.left.parent = node.right;
		}
		// handle the case where the successor is present in the right subtree
		else {
			transplantNode(root, successorNode, successorNode.right);// replace the successornode
			// the successor node will never have a left child
			successorNode.right = node.right;
			successorNode.right.parent = successorNode;
			successorNode.left = node.left;
			successorNode.left.parent = successorNode;
			transplantNode(root, node, successorNode);
		}

	}

	// Finds the right direction of the child and handles the assignment
	public static void transplantNode(BinaryTree root, BinaryTree originalNode, BinaryTree updatedNode) {
		if (originalNode.parent == null) {
			root = updatedNode;
			// do something when the parent is a root
		} else {
			if (originalNode == originalNode.parent.left) {
				originalNode.parent.left = updatedNode;
			} else {
				originalNode.parent.right = updatedNode;
			}
		}
		if (updatedNode != null) {
			updatedNode.parent = originalNode.parent; // Updating the parents of the nodes
		}
	}
}
