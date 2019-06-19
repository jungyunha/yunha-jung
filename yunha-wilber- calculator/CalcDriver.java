package calcdemo;

import java.util.Scanner;

public class CalcDriver {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		CalcNav calculator = new CalcNav();
		while (true) {
			System.out.println("Please insert one of these operands: +, -, *, /");
			char option = sc.next().charAt(0); // checks if its a character and not an integer.
			if (Character.toString(option).matches("[-+*/]")) { // checks user input if its a -+*/. If not loops around
																// and tells user invalid.
				System.out.println("Enter First Number: ");
				double x = sc.nextDouble(); // double number
				System.out.println("Enter Second Number: ");
				double y = sc.nextDouble(); // double number
				switch (option) {
				case '+':
					System.out.println(calculator.sum(x, y)); // Add
					break;
				case '-':
					System.out.println(calculator.sub(x, y)); // Subtract
					break;
				case '*':
					System.out.println(calculator.multi(x, y)); // Multiply
					break;
				case '/':
					System.out.println(calculator.divide(x, y)); // Divide
					break;
				}
				break; // too break the loop. Don't know why we have to include a break here.
			} else {
				System.out.println("Invalid"); // tells user invalid input for operand
				System.out.println("Please insert correct operator.");
			}
		}
		sc.close(); // closes scanner.
	}
}