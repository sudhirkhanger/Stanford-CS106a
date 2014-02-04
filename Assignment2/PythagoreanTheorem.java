/*
 * File: PythagoreanTheorem.java
 * Name: Sudhir Khanger
 * -----------------------------
 * Ask for two integers and apply Pythagorean theorem
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
        public void run() {
                int a = readInt("Enter a:");
                int b = readInt("Enter b:");
                double y = (Math.sqrt(a*a + b*b));
                println("c =" + y);
        }
}