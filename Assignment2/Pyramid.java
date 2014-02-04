/*
 * File: Pyramid.java
 * Name: Sudhir Khanger
 * ------------------
 * Create pyramid of boxes with desirable width and height
 * center in the window and reduce a box per-step
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {
        
        public void run() {
                /*
                 * locate the first brick
                 * one-forth of the width
                 * so that pyramid is at
                 * center.
                 */
                int x = getWidth()/4;
                int y = getHeight();
                /*
                 * i loop and y moves
                 * subsequent rows to the top
                 */
                for (int i = BRICKS_IN_BASE; i > 0 ; i-- ) {
                        y -= BRICK_HEIGHT;
                        /*
                         * j and x are used to
                         * create row of bricks.
                         */
                        for (int j = 0; j < i; j++) {
                                add (new GRect( x, y, BRICK_WIDTH, BRICK_HEIGHT));
                                x += BRICK_WIDTH;
                        }
                        /*
                         * following brings back the
                         * brick to starting point
                         * moving it half brick forward.
                         */
                        x -= (i * BRICK_WIDTH);
                        x += (BRICK_WIDTH / 2);
                }
        }
        
        /** Width of each brick in pixels */
        private static final int BRICK_WIDTH = 30;

        /** Width of each brick in pixels */
        private static final int BRICK_HEIGHT = 12;

        /** Number of bricks in the base of the pyramid */
        private static final int BRICKS_IN_BASE = 14;
        
}

