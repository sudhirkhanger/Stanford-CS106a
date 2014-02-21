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
		// Inorder to avoid re-adding score to already added cell
		initializeScore();
		// Number of rounds for each given category 
		for(int i = 0; i < N_SCORING_CATEGORIES; i++) {
			// one turn each for each player
			for (int j = 1; j <= nPlayers; j++) {
				turn(j);
			}
		}
		// compute and print the total score
		for (int k = 1; k <= nPlayers; k++) {
			totalScore(k);
		}
		display.printMessage("Congratualtions, " + playerNames[winner()] + ", you are the winner with a total score of " + score[TOTAL - 1][winner()] + "!");
	}
	
	/* Each player gets a turn in each single round.
	 * A single turn is comprised of roll, second roll,
	 * third roll and choosing a category.
	 */
	private void turn(int player){
		firstRoll(player);
		reroll();
		reroll();
		selectCategory(player);
	}
	
	/* First roll resets all dice values
	 * stores random number in dice array 
	 */
	private void firstRoll(int player) {
		display.printMessage(playerNames[player - 1] + "'s turn! Click \"Roll Dice\" button to roll the dice.");
		display.waitForPlayerToClickRoll(player);
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
	
	/* 
	 * Decides the appropriate category score
	 */
	private void selectCategory(int player) {
		display.printMessage("Please Select a category");
		int category = display.waitForPlayerToSelectCategory();	
		while (score[category - 1][player - 1] != -1) {
			display.printMessage("Choose a different category");
			category = display.waitForPlayerToSelectCategory();
		}
			if (YahtzeeMagicStub.checkCategory(dice, category)) {
				switch (category) {
					case 1:
					case 2:
					case 3:
					case 4:
					case 5:
					case 6:
						display.updateScorecard(category, player, oneToSixScore(category)); 
						score[category - 1][player - 1] = oneToSixScore(category);
						break;
					case 9: 	// Three of a Kind
					case 10:		// Four of a Kind
					case 15:	// Chance
						display.updateScorecard(category, player, sumOfAllDices());
						score[category - 1][player - 1] = sumOfAllDices();
						break;
					case 11:		// Full House
						display.updateScorecard(category, player, FULL_HOUSE_SCORE);
						score[category - 1][player - 1] = FULL_HOUSE_SCORE;
						break; 	
					case 12:	// Small Straight
						display.updateScorecard(category, player, SMALL_STRAIGHT_SCORE);
						score[category - 1][player - 1] = SMALL_STRAIGHT_SCORE;
						break;
					case 13:	// Large Straight
						display.updateScorecard(category, player, LARGE_STRAIGHT_SCORE);
						score[category - 1][player - 1] = LARGE_STRAIGHT_SCORE;
						break;
					case 14:	// Yahtzee
						display.updateScorecard(category, player, YAHTZEE_SCORE);
						score[category - 1][player - 1] = YAHTZEE_SCORE;
						break;
				}
			} else {
				display.updateScorecard(category, player, WRONG_CATEGORY_SCORE);
				score[category - 1][player - 1] = WRONG_CATEGORY_SCORE;
				}
		/* Update total score*/
			display.updateScorecard(TOTAL, player, categoryScore(player));
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
	
	/* Total score including upper, lower and bonus */
	private void totalScore(int player) {
		int bonus = 0;
		if (upperScore(player) >= UPPERSCORE_SCORE) bonus = BONUS_SCORE;
		int total = upperScore(player) + lowerScore(player) + bonus;
		score[TOTAL -1][player - 1] = total;
		display.updateScorecard(UPPER_SCORE, player, upperScore(player)); 	// upper score
		display.updateScorecard(UPPER_BONUS, player, bonus);				// upper bonus
		display.updateScorecard(LOWER_SCORE, player, lowerScore(player));	// lower score
		display.updateScorecard(TOTAL, player, total);						// total
	}
	
	/* Total sum of all category scores*/
	private int categoryScore(int player) {
		int total = 0;
		for(int i = 0; i < N_CATEGORIES; i++) {
			if(score[i][player - 1] != -1) total += score[i][player - 1];
		}
		return(total);
	}
	
	/* Sum of categories between One and Six */
	private int upperScore(int player) {
		int total = 0;
			for(int i = ONES - 1; i < SIXES; i++) {
				total += score[i][player - 1];
			}
			return(total);
	}
	
	/* Sum of categories between Three of a kind and Chance */
	private int lowerScore(int player) {
		int total = 0;
			for(int i = THREE_OF_A_KIND - 1; i < CHANCE; i++) {
				total += score[i][player - 1];
			}
			return(total);
	}
	
	private int winner() {
		int max = 0;
		int winner = 0;
		if(nPlayers != 1) {
			for(int i = 0; i < MAX_PLAYERS; i++) {
				if (score[TOTAL - 1][i] > max) {
					max = score[TOTAL -1][i];
					winner = i;
				}
			}
		}
		return(winner);
	}
	
	private void initializeScore() {
		for(int i = 0; i < N_CATEGORIES; i++) {
			for(int j = 0; j < MAX_PLAYERS; j++)
				score[i][j] = -1;
		}
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
	private int[][] score = new int[N_CATEGORIES][MAX_PLAYERS];
	private int[] dice = new int[N_DICE];
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
}
