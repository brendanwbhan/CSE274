import java.util.Arrays;

public class Tester {

	public static void main(String[] args) {
		// Testing constructor
		WordList w1 = new WordList(5); // With capacity of 5
		WordList w2 = new WordList(); // With default capacity
		
		// Testing add()
		System.out.println("Testing add(String s): ");
		w1.add("A");
		w1.add("B");
		w1.add("C");
		w1.add("D");
		w1.add("E");
		
		w2.add("A");
		w2.add("B");
		w2.add("C");
		w2.add("D");
		w2.add("E");
		
		// Testing size()
		System.out.println("Testing size(): ");
		System.out.println(w1.size()); // Prints 5
		
		// Testing second add()
		System.out.println("Testing add(int index, String s): ");
		System.out.println(w1.add(2,"Z")); // prints false since the capacity is 5
		System.out.println(w2.add(2,"Z")); // prints true since the capacity is 10, and the new element can be add
		System.out.println(w1.toString()); // prints [A, B, C, D, E]
		System.out.println(w2.toString()); // prints [A, B, Z, C, D, E]
		
		w1.add(-1,"Z"); // throws an exception, index<0
		w1.add(6,"Z"); // throws an exception, index>size
		
		// Testing size() again
		System.out.println("Testing size() again: ");
		System.out.println(w2.size()); //Should be 6
		
		// Testing remove(int index)
		System.out.println("Testing remove(int index): ");
		System.out.println(w2.remove(2)); // prints Z, which is in index 2
		System.out.println(w2.toString()); // prints [A, B, C, D, E]
		
		System.out.println(w2.remove(-1)); // throws an exception, index < 0
		
		// Testing contains()
		System.out.println("Testing contains(): ");
		System.out.println(w2.contains("A")); // prints true
		System.out.println(w2.contains("X")); // prints false
		
		// Testing getLocation
		System.out.println("Testing getLocation(): ");
		System.out.println(w2.getLocation("A"));
		
		// Testing remove(String s)
		System.out.println("Testing remove(String s): ");
		System.out.println(w2.remove("A")); // prints true
		System.out.println(w2.toString()); // prints [B, C, D, E], A is removed
		
		// Testing get
		System.out.println("Testing get(): ");
		System.out.println(w1.get(0)); // prints A, in index 0
		System.out.println(w1.toString()); // prints [A, B, C, D, E]
				
		System.out.println(w1.remove("A")); // prints true
		System.out.println(w1.get(0)); // A is removed above, now prints B
		System.out.println(w1.toString()); // prints [B, C, D, E]
		
		// Testing set
		System.out.println("Testing set(): ");
		System.out.println(w1.set(0, "A")); // prints B that was in index 0
		System.out.println(w1.toString()); // prints [A, C, D, E], B is now replaced with A
		
		// Testing toArray()
		System.out.println("Testing toArray(): ");
		System.out.println(w2.toArray());
		
		// Testing clear()
		System.out.println("Testing clear(): ");
		w1.clear();
		System.out.println(w1.toString()); // array is cleared
		
		System.out.println("Testing remove(String s): ");
		System.out.println(w1.add("a")); 
		System.out.println(w1.add("b")); 
		System.out.println(w1.add("c")); 
	}
}
