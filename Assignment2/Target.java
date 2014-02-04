/*
 * File: Target.java
 * Name: Sudhir Khanger
 * -----------------
 * Draw Target sign.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {        
        public void run() {
                // coordinates to center the circles.
                int x = getWidth()/2;
                int y = getHeight()/2;
                /* 1 inch = 72 pixels
                 * and move origin by half the length of radius
                 * in both direction to offset for the
                 * different size of circles.
                 */
                GOval outer = (new GOval(x-36, y-36, 72,72));
                outer.setFilled(true);
                outer.setFillColor(Color.RED);
                /** 0.65 inches == 46.8 pixels */
                GOval middle = (new GOval(x-23.4, y-23.4, 46.8, 46.8));
                middle.setFilled(true);
                middle.setFillColor(Color.WHITE);
                /** 0.3 inches == 21.6 */
                GOval inner = (new GOval(x-10.8, y-10.8, 21.6, 21.6));
                inner.setFilled(true);
                inner.setFillColor(Color.RED);
                /** Add all circles to the canvas */
                add(outer);
                add(middle);
                add(inner);
        }
}