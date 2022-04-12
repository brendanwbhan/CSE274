// Author: Brendan Han
// CSE 274
// 02/08/2022

import java.util.Arrays;
import java.util.Stack;

public class StackProblems {

	public static void main(String[] args) {

		// Do initial testing here.
		// For example, here is a basic test of the sum method:
		ArrayStack<Integer> stk = new ArrayStack<>();
		stk.push(4);
		stk.push(1);
		stk.push(2);
		stk.push(2);
		stk.push(7);
		stk.push(2);
		stk.push(8);
		stk.push(8);
		stk.push(8);
		stk.push(4);
		System.out.println(stk);
		System.out.println(copyStack(stk));
		reverseStack(stk);
		System.out.println(stk);
		
		pushUnder(stk, 3);
		System.out.println(stk);
		
		System.out.println("49? " + sum(stk));
		
		pushUnder(stk, 3);
		System.out.println(stk);
		
		stk.push(4);
		stk.push(1);
		stk.push(2);
		stk.push(2);
		stk.push(7);
		stk.push(2);
		stk.push(8);
		stk.push(8);
		stk.push(8);
		stk.push(4);
		System.out.println("31? " + sumSkipDuplicates(stk));
		System.out.println(stringToStack("41aa22728884"));	
		System.out.println(isPalindrome("mom"));
	}

	/*
	 * Computes the sum of all the numbers in the ArrayStack. 
	 * It's okay to destroy the ArrayStack in the process.
	 */
	public static int sum(ArrayStack<Integer> data) {
		int sum = 0;
		
		while (!data.isEmpty()) {
			sum += data.pop();
		}			
		return sum;
	}

	/*
	 * Puts an integer under the top item in an ArrayStack. 
	 * If the ArrayStack is empty, just put the item on the top. 
	 * For example: if 		stk starting at the top is: 7, 8, 5, 11, 
	 * 				then 	pushUnder(stk, 20) would result in: 7, 20, 8, 5, 11
	 * 
	 * For example: if 		stk is empty,  
	 * 				then 	pushUnder(stk, 20) would result in: 20
	 */
	public static void pushUnder(ArrayStack<Integer> data, int value) {
		if (!data.isEmpty()) {
			int temp = data.pop();
			data.push(value);
			data.push(temp);
		} else {
			data.push(value);
		}
	}
	
	/*
	 * Computes the sum of all the numbers in the ArrayStack. However, if two or
	 * more numbers in a row are equal, only add one of them. So, for example, if the
	 * ArrayStack contained 4, 1, 2, 2, 7, 2, 8, 8, 8, 4, then the numbers that would
	 * be added would be 4 + 1 + 2 + 7 + 2 + 8 + 4 = 28
	 */
	public static int sumSkipDuplicates(ArrayStack<Integer> data) {
		int sum = 0;
		int temp = 0;
		
		while(!data.isEmpty()) {
			temp = data.pop();
			
			if (!data.isEmpty())
				sum += (temp == data.peek()? 0:temp);
		}
		return sum + temp;
	}

	/*
	 * Puts all of the characters of a string into an ArrayStack, with the first
	 * character of the string at the bottom of the ArrayStack and the last character
	 * of the string at the top of the ArrayStack.
	 */
	public static ArrayStack<Character> stringToStack(String s) {
		ArrayStack<Character> data = new ArrayStack<>();
		
		for (int i = 0; i<s.length(); i++) {
			data.push(s.charAt(i));
		}
		
		return data;
	}

	/*
	 * Returns an exact copy of the given ArrayStack.  At the end of this method
	 * the original stack should be the same as when it started, and a new copy
	 * of that ArrayStack should be returned.  YOU MAY USE ADDITIONAL ArrayStacks AS NEEDED
	 * BUT YOU MAY NOT USE ANY OTHER DATA STRUCTURES (no arrays, ArrayList, HashSet, etc.)
	 */
	public static ArrayStack<Integer> copyStack(ArrayStack<Integer> s) {
		ArrayStack<Integer> temp = new ArrayStack<>();
		ArrayStack<Integer> copy = new ArrayStack<>();
		
		while(!s.isEmpty()) {
			temp.push(s.pop());
		}
		
		while(!temp.isEmpty()) {
			copy.push(temp.peek());
			s.push(temp.pop());
		}
		return copy;
	}

	/*
	 * Reverses a given ArrayStack, so that the top of the ArrayStack becomes the bottom
	 * and the bottom becomes the top. YOU MAY USE ADDITIONAL ArrayStack AS NEEDED
	 * BUT YOU MAY NOT USE ANY OTHER DATA STRUCTURES (no arrays, ArrayList, HashSet, etc.)
	 */
	public static void reverseStack(ArrayStack<Integer> s) {
		ArrayStack<Integer> tmp = new ArrayStack<>();
		ArrayStack<Integer> result = new ArrayStack<>();
		
		transfer(tmp,s);
		transfer(result, tmp);
		transfer(s, result);
	}
	
	// helper method
	private static void transfer(ArrayStack<Integer> s1, ArrayStack<Integer> s2) {
		while(!s2.isEmpty()) {
			s1.push(s2.pop());
		}
	}
	
	/*
	 * A palindrome reads the same forward and backward. Use an ArrayStack to
	 * determine if a given string is a palindrome. Challenge: try not to use
	 * any additional variables (except a counter for any loop). Just the given
	 * string and an ArrayStack of Characters.
	 */
	public static boolean isPalindrome(String s) {
		/*
		boolean pal = true;
		for (int i=0; i<s.length() / 2;i++) {
			if(s.charAt(i) != s.charAt(s.length()-(i+1))) {
				pal = false;
			}
		}
		return pal;
		*/
		
		// using stack
		ArrayStack<Character> stk1 = new ArrayStack<>();
		ArrayStack<Character> stk2 = new ArrayStack<>();
		
		boolean pal = true;
		for (int i=0; i<s.length() / 2;i++) {	
			stk1.push(s.charAt(i));
			stk2.push(s.charAt(s.length()-(i+1)));
			
			if(stk1.pop() != stk2.pop())
				pal = false;
		}
		return pal;
	}

}
