/*
 * File: Hailstone.java
 * Name: Sudhir Khanger
 * --------------------
 * Douglas Hofstadter’s Hailstone Problem 
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
        public void run() {
                int n = readInt("Enter #: ");
                int x = 0;
                if (n == 1) println("You have a 1");
                while (n != 1) {
                        /** Check if Even or Odd and manipulate as instructed
                         * x is used to count # of steps
                         */
                        if (n % 2 == 0) {
                                println(n + " is even, so I take half:    " + (n / 2));
                                n /= 2;
                                x++;
                        } else {
                                println(n + " is odd, so I make 3n+1:     " + (3*n + 1));
                                n = 3*n + 1;
                                x++;
                        }
                }
                println("The process took " + x + " to reach 1");
        }
}