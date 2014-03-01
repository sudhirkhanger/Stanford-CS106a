/* 
 * File: FacePamphlet.java
 * Name: Sudhir Khanger
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends ConsoleProgram 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		// North Region
		nameTextField = new JTextField(TEXT_FIELD_SIZE);		// actionCommand not associated
		addButton = new JButton("Add");
		deleteButton = new JButton("Delete");
		lookupButton = new JButton("Lookup");
		
		add(new JLabel("Name"), NORTH);
		add(nameTextField, NORTH);
		add(addButton, NORTH);
		add(deleteButton, NORTH);
		add(lookupButton, NORTH);
		
		// West Region
		changeStatusTextField = new JTextField(TEXT_FIELD_SIZE);
		changeStatusButton = new JButton("Change Status");
		changePictureTextField = new JTextField(TEXT_FIELD_SIZE);
		changePictureButton = new JButton("Change Picture");
		addFriendTextField = new JTextField(TEXT_FIELD_SIZE);
		addFriendButton = new JButton("Add Friend");
		
		add(changeStatusTextField, WEST);
		add(changeStatusButton, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(changePictureTextField, WEST);
		add(changePictureButton, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(addFriendTextField, WEST);
		add(addFriendButton, WEST);
		
		// ActionListeners
		addActionListeners();
		changeStatusTextField.addActionListener(this);
		changePictureTextField.addActionListener(this);
		addFriendTextField.addActionListener(this);
	}
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
    	if (!nameTextField.getText().equals("")) {
    		if (e.getSource() == addButton) {
    			db.addProfile((new FacePamphletProfile(nameTextField.getText())));
    			println("Add: " + nameTextField.getText());
    		}
    		if (e.getSource() == deleteButton) {
    			db.deleteProfile(nameTextField.getText());
    			println("Delete: " + nameTextField.getText());
    		}
    		
    		if (e.getSource() == lookupButton) {
    			if (db.getProfile(nameTextField.getText()) != null) {
    				println("Lookup: " + nameTextField.getText());
    			} else {
    				println("Profile Not Found");
    			}
    		}
    	}
		// Change Status
		if (e.getSource() == changeStatusButton || e.getSource() == changeStatusTextField) {
			if (!changeStatusTextField.getText().equals("")) println("Change Status: " + changeStatusTextField.getText());
		}
		// Change Picture
		if (e.getSource() == changePictureButton || e.getSource() == changePictureTextField) {
			if (!changePictureTextField.getText().equals("")) println("Change Picture: " + changePictureTextField.getText());
		}
		// Add Friend
		if (e.getSource() == addFriendButton || e.getSource() == addFriendTextField) {
			if (!addFriendTextField.getText().equals("")) println("Add Friend: " + addFriendTextField.getText());
		}
	}
    
    /* Private Instance Variables */
    private JButton addButton;
    private JButton deleteButton;
    private JButton lookupButton;
    private JButton changeStatusButton;
    private JButton changePictureButton;
    private JButton addFriendButton;
    private JTextField nameTextField;
    private JTextField changeStatusTextField; 
    private JTextField changePictureTextField;
    private JTextField addFriendTextField;
    private FacePamphletDatabase db = new FacePamphletDatabase();
}
