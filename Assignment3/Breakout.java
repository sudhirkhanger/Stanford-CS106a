/*
 * File: Breakout.java
 * -------------------
 * Name: Sudhir Khanger
 * This file will eventually implement the game of Breakout.
 * Todo
 * 1. Fix the border bug
 * 2. Fix the paddle glue bug
 * 3. add kickers
 * 4. Improve user control over bounces
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
/** width of total brick and spaces in a row */
	private static final int BRICK_SEP_WIDTH = (NBRICKS_PER_ROW * (BRICK_WIDTH + BRICK_SEP)) - BRICK_SEP;
	
/** Delay to make the ball visible */
	private static final int DELAY = 20;
	
/** Runs the Breakout program. */
	public void run() {
		drawBricks();
	//	drawBorder();
		drawPaddle();
		for (int i = 0; i < NTURNS; i++) {
			GObject ulabel = waitForUserClickLabel();
			add(ulabel);
			waitForClick();
			remove(ulabel);
			drawBall();
			vx = rgen.nextDouble(1.0, 3.0);
			if (rgen.nextBoolean(0.5)) vx = -vx;
			while (!turnOver()) {
				moveBall();
				bouncingBall();
				collisionTest();
				pause(DELAY);
			}
		}
		gameOverLabel();
	}
	
	/* This method draws 10 rows of 10 bricks */
	private void drawBricks() {
		int y = BRICK_Y_OFFSET;
		for (int i = 0; i < NBRICKS_PER_ROW; i++) {
			int x = ((getWidth() - BRICK_SEP_WIDTH)/2);
			for (int j = 0; j < NBRICK_ROWS; j++) {
				GRect brick= new GRect(BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				switch (i) {
					case 0:
					case 1:
						brick.setFillColor(Color.RED);
						break;
					case 2:
					case 3:
						brick.setFillColor(Color.ORANGE);
						break;
					case 4:
					case 5:
						brick.setFillColor(Color.YELLOW);
						break;
					case 6:
					case 7:
						brick.setFillColor(Color.GREEN);
						break;
					case 8:
					case 9:
						brick.setFillColor(Color.CYAN);
						break;
				}
				add(brick, x, y);
				x = x + BRICK_WIDTH + BRICK_SEP;
			}
			y = y + BRICK_HEIGHT + BRICK_SEP;
		}
	}
	
	/* Draws Paddle */
	private void drawPaddle() {
		paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setLocation((getWidth() - PADDLE_WIDTH)/2, HEIGHT - PADDLE_Y_OFFSET);
		add(paddle);
		
		// Add mouse sensitivity to the paddle
		addMouseListeners();
	}
	
	/* Draws the ball in the center */
	private void drawBall() {
		ball = new GOval(2* BALL_RADIUS, 2* BALL_RADIUS);
		ball.setFilled(true);
		ball.setLocation(getWidth()/2 - BALL_RADIUS, HEIGHT/2 - BALL_RADIUS);
		add(ball);
	}
	
	/* Draw window border*/
	private void drawBorder() {
		GRect border =new GRect(((getWidth() - BRICK_SEP_WIDTH)/2) - BRICK_SEP, 0, WIDTH + BRICK_SEP, HEIGHT);
		border.sendToBack();
		add(border);
	}
	
	/* Move the ball */
	private void moveBall() {
		ball.move(vx, vy);
		add(ball);
	}
	
	/* Wait for user click label */
	private GLabel waitForUserClickLabel() {
		GLabel label = (new GLabel("Click To Continue"));
		label.setFont("SansSerif-36");
		label.setColor(Color.RED);
		label.setLocation((getWidth() - label.getWidth())/2, (HEIGHT - label.getAscent())/2);
		return(label);
	}
	
	/* Game over label */
	private void gameOverLabel() {
		GLabel label = (new GLabel("Game Over"));
		label.setFont("SansSerif-36");
		label.setColor(Color.RED);
		label.setLocation((getWidth() - label.getWidth())/2, (HEIGHT - label.getAscent())/2);
		add(label);
	}
	
	/* Bounce ball off the walls */
	private void bouncingBall() {
		if (ball.getX() > ((getWidth() + BRICK_SEP_WIDTH)/2) - (BALL_RADIUS * 2) ||      // Right Side
			ball.getX() < (getWidth() - BRICK_SEP_WIDTH)/2) {							 // Left Side
				vx = -vx;
				bounceClip.play();
			} else if (ball.getY() < 1) {
				vy = -vy;
				bounceClip.play();
			}
	}
	
	/* Makes paddle follow the mouse */
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
			if (x < ((getWidth() - BRICK_SEP_WIDTH)/2)) x = ((getWidth() - BRICK_SEP_WIDTH)/2);
			if (x > ((getWidth() + BRICK_SEP_WIDTH)/2) - PADDLE_WIDTH) x =((getWidth() + BRICK_SEP_WIDTH)/2) - PADDLE_WIDTH;
				paddle.setLocation(x, HEIGHT - PADDLE_Y_OFFSET);
				add(paddle);
	 }
	
	/* Turn over
	 *  if ball goes beyond the paddle
	 *  or if no more bricks are left
	 */
	private boolean turnOver() {
		if (ball.getY() > HEIGHT) remove(ball);
		return (ball.getY() > HEIGHT ||
				numberOfBricks < 1);
	}

	/* See if an object exist at the desired coordinates */
	private GObject getCollidingObject() {
		// Top-Left-Corner
		if (getElementAt(ball.getX(), ball.getY()) != null) { 
			return getElementAt(ball.getX(), ball.getY());
			}
		// Top-Right-Corner
		else if (getElementAt(ball.getX() + (BALL_RADIUS * 2), ball.getY()) != null) {
			return getElementAt(ball.getX() + (BALL_RADIUS * 2), ball.getY());
			} 
		// Bottom-Left Corner
		else if (getElementAt(ball.getX(), ball.getY() + (BALL_RADIUS * 2)) != null) {
			return getElementAt(ball.getX(), ball.getY() + (BALL_RADIUS * 2));			
			}
		// Bottom-Right Corner
		else if (getElementAt(ball.getX() + (BALL_RADIUS * 2), ball.getY() + (BALL_RADIUS * 2)) != null) {
			return getElementAt(ball.getX() + (BALL_RADIUS * 2), ball.getY() + (BALL_RADIUS * 2));
			} 
		else {
			return null;
		}
	} 
	
	/* When an object exist
	 * if paddle then turn around
	 * if brick then remove and turn around
	 */
	private void collisionTest() {
		GObject collider = getCollidingObject();
		if (collider != null) {
			if (collider == paddle) {
				vy = -vy;
				bounceClip.play();
			} else {
				remove(collider);
				numberOfBricks--;
				vy = -vy;
				bounceClip.play();
			}
		}
	}

	
	/* Instance variables */
	private GRect paddle;
	private GOval ball;
	private double vx;
	private double vy = 3.0;
	private int numberOfBricks = NBRICK_ROWS * NBRICKS_PER_ROW;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
}