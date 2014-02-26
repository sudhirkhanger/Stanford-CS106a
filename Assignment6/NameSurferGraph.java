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
		int z = getHeight()/MAX_RANK;
		GLabel word = new GLabel("1900");
			for (int i = 0; i < NDECADES; i++) {
				add(new GLine(i * x, y0, i * x, y1));
				add(new GLabel((Integer.toString((i*10) + START_DECADE)), (i * x) + word.getWidth(), getHeight() - word.getHeight()/2));
			}
		add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
		add(new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE));
	
			for (int i = 0; i < addEntryList.size(); i ++) {
				for (int j = 0; j < NDECADES; j++) {
					add(new GLine(j*x, addEntryList.get(i).getRank((j*10)+1900), (j+1) * x, addEntryList.get(i).getRank( ((j*10)+1900) + 10 )));
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
