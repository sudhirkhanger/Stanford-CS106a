/*
 * File: StoneMasonKarel.java
 * Name: Sudhir Khanger
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run() {
        while (frontIsClear()) {
        fillColumn();
        goBackToFirstStreet();
        goToNextColumn();
        }
        fillColumn();
    }

	private void fillColumn() {
        turnLeft();
        // Check if beeper is present in first corner.
        if (noBeepersPresent()) {
                putBeeper();
        }
        // Check if wall is present immidiately.
        while (frontIsClear()) {
                move();
                // Check if beeper is present at the corner.
                if (noBeepersPresent()) {
                        putBeeper();
                }
        }
	}

	private void goBackToFirstStreet() {
        turnAround();
        while (frontIsClear()) {
                move();
        }
        turnLeft();
	}

	private void goToNextColumn() {
        int columnGap = 4;
        if (frontIsClear()) {
                for (int i=0; i<columnGap; i++) {
                        move();
                }
        }
	}
}