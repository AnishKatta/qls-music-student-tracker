package qls.music.studenttracker.driver;

import java.util.Scanner;

public class UserInputUtil {
	
	@SuppressWarnings("resource")
	public static long getLongNumber(String message) {
		Scanner numberScanner = new Scanner(System.in).useDelimiter("\n");
		String number = null;
		while (true) {
			System.out.print("Enter " + message + ": ");
			number = numberScanner.next();
			boolean isValid = isValidLong(number);
			if (isValid) {
				break;
			}
		}
		return Long.parseLong(number);
	}
	
	@SuppressWarnings("resource")
	public static int getIntNumber(String message) {
		Scanner numberScanner = new Scanner(System.in).useDelimiter("\n");
		String number = null;
		while (true) {
			System.out.print("Enter " + message + ": ");
			number = numberScanner.next();
			boolean isValid = isValidInteger(number);
			if (isValid) {
				break;
			}
		}
		return Integer.parseInt(number);
	} 
	
	private static boolean isValidLong(final String input) {
		try {
			Long.parseLong(input);
			return true;
		} catch (NumberFormatException e) {
			System.out.println("Not a valid number: " + input);
			return false;
		}
	}
	
	private static boolean isValidInteger(final String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException e) {
			System.out.println("Not a valid number: " + input);
			return false;
		}
	}
}
