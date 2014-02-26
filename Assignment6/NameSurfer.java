/*
 * File: NameSurfer.java
 * Name: Sudhir Khanger
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
	    // You fill this in, along with any helper methods //
		
		// Create the GCanvas
		graph = new NameSurferGraph(); 
		add(graph);
		
		// Create Interactors
		nameField = new JTextField(10);
		nameField.setActionCommand("name");
		graphButton = new JButton("Graph");
		clearButton = new JButton("Clear");
		
		// Add Interactors to Canvas
		add(new JLabel("Names:"), NORTH);
		add(nameField, NORTH);
		add(graphButton, NORTH);
		add(clearButton, NORTH);
		
		// Reads the file
		db = new NameSurferDataBase("names-data.txt");
		
		// Call actionPerformed method
		addActionListeners();
		nameField.addActionListener(this);
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
		// Respond to interactors
		String cmd = e.getActionCommand();
		if ( !(nameField.getText().equals("")) ) {
			if (cmd.equals("name") || e.getSource() == graphButton) {
				graph.addEntry(db.findEntry(toUpperCase(nameField.getText())));
				graph.update();
			}
		}
			if (e.getSource() == clearButton) {
				graph.clear();
			}
	}
	
	private String toUpperCase(String str){
		String result = "";
		char ch = str.charAt(0);
		char strUpperCase = Character.toUpperCase(ch);
		result = strUpperCase + str.substring(1);
		return result;
	}
	
/* Private Instance Variables*/
	private JTextField nameField;
	private JButton graphButton;
	private JButton clearButton;
	private NameSurferGraph graph;
	private NameSurferDataBase db;
}
