/*
 * File: Hangman.java
 * Name: Sudhir Khanger
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4 CS106a
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	
	public void init() { 
		 canvas = new HangmanCanvas(); 
		 add(canvas); 
		}
	
    public void run() {
    	canvas.reset();
    	hangmanWord = wordFromList();
    	String dash = dash();
    	String word = "";
    	
    	println("Welcome to Hangman!");
    	println(hangmanWord + " is " + hangmanWord.length() + " letters long.");
    	println("The word now looks like this: " + dash);
    	println("You have " + maxIncorrectGuesses + " guesses left.");
    	
    	while (gameOver() != false ) {
    		char userGuess = userGuess();
    		boolean match = false;
    		
    		/* Match user entered letter with Hangman word */
    		for (int i = 0; i < hangmanWord.length(); i++) {
    				char hangmanChar = hangmanWord.charAt(i);
    				if (userGuess == hangmanChar) {
    					/* Make sure only empty letters are manipulated */
    					if (dash.charAt(i) == '-') {
    						correctGuess++;
    						dash = dash.substring(0, i) + hangmanChar + dash.substring(i+1);
    			    		word = word + hangmanChar;
    					}
    					match = true;
    				}
    		}	
    		
    		/* Let user know of wrong guess */
    		if (match == false) {
    			println("There are no " + userGuess + "'s" + " in the word.");
    			incorrectGuesses++;
    			canvas.noteIncorrectGuess(userGuess);
    		/* Let user know of correct guess */	
    		} else {
    			println("That guess is correct.");
    			canvas.displayWord(word);
    		}
    			
    		/* Execute when only guess is left */
    		if (incorrectGuesses == maxIncorrectGuesses - 1) {
    			println("The word now looks like this: " + dash);
    			println("You have only one guess left.");
    		/* Execute after each guess */
    		} else {
    			/* Except in win or lose condition */
    			if ((incorrectGuesses == maxIncorrectGuesses) || (correctGuess == hangmanWord.length())) break;
    			println("The word now looks like this: " + dash);
    			println("You have " + (maxIncorrectGuesses - incorrectGuesses) + " guesses left.");
    		}
    	}
    	loseOrWin();
    }
    
    /* Get the random word from HangmanLexicon.java */
    private String wordFromList() {
    	HangmanLexicon word = new HangmanLexicon();
		int index = rgen.nextInt(0, word.getWordCount() - 1);
		return(word.getWord(4));
    }
    
    /* Ask user letter
     * make sure it is
     * either A to Z or a to z
     * convert a to z to upper case
     */
    private char userGuess() {
    	while (true) {
    		String str = readLine("Your guess: ");
    		
    		/* Make sure string is a single character */
    		while (str.length() != 1) {
    			str = readLine("Your guess: ");
    			println("Illegal Guess!");
    		}
    		
    		/* check if character is between A to Z or a to z. */
    		if (str.charAt(0) >= 'a' &&
    			str.charAt(0) <= 'z') {
    			return(Character.toUpperCase(str.charAt(0)));
    		} else if (str.charAt(0) >= 'A' &&
    				   str.charAt(0) <= 'Z') {
    			return(str.charAt(0));
    		} else {
    			println("Illegal Guess!");
    		}
    	}
    }
    
    /* String of dashes as long as Hangman Word */
    private String dash() {
    	String result = "";
    	for (int i = 0; i < hangmanWord.length(); i++) {
    		result = result + "-";
    	}
    	return(result);
    }
    
    /* Print the end result of the game */
    private void loseOrWin() {
    	// lose
    	if (incorrectGuesses == maxIncorrectGuesses) {
    		println("You are completely hung.");
    		println("The word was " + hangmanWord);
    		println("You lose");
    	// win
    	} else if (correctGuess == hangmanWord.length()) {
    		println("You guessed the word: " + hangmanWord);
    		println("You win");
    	}
    }
    
    /* Set conditions to either win or lose the game */
    private boolean gameOver() {
    	return(incorrectGuesses < maxIncorrectGuesses && correctGuess < hangmanWord.length());
    }

    /* Instance variables */
    private RandomGenerator rgen = RandomGenerator.getInstance();
    private String hangmanWord;
    private int incorrectGuesses = 0;
	private int maxIncorrectGuesses = 8;
	private int correctGuess = 0;
	private HangmanCanvas canvas;
}