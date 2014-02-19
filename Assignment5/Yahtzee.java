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
		// implement choosing category
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
					dice[i] = cheatMode();
				}
			}
		display.displayDice(dice);
	}
	
	/*
	 * Cheatmode makes it possible
	 * to let user define the dice value
	 * in order to test game for all
	 * possible outcomes. 
	 */
	
	private int cheatMode() {
		IODialog dialog = getDialog();
		int userDiceValue = dialog.readInt("Enter the dice value");
		return(userDiceValue);
	}
		
/* Private instance variables */
	private int[] dice = new int[N_DICE];
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

}
