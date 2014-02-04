/*
 * File: CollectNewspaperKarel.java
 * Name: Sudhir Khanger
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {
	public void run() {
        gotoNewspaper();
        pickBeeper();
        gotoStart();
	}
	
	/* This method makes Karel to
	 * to front door where news paper
	 * is in the given world.
	 */
	private void gotoNewspaper() {
        move();
        move();
        turnRight();
        move();
        turnLeft();
        move();
	}
	/* This method takes Karel
	 * back to start place.
	 */
	private void gotoStart() {
        turnAround();
        move();
        move();
        move();
        turnRight();
        move();
        turnRight();
	}
}
