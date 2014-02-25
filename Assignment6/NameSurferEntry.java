/*
 * File: NameSurferEntry.java
 * Name: Sudhir Khanger
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	/* Tokenizes a line
	 * and stores individual value
	 * into an array*/
	public NameSurferEntry(String line) {
		StringTokenizer tokenizer = new StringTokenizer(line);
		for (int i = 0; i <= NDECADES ; i++) {
			myArr[i] = tokenizer.nextToken();
		}
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		return myArr[0];
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		int i = (decade - START_DECADE) / 10;
		int x = -1;
		switch (i) {
			case 0:
				x = Integer.parseInt(myArr[1]);
				break;
			case 1:
				x = Integer.parseInt(myArr[2]);
				break;
			case 2:
				x = Integer.parseInt(myArr[3]);
				break;
			case 3:
				x = Integer.parseInt(myArr[4]);
				break;
			case 4:
				x = Integer.parseInt(myArr[5]);
				break;
			case 5:
				x = Integer.parseInt(myArr[6]);
				break;
			case 6:
				x = Integer.parseInt(myArr[7]);
				break;
			case 7:
				x = Integer.parseInt(myArr[8]);
				break;
			case 8:
				x = Integer.parseInt(myArr[9]);
				break;
			case 9:
				x = Integer.parseInt(myArr[10]);
				break;
			case 10:
				x = Integer.parseInt(myArr[11]);
				break;
		}
		return x;
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		// You need to turn this stub into a real implementation //
		return ("Yearly rank of " + myArr[0] + " starting 1900 following each decade is " +
				myArr[1] + " " +
				myArr[2] + " " +
				myArr[3] + " " +
				myArr[4] + " " +
				myArr[5] + " " +
				myArr[6] + " " +
				myArr[7] + " " +
				myArr[8] + " " +
				myArr[9] + " " +
				myArr[10] + " " +
				myArr[11]);
	}
	
	/* Create a private instance variable
	 *  to store name and popularity rank */
	private String[] myArr = new String[NDECADES + 1];
}

