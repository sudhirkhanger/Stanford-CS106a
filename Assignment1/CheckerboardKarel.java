/*
 * File: CheckerboardKarel.java
 * Name: Sudhir Khanger
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
	public void run() {
		while (true) {
                 int i = 0;
                 putBeeper();
                 nextStep();
                 i++;
                 if (facingEast() && frontIsBlocked() && (leftIsBlocked())) {
                         if (i == 2) putBeeper();
                         break;
                 }
                 if (facingWest() && frontIsBlocked() && (rightIsBlocked())) {
                         if (i == 2) putBeeper();
                         break;
                 }
                 nextStep();
                 i++;
                 if (facingEast() && frontIsBlocked() && (leftIsBlocked())) {
                         if (i == 2) putBeeper();
                         break;
                 }
                 if (facingWest() && frontIsBlocked() && (rightIsBlocked())) {
                         if (i == 2) putBeeper();
                         break;
                 }
        }
	}

    /*
     * Make a choice to either move
     * or change street.
     */
	private void nextStep() {
    	 if (frontIsClear()) {
    		 move();
         } else {
        	 changeStreet();
         }
    }

    /*
     * Change direction
     * step on to next street
     */
    private void changeStreet() {
        if (facingEast() && leftIsClear()) {
             repositionFromEastToWest();
        } else if (facingWest() && rightIsClear()) {
             repositionFromWestToEast();
        }
    }

    /* Precondition:- Karel faces East.
     * Postcondition:- Karel faces West on the row above it.
     */
    private void repositionFromEastToWest() {
    	turnLeft();
    	move();
    	turnLeft();
    }

    /* Precondition:- Karel faces West.
     * Postcondition:- Karel faces East on the row above it.
     */
    private void repositionFromWestToEast() {
    	turnRight();
    	move();
    	turnRight();
        }
}