/***
 * 
 * @apiNote Lab-05_DoublyLinkedCollection
 * 
 * @author PUT YOUR NAME AND ANYONE ELSE THAT YOU WORKED WITH HERE
 *
 */

public class DoublyLinkedCollection<E> implements CollectionInterface<E> {
	//====================================================================================== Internal Classes
	private class Node {
		E data;
		Node next;
		Node prev;
		
		public Node(E data) {
			this(data, null, null);
		}
		
		public Node(E data, Node next, Node prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
		
		//============================================= Node methods
		
		void remove() {
			if (prev != null)prev.next = next;
			if (next != null)next.prev = prev;
		}
		
		Node insertLeft(Node n) {
			n.next = this;
			n.prev = prev;
			if (prev != null) prev.next = n;
			prev = n;
			
			return n;
		}
		
		Node insertRight(Node n) {
			n.prev = this;
			n.next = next;
			if (next != null) next.prev = n;
			next = n;
			
			return n;
		}
		
		Node find(E data) {
			if (data.equals(this.data)) return this;	// base case
			if (next == null) return null;				// base case
			
			return next.find(data);
		}
		
		Node get(int i) {
			if (i == 0) return this;			// base case, cool manipulation with the i
			if (next == null) return null;		// base case
			
			return next.get(--i);
		}
		
		public String toString() {
			if (next == null) return data.toString();

			return data + ", " + next.toString();
		}

	}
		
	//====================================================================================== Properties
	private Node head;
	private Node tail;
	private int size;
	
	//====================================================================================== Constructors
	public DoublyLinkedCollection() {
		clear();
	}
	
	//====================================================================================== Methods
	@Override
	public boolean add(E e) { // add last
		return add(e, size);		
	}

	@Override
	public boolean add(E e, int index) {
		// You need to check the bounds first
		Node n = new Node(e);
		
		if (index < 0 || index > size) 
			throw new IndexOutOfBoundsException();
		
		if (isEmpty()) {
			head = tail = n;
		} else {
			if(index == size) tail = tail.insertRight(n); // head = is needed to fix the location of tail
			else if (index == 0) head = head.insertLeft(n);
			else head.get(index).insertLeft(n);
		}
		size++;
		return true;		
	}

	@Override
	public void clear() {
		head = tail = null;
		size = 0;
	}

	@Override
	public boolean contains(E o) {
		return false;		
	}

	@Override
	public E get(int index) {
		// You need to check the bounds first
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
		
		return head.get(index).data;		
	}

	@Override
	public int indexOf(E o) {
		Node tmp = head;
		int count = 0;
		
		while (tmp.data != o) {
			count++;
			tmp = tmp.next;
		}
		
		return count;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public boolean remove(int index) {
		// You need to check the bounds first
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
		
		Node n = head.get(index);
		
		if (isEmpty())
			return false;
		else
			if (index == 0) {
				head = head.next;
			} else if (index == size -1) {
				tail = tail.prev;
				tail.next = null;
			} else {
				n.remove();
			}
			size--;
			return true;
	}

	@Override
	public boolean remove(E o) {
		remove(indexOf(o));
		return true;
	}

	@Override
	public boolean remove() {
		return remove(size - 1);		// remove method will remove the last node
	}

	@Override
	public int size() {
		return size;		
	}

	@Override
	public String toString() {
		if (isEmpty()) return "[]";
		return "[" + head.toString() + "]";
	}
}
