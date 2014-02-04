/*
 * File: FindRange.java
 * Name: Sudhir Khanger
 * --------------------
 * Ask for integers and find smallest
 * and largest value.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	public void run() {
		int x = readInt("?");
		/**store values to compare
		 * with the previous ones
		 */
		int largestInt = x;
		int smallestInt = x;
		if (x == 0) {
			println("Zer0 has been enterned....TERMINATING");
		} else {
			while (x != SENTINEL) {
				/** Check & Store largest value */
				if (x > largestInt) {
					largestInt = x;
				}
					/** Check & Store smallest value */
					if (x < smallestInt) {
						smallestInt = x;
				}
				x = readInt("?");
			}
			println("Smallest #:" + smallestInt);
			println("Largerst #:" + largestInt);
		}
	}
	
	// Sentinel Value
	private static final int SENTINEL = 0;
}