/*
 * File: HangmanCanvas.java
 * Name: Sudhir Khanger
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		gameSetup();
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		GLabel correctWord = new GLabel(word);
		int x = getWidth() / 10;
		int y = getHeight() / 10;
		correctWord.setLocation(1.2*x, 9.25*y);
		correctWord.setFont("SansSerif-bold-20");
		GObject element = getElementAt(1.2*x, 9.25*y);
		if (element != null) remove(element);
		add(correctWord);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter, int num) {
		result = result + letter;
		GLabel incorrectLetter = new GLabel(result);
		int x = getWidth() / 10;
		int y = getHeight() / 10;
		incorrectLetter.setLocation(1.2*x, 9.5*y);
		add(incorrectLetter);
		
		/* create the hangman figure */
		switch(num) {
			case 1:
				// head
				head();
				break;
			case 2:
				// body
				body();
				break;
			case 3:
				// left arm
				leftArm();
				break;
			case 4:
				// right arm
				rightArm();
				break;
			case 5:
				// left leg
				leftLeg();
				break;
			case 6:
				// right leg
				rightLeg();
				break;
			case 7:
				// left foot
				leftFoot();
				break;
			case 8:
				// right foot
				rightFoot();
				break;
		}
		
	}
	
	private void gameSetup() {
		int x = (getWidth() - (BEAM_LENGTH + UPPER_ARM_LENGTH))/2;
		int y = (getHeight() + (ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH + LEG_LENGTH) )/2;
		int y1 = (getHeight() - (ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH + LEG_LENGTH) )/2;
		// add scaffold
		add(new GLine(x, y, x, y1));
		// add beam
		int x1 = x + BEAM_LENGTH;
		add(new GLine(x, y1, x1, y1));
		// add rope
		int y2 = y1 + ROPE_LENGTH;
		add(new GLine(x1, y1, x1, y2));
	}
	
	private void head() {
		int x = ((getWidth() - (BEAM_LENGTH + UPPER_ARM_LENGTH))/2) + BEAM_LENGTH - HEAD_RADIUS;
		int y = ((getHeight() - (ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH + LEG_LENGTH) )/2) + ROPE_LENGTH;
		add(new GOval(x, y, 2 * HEAD_RADIUS, 2 * HEAD_RADIUS));
	}
	
	private void body() {
		int x = ((getWidth() - (BEAM_LENGTH + UPPER_ARM_LENGTH))/2) + BEAM_LENGTH;
		int y = ((getHeight() - (ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH + LEG_LENGTH) )/2) + ROPE_LENGTH  + (2 * HEAD_RADIUS);
		int y1 = ((getHeight() - (ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH + LEG_LENGTH) )/2) + ROPE_LENGTH  + (2 * HEAD_RADIUS) + BODY_LENGTH;
		add(new GLine(x, y, x, y1));
		int x1 = x - (HIP_WIDTH/2);
		int x2 = x + (HIP_WIDTH/2);
		add(new GLine(x1, y1, x2, y1));
	}
	
	private void leftArm() {
		int x = ((getWidth() - (BEAM_LENGTH + UPPER_ARM_LENGTH))/2) + BEAM_LENGTH;
		int y = ((getHeight() - (ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH + LEG_LENGTH) )/2) + ROPE_LENGTH  + (2 * HEAD_RADIUS) + ARM_OFFSET_FROM_HEAD;
		int x1 = x - UPPER_ARM_LENGTH;
		add(new GLine(x, y, x1, y));
		int y1 = y + LOWER_ARM_LENGTH;
		add(new GLine(x1, y, x1, y1));
		
	}
	
	private void rightArm() {
		int x = ((getWidth() - (BEAM_LENGTH + UPPER_ARM_LENGTH))/2) + BEAM_LENGTH;
		int y = ((getHeight() - (ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH + LEG_LENGTH) )/2) + ROPE_LENGTH  + (2 * HEAD_RADIUS) + ARM_OFFSET_FROM_HEAD;
		int x1 = x + UPPER_ARM_LENGTH;
		add(new GLine(x, y, x1, y));
		int y1 = y + LOWER_ARM_LENGTH;
		add(new GLine(x1, y, x1, y1));
	}
	
	private void leftLeg() {
		int x = ((getWidth() - (BEAM_LENGTH + UPPER_ARM_LENGTH))/2) + BEAM_LENGTH - (HIP_WIDTH/2);
		int y = ((getHeight() - (ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH + LEG_LENGTH) )/2) + ROPE_LENGTH  + (2 * HEAD_RADIUS) + BODY_LENGTH;
		int y1 = ((getHeight() - (ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH + LEG_LENGTH) )/2) + ROPE_LENGTH  + (2 * HEAD_RADIUS) + BODY_LENGTH + LEG_LENGTH;
		add(new GLine(x, y, x, y1));
	}
	
	private void rightLeg() {
		int x = ((getWidth() - (BEAM_LENGTH + UPPER_ARM_LENGTH))/2) + BEAM_LENGTH + (HIP_WIDTH/2);
		int y = ((getHeight() - (ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH + LEG_LENGTH) )/2) + ROPE_LENGTH  + (2 * HEAD_RADIUS) + BODY_LENGTH;
		int y1 = ((getHeight() - (ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH + LEG_LENGTH) )/2) + ROPE_LENGTH  + (2 * HEAD_RADIUS) + BODY_LENGTH + LEG_LENGTH;
		add(new GLine(x, y, x, y1));
	}
	
	private void leftFoot() {
		int x = ((getWidth() - (BEAM_LENGTH + UPPER_ARM_LENGTH))/2) + BEAM_LENGTH - (HIP_WIDTH/2);
		int y = ((getHeight() - (ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH + LEG_LENGTH) )/2) + ROPE_LENGTH  + (2 * HEAD_RADIUS) + BODY_LENGTH + LEG_LENGTH;
		int x1 = x - FOOT_LENGTH;
		add(new GLine(x, y, x1, y));
		}

	private void rightFoot() {
		int x = ((getWidth() - (BEAM_LENGTH + UPPER_ARM_LENGTH))/2) + BEAM_LENGTH + (HIP_WIDTH/2);
		int y = ((getHeight() - (ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH + LEG_LENGTH) )/2) + ROPE_LENGTH  + (2 * HEAD_RADIUS) + BODY_LENGTH + LEG_LENGTH;
		int x1 = x + FOOT_LENGTH;
		add(new GLine(x, y, x1, y));
	}
	
/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	String result = "";

}
