/*
 * File: MidpointFindingKarel.java
 * Name: Sudhir Khanger
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	public void run() {
        /* Variables to ascertain width
         * of Karel world
         */
        int numberOfCorners = 0;
        int backwordMoves;
        // Move to the end of world.
        while (frontIsClear()) {
                move();
                numberOfCorners++;
        }
        int midPoint = numberOfCorners / 2;
        turnAround();
        // Move back to the mid point
        for (backwordMoves=0; backwordMoves < midPoint; backwordMoves++ ) {
                move();
        }
        putBeeper();
	}

}