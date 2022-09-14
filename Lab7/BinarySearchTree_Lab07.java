// Brendan Han
// CSE274

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/*
 	Lab-07: BinarySearchTree Implementation
 	
 	Rules:
 		1. Allow Tester to iterate through all nodes using the in-order traversal as the default.
 			This means, in Tester the following code should work for an instance of this class
 			called bst that is storing Student objects for the data:
 				
 				BinarySearchTree_Lab07<String> bst = new BinarySearchTree_Lab07<String>();
 				bst.add("Man");		bst.add("Soda");	bst.add("Flag");
 				bst.add("Home");	bst.add("Today");	bst.add("Jack");
 			
 				for(String s : bst) 
 					System.out.println(s);
*/

public class BinarySearchTree_Lab07<T> {
//====================================================================================== Properties
	private Node root;
	private int nodeCount;
	
	private Comparator<T> comp = new Comparator<T>() {

		@Override
		public int compare(T o1, T o2) {
			return ((Comparable) o1).compareTo(o2);
		}

	};
	//====================================================================================== Constructors
	public BinarySearchTree_Lab07() {
		root = null;
		nodeCount = 0;
	}
	
	// Constructor that takes an array of items and populates the tree
	public BinarySearchTree_Lab07(T[] items) {
		this();
		
		for (T t : items)
			add(t);
	}
	
	//====================================================================================== Methods
	public void add(T data) {	// Implement recursively and do NOT allow duplicates
		if (data == null)
			return;
		if (root == null)
			root = new Node(data);
		else
			recAdd(root, data);
		nodeCount++;
	}
	
	private void recAdd(Node n, T e) {
		int d = comp.compare(n.data, e);
		if (d < 0)
			if (n.right == null)
				n.right = new Node(e);
			else
				recAdd(n.right, e);
		else if (n.left == null)
			n.left = new Node(e);
		else
			recAdd(n.left, e);
	}

	// Returns the traversal of this tree as an array
	public String[] preOrder_Traversal() {
		LinkedList<T> lst = new LinkedList<T>();
		preOrder(root, lst);
		
		String[] arr = new String[lst.size()];
		int count = 0;
		
		while(!lst.isEmpty()) {
			arr[count++] = lst.remove().toString();
		}
		
		return arr;		
	}
	
	//helper method
	private void preOrder(Node node , LinkedList <T> ret ) { // NLR
		if(node == null) return;
		ret.add(node.data);
		if(node.left != null) preOrder(node.left, ret);
		if(node.right != null) preOrder(node.right, ret);
	}
	
	public String[] inOrder_Traversal() {
		LinkedList<T> lst = new LinkedList<T>();
		inOrder(root, lst);
		
		String[] arr = new String[lst.size()];
		int count = 0;
		
		while(!lst.isEmpty()) {
			arr[count++] = lst.remove().toString();
		}
		
		return arr;	
	}
	
	//helper method
	private void inOrder(Node node , LinkedList <T> ret ) { //LNR
		if(node == null) return;
		if(node.left != null) inOrder(node.left , ret);
		ret.add(node.data);
		if(node.right != null) inOrder(node.right, ret);
	}
	
	public String[] postOrder_Traversal() {
		LinkedList<T> lst = new LinkedList<T>();
		postOrder(root, lst);
		
		String[] arr = new String[lst.size()];
		int count = 0;
		
		while(!lst.isEmpty()) {
			arr[count++] = lst.remove().toString();
		}
		
		return arr;	
	}
	
	//helper method
	private void postOrder (Node node , LinkedList <T> ret ) { //LRN
		if(node == null) return;
		if(node.left != null) postOrder(node.left, ret);
		if(node.right != null) postOrder(node.right, ret);
		ret.add(node.data);
	}
	
	public String[] breadthFirst_Traversal() {
		LinkedList<T> lst = new LinkedList<T>();
		levelOrder(root, lst);
		
		String[] arr = new String[lst.size()];
		int count = 0;
		
		while(!lst.isEmpty()) {
			arr[count++] = lst.remove().toString();
		}
		
		return arr;	
	}
	
	//helper method
	private void levelOrder (Node node , LinkedList <T> ret ) {
		if (node == null ) return;
		Queue<Node> q = new LinkedList<>();
		q.add(node);
		
		while(!q.isEmpty()) {
			Node tmp = q.remove();
			ret.add(tmp.data);
			if(tmp.left != null) q.add(tmp.left);
			if(tmp.right != null) q.add(tmp.right);
		}
	}

	// Since this is a binary SEARCH tree, you should write
	// an efficient solution to this that takes advantage of the order
	// of the nodes in a BST.  Your algorithm should be, on average,
	// O(h) where h is the height of the BST.
	public boolean contains(T data) {
		return recContains(data, root);
	}
	
	// private helper method
	private boolean recContains(T target, Node node) {
		if (node == null) return false;
		
		int d = (comp.compare(target, node.data));
		
		if (d == 0) return true;
		
		return recContains(target, (d < 0 ? node.left : node.right));
	}
	
	// returns the smallest value in the tree
	// or throws an IllegalStateException() if the
	// tree is empty.  Write the recursive version 
	public T min() { return min(root); }		// this method is done for you.
			
	private T min(Node n) {	// Write this method.
		Node tmp = n;
		
		if(isEmpty()) throw new IllegalStateException();
		
		while (tmp.left != null)
			tmp = tmp.left;
		return tmp.data;
	}
	
	// returns the largest value in the tree
	// or throws an IllegalStateException() if the
	// tree is empty.  Write the recursive version
	public T max() { return max(root); }		// this method is done for you.

	private T max(Node n) {	// Write this method.
		Node tmp = n;
		
		if (isEmpty()) throw new IllegalStateException();

		while(tmp.right != null)
			tmp = tmp.right;
		
		return tmp.data;
	}
	
	// Returns whether the tree is empty
	public boolean isEmpty() {
		return root == null;
	}
	
	// returns the height of this BST. If a BST is empty, then
	// consider its height to be -1.
	public int getHeight() {
		return height(root);
	}
	
	private int height(Node node) {
		if (node == null) return -1;
		
		return Math.max(node.left != null ? height(node.left) + 1 : 0,
						node.right != null ? height(node.right) + 1 : 0);
	}

	// returns the number of leaf nodes
	public int leafCount() {
		return getLeafCount(root);
	}
	
	private int getLeafCount(Node node) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return 1;
        else
            return getLeafCount(node.left) + getLeafCount(node.right);
    }
		
	// returns the "level" of the value in a tree.
	// the root is considered level 0
	// the children of the root are level 1
	// the children of the children of the root are level 2
	// and so on.  If a value does not appear in the tree, return -1
	//              15
	//             /  \
	//            10  28
	//              \   \
	//              12  40
	//                 /
	//                30
	// In the tree above:
	// getLevel(15) would return 0
	// getLevel(10) would return 1
	// getLevel(30) would return 3
	// getLevel(8) would return -1
	public int getLevel(T n) {
		Node tmp = root;
		int count = 0;
		if (tmp == null) return -1;
		
		while (tmp != null) {
			if (comp.compare(n, tmp.data) == 0) {
				return count;
			}
			
			if (comp.compare(n, tmp.data) < 0) {
				tmp = tmp.left;
				count++;
				
			} else if (comp.compare(n, tmp.data) > 0) {
				tmp = tmp.right;
				count++;
			}
			
		}
		return count;
	}

	
	// *********************************************************
	// EXTRA CREDIT: 5pts
	// *********************************************************
	// A tree is height-balanced if at each node, the heights
	// of the node's two subtrees differs by no more than 1.
	//  Special note about null subtrees:
	//            10
	//              \
	//               20
	// Notice in this example that 10's left subtree is null,
	// and its right subtree has height 0. We would consider this
	// to be a balanced tree. If the tree is empty, return true;
	public boolean isBalanced() {
		return false;
	}

	
	//====================================================================================== Inner Node Class
	private class Node {
		private T data;
		private Node left, right;
		
		private Node(T data) {
			this.data = data;
			left = right = null;
		}
	}
}

