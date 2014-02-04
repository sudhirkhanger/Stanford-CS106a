/*
 * File: ProgramHierarchy.java
 * Name: Sudhir Khanger
 * ---------------------------
 * Draw Program class hierarchy. Might be off due to
 * problems in applet size.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {
                        
        public void run() {
                /** Height & Width of the window */
                double width = getWidth();
                double height = getHeight();
                /** ConsoleProgram Box */
                add(new GRect(width/2 - BOX_WIDTH/2, height/2 - BOX_HEIGHT/2, BOX_WIDTH, BOX_HEIGHT));
                GLabel consoleProgram = (new GLabel("ConsoleProgram"));
                consoleProgram.setLocation(width/2 - BOX_WIDTH/2 + consoleProgram.getWidth()/8, height/2 - BOX_HEIGHT/2 + consoleProgram.getAscent());
                add(consoleProgram);
                
                /** GraphicalProgram Box */
                add(new GRect((width/2 + BOX_GAP + BOX_WIDTH) - BOX_WIDTH/2, height/2 - BOX_HEIGHT/2, BOX_WIDTH, BOX_HEIGHT));
                GLabel graphicalProgram = (new GLabel("GraphicalProgram"));
                graphicalProgram.setLocation(width/2 + BOX_GAP + BOX_WIDTH - BOX_WIDTH/2 + graphicalProgram.getWidth()/8, height/2 - BOX_HEIGHT/2 + graphicalProgram.getAscent());
                add(graphicalProgram);
                
                /** DialogProgram Box */
                add(new GRect((width/2 - BOX_GAP - BOX_WIDTH) - BOX_WIDTH/2, height/2 - BOX_HEIGHT/2, BOX_WIDTH, BOX_HEIGHT));
                GLabel dialogProgram = (new GLabel("DialogProgram"));
                dialogProgram.setLocation(width/2 - BOX_GAP - BOX_WIDTH - BOX_WIDTH/2 + dialogProgram.getWidth()/8, height/2 - BOX_HEIGHT/2 + dialogProgram.getAscent());
                add(dialogProgram);
                
                /** Program Box*/
                add(new GRect(width/2 - BOX_WIDTH/2, height/2 - TOP_BOX_GAP - BOX_HEIGHT/2, BOX_WIDTH, BOX_HEIGHT));
                GLabel program = (new GLabel("Program"));
                program.setLocation(width/2 - BOX_WIDTH/2 + program.getWidth()/2 , height/2 - TOP_BOX_GAP - BOX_HEIGHT/2 + program.getAscent());
                add(program);
                
                /** Lines */
                add((new GLine(width/2, height/2 - TOP_BOX_GAP + BOX_HEIGHT/2, width/2 - BOX_GAP - BOX_WIDTH, height/2 - BOX_HEIGHT/2)));
                add((new GLine(width/2, height/2 - TOP_BOX_GAP + BOX_HEIGHT/2, width/2, height/2 - BOX_HEIGHT/2)));
                add((new GLine(width/2, height/2 - TOP_BOX_GAP + BOX_HEIGHT/2, width/2 + BOX_GAP + BOX_WIDTH, height/2 - BOX_HEIGHT/2)));
                
        }
        
        /** Width of the rectangular box */
        private static final double BOX_WIDTH = 125;

        /** Height of the rectangular box */
        private static final double BOX_HEIGHT = 25;
        
        /** Gap between lower boxes */
        private static final double BOX_GAP = 20;
        
        /** Gap between lower boxes */
        private static final double TOP_BOX_GAP = 125;
        
}