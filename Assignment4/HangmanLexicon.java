/*
 * File: HangmanLexicon.java
 * Name: Sudhir Khanger
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.io.*;
import java.util.*;

public class HangmanLexicon {
	
	private ArrayList<String> file() {
		ArrayList<String> wordArr = new ArrayList<String>();
		try {
			BufferedReader file = new BufferedReader(new FileReader("ShorterLexicon.txt"));
			while (true) {
				if (file.readLine() == null) break;
				wordArr.add(file.readLine());
			}
			file.close();
		} catch (IOException ex) {
			throw new ErrorException (ex);
		}
		return(wordArr);
	}
/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return(file().size());
	}
	
/** Returns the word at the specified index. */
	public String getWord(int index) {
		return(file().get(index));
	}
}
