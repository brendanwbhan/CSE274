import java.util.EmptyStackException;

/**
 * A Linked List based stack.
 * @author Brendan Han
 *
 */

public class LinkedListStack<Athlete> implements StackInterface<Athlete> {
	
	//======================================================== Inner Node Class
	public class Node {
		Athlete data = null;
		Node next = null;
		
		public Node(Athlete data) {
			this(data, null);
		}
		
		// connecting nodes
		// Node n = New Node(5);
		// Node new_n = New Node(7);
		// new_n.next = n;
		public Node(Athlete data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
	//=========================================================================
	
	// private member variable to keep track of the number of nodes
	private int size;
	private Node head;
	
	/*
	 * For the following implementations, refer to the StackInterface for 
	 * documentation on how to handle edge cases
	 * 
	 */
	@Override
	public void push(Athlete item) {
		head = new Node(item, head);
		size++;
	}

	@Override
	public Athlete pop() {
		Athlete ret = peek();
		head = head.next;
		size--;
		return ret;
	}

	@Override
	public Athlete peek() {	
		if (head == null) {
			throw new EmptyStackException();
		}
		return head.data;
	}

	@Override
	public boolean isEmpty() {
		if (head == null)
			return true;
		return false;
	}

	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	// Returns (without removing) the Athlete as the index position
	// throws IndexOutOfBoundsException if index is invalid 
	public Athlete get(int index) {
		while (index > 0 && head != null) {
			head = head.next;
			index--;
		}
		
		return peek();
	}

	// Reverses the stack
	public void reverseStack() {
		// use push and pop from one list to another
		// copy and paste
		LinkedListStack<Athlete> tmp = new LinkedListStack<>();
		LinkedListStack<Athlete> result = new LinkedListStack<>();
		
		transfer(tmp, this);
		transfer(result, tmp);
		transfer(this, result);
	}
	
	// helper method
	private void transfer(LinkedListStack<Athlete> s1,LinkedListStack<Athlete> s2) {
		while(!s2.isEmpty()) {
			s1.push(s2.pop());
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node tmp = head;
		
		while (tmp != null) {
			sb.append(tmp.data);
			sb.append("\n");
			tmp = tmp.next;
		}
		return sb.toString();
	}
}
