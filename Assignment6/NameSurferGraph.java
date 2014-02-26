/*
 * File: NameSurferGraph.java
 * Name: Sudhir Khanger
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		//	 You fill in the rest //
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		removeAll();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		// Adds the NameSurferEntry to an ArrayList
		addEntryList.add(entry);
	}
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		clear();
		// Create the background grid
		int x = getWidth()/NDECADES;
		int y0 = 0;
		int y1 = getHeight();
		GLabel word = new GLabel("1900");
			for (int i = 0; i < NDECADES; i++) {
				// Add verticle lines and year in the bottom
				add(new GLine(i*x, y0, i*x, y1));
				add(new GLabel((Integer.toString((i*10) + START_DECADE)), (i*x) + word.getWidth(), getHeight() - word.getHeight()/2));
			}
		add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));			// Top Horizontal Line
		add(new GLine(0, y1-GRAPH_MARGIN_SIZE, getWidth(), y1-GRAPH_MARGIN_SIZE));		// Bottom Horizontal Line
		
			for (int i = 0; i < addEntryList.size(); i ++) {
				for (int j = 0; j < NDECADES; j++) {
					// the graph must be between the two horizontal lines
					double min = GRAPH_MARGIN_SIZE + word.getAscent();
					double max = getHeight()- GRAPH_MARGIN_SIZE;
					// the graph goes from 0 to 1000
					double totalHeight = max-min;
					double factor = totalHeight/1000;
					// value for two years
					double z0 = addEntryList.get(i).getRank((j*10) + 1900); 
					double z1 = addEntryList.get(i).getRank((j*10) + 1910);
					// exact coordinates for the graph
					double m = (z0 * factor) + min;
					double n = (z1 * factor) + min;
					// As instruction says 0 is in bottom and not top
					if (z0 == 0) m = max;
					if (z1 == 0) n = max;
					
			//		int c;
			//		if (i < 4) {
			//			c = i;
			//		} else {
			//			c = colorChooser(i);
			//		}
					
					if (j == NDECADES -1) {
						// Skip so no line is drawn after last point
					} else {
						GLine line = new GLine(j*x, m, (j+1)*x, n);
						switch (i % 4) {
							case 0:
								line.setColor(Color.BLACK);
								break;
							case 1:
								line.setColor(Color.RED);
								break;
							case 2:
								line.setColor(Color.BLUE);
								break;
							case 3:
								line.setColor(Color.MAGENTA);
								break;
							default:
								line.setColor(Color.BLACK);
								break;
						}
						add(line);
					}
						GLabel name = new GLabel(addEntryList.get(i).getName() + " " + Integer.toString((int) z0), j*x, m);
						switch (i % 4) {
							case 0:
								name.setColor(Color.BLACK);
								break;
							case 1:
								name.setColor(Color.RED);
								break;
							case 2:
								name.setColor(Color.BLUE);
								break;
							case 3:
								name.setColor(Color.MAGENTA);
								break;
							default:
								name.setColor(Color.BLACK);
								break;
						}
						add(name);
						add(new GLabel(Integer.toString(i%4), 100, 10*i));
				}
			}
	}
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	/* Instance variable */
	ArrayList<NameSurferEntry> addEntryList = new ArrayList<NameSurferEntry>();
}
