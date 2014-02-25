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

public class NameSurfer extends ConsoleProgram implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
	    // You fill this in, along with any helper methods //
		// Create Interactors
		nameField = new JTextField(10);
		nameField.setActionCommand("name");
		graph = new JButton("Graph");
		clear = new JButton("Clear");
		
		// Add Interactors to Canvas
		add(new JLabel("Names:"), NORTH);
		add(nameField, NORTH);
		add(graph, NORTH);
		add(clear, NORTH);
		
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
			if (cmd.equals("name") || e.getSource() == graph) println("Graph: " + "\"" + nameField.getText() + "\"");
		}
			if (e.getSource() == clear) println("Clear");
	}
	
/* Private Instance Variables*/
	private JTextField nameField;
	private JButton graph;
	private JButton clear;
	
}
