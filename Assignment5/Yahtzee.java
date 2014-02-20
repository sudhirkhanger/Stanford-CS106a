/*
 * File: Yahtzee.java
 * Name: Sudhir Khanger
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		// Number of rounds for each given category 
		for(int i = 0; i < N_SCORING_CATEGORIES; i++) {
			turn();
		}
	}
	
	/* Each player gets a turn in each single round.
	 * A single turn is comprised of roll, second roll,
	 * third roll and choosing a category.
	 */
	private void turn(){
		firstRoll();
		reroll();
		reroll();
		selectCategory();
	/*	int category = display.waitForPlayerToSelectCategory();
		if (YahtzeeMagicStub.checkCategory(dice, category)) {
			display.updateScorecard(category, nPlayers, 50);
		} else {
			display.updateScorecard(category, nPlayers, WRONG_CATEGORY_SCORE);
		}
	*/
	}
	
	/* First roll resets all dice values
	 * stores random number in dice array 
	 */
	private void firstRoll() {
		display.printMessage(playerNames[0] + "'s turn! Click \"Roll Dice\" button to roll the dice.");
		display.waitForPlayerToClickRoll(nPlayers);
			for(int i = 0; i < N_DICE; i++) {
				dice[i] = rgen.nextInt(1, 6);
			}
		display.displayDice(dice);
	}
	
	/* Second roll makes it possible
	 * to only roll chosen dices
	 * and store dice values in appropriate
	 * dice[] location
	 */
	private void reroll() {
		display.printMessage("Select the dice you wish to re-roll and click \"Roll Again\"");
		display.waitForPlayerToSelectDice();
			for(int i = 0; i < N_DICE; i++) {
				if (display.isDieSelected(i)) {
					/* Following will be in the main program.
					dice[i] = rgen.nextInt(1, 6);
					*/
					// cheat mode
					dice[i] = cheatMode(i);
				}
			}
		display.displayDice(dice);
	}
	
	private void selectCategory() {
		int category = display.waitForPlayerToSelectCategory();
			if (YahtzeeMagicStub.checkCategory(dice, category)) {
				switch (category) {
					case 1:
					case 2:
					case 3:
					case 4:
					case 5:
					case 6:
						display.updateScorecard(category, nPlayers, oneToSixScore(category)); 
						score[category - 1] = oneToSixScore(category);
						break;
					case 7: 	// Three of a Kind
					case 8:		// Four of a Kind
					case 13:	// Chance
						display.updateScorecard(category, nPlayers, sumOfAllDices());
						score[category - 1] = sumOfAllDices();
						break;
					case 9:		// Full House
						display.updateScorecard(category, nPlayers, FULL_HOUSE_SCORE);
						score[category - 1] = FULL_HOUSE_SCORE;
						break; 	
					case 10:	// Small Straight
						display.updateScorecard(category, nPlayers, SMALL_STRAIGHT_SCORE);
						score[category - 1] = SMALL_STRAIGHT_SCORE;
						break;
					case 11:	// Large Straight
						display.updateScorecard(category, nPlayers, LARGE_STRAIGHT_SCORE);
						score[category - 1] = LARGE_STRAIGHT_SCORE;
						break;
					case 12:	// Yahtzee
						display.updateScorecard(category, nPlayers, YAHTZEE_SCORE);
						score[category - 1] = YAHTZEE_SCORE;
						break;
				}
			} else {
				display.updateScorecard(category, nPlayers, WRONG_CATEGORY_SCORE);
				score[category - 1] = WRONG_CATEGORY_SCORE;
			}
	}
	
	/* Counts all instances of a category
	 * primarily used for category 1-6
	 */
	private int oneToSixScore(int category) {
		int x = 0;
		for (int i = 0; i < N_DICE; i++) {
			if (dice[i] == category) x += category;		
		}
		return(x);
	}
	
	/* Simple method to count all values in dice array */
	private int sumOfAllDices() {
		int total = 0;
		for (int i = 0; i < N_DICE; i++) {
			total = total + dice[i];
		}
		return(total);
	}
	
	/* Cheatmode makes it possible
	 * to let user define the dice value
	 * in order to test game for all
	 * possible outcomes. 
	 */
	private int cheatMode(int i) {
		IODialog dialog = getDialog();
		int x = 0;
		while(true) {
			x = dialog.readInt("Enter the dice value for " + (i+1));
			if (x >= 1 && x <= 6) break;
		}
		return(x);
	}
		
/* Private instance variables */
	private int[] score = new int[N_SCORING_CATEGORIES];
	private int[] dice = new int[N_DICE];
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

}
