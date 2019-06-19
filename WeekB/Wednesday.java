package com.revature.WeekB;

import java.util.*;

public class Wednesday {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter a word: ");
		String s = sc.nextLine();
		System.out.println(reverse(s));

		System.out.println("Enter if the word is a palindrome: ");
		String sw = sc.nextLine();
		System.out.println(palindrome(sw));
	}

	public static String reverse(String s) {
		if (s.length() < 2) { // returns length of s and if its less than 2 return s.
			return s;
		}
		StringBuilder result = new StringBuilder();
		for (int i = s.length() - 1; i >= 0; i--) {
			result.append(s.charAt(i)); // Appends the specified string to this character sequence.
		}
		return result.toString();
	}

	public static boolean palindrome(String s) { //Does not work.
		String reverse = "";
		int length = s.length();
		for (int i = length - 1; i >= 0; i--) {
			reverse = reverse + s.charAt(i);
			if (s.equals(reverse)) {
				System.out.println("IT IS!");
			} else {
				System.out.println("NOPE");
			}
		}
		return false;
	}
}
