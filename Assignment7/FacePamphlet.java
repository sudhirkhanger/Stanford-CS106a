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
import java.util.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		
		// Initialize graphical class constructor
		canvas = new FacePamphletCanvas(); 
		add(canvas);
		
		// North Region
		nameTextField = new JTextField(TEXT_FIELD_SIZE);
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
    	// Add Profile
    	if (!nameTextField.getText().equals("")) {	
    		if (e.getSource() == addButton) {			
    			db.addProfile((new FacePamphletProfile(nameTextField.getText())));
				//println("Add: new profile: " + db.getProfile(nameTextField.getText().toString()));
				currentProfile = db.getProfile(nameTextField.getText());
				//println("--> Current profile: " + currentProfile.toString());
				canvas.displayProfile(currentProfile);
    		}
    	
    	// Delete Profile
    	if (e.getSource() == deleteButton) {
    		db.deleteProfile(nameTextField.getText());
    		currentProfile = null;
    		//println("Delete: " + nameTextField.getText());
    		//println("--> No Current profile");
    	}
    	
    	// Lookup Profile
    	if (e.getSource() == lookupButton) {
    		if (db.containsProfile(nameTextField.getText())) {
    			//println("Lookup: " + nameTextField.getText());
    			currentProfile = db.getProfile(nameTextField.getText());
    			//println("--> Current profile: " + currentProfile.toString());
    		} else {
    			//println("Profile Not Found");
    			currentProfile = null;
    			//println("--> No Current profile");
    		}
    	}
    	
    	}
		// Change Status
		if (e.getSource() == changeStatusButton || e.getSource() == changeStatusTextField) {
			if (!changeStatusTextField.getText().equals("")) {			// TextField can't be empty
				if (currentProfile != null) {							// currentProfile can't be null
					if (db.containsProfile(nameTextField.getText())) { 	// profile in TextField should exist
						db.getProfile(nameTextField.getText()).setStatus(changeStatusTextField.getText());
						//println("Status updated to " + changeStatusTextField.getText());
						//println("--> Current profile: " + currentProfile.toString());
					} else {
						//println("Profile doesn't exist");
						//println("--> Current profile: " + currentProfile.toString());
					}
					
				} else {
					//println("Select a profile to change status");
					//println("--> No Current profile");
				}
			}
		}
		// Change Picture
		if (e.getSource() == changePictureButton || e.getSource() == changePictureTextField) {
			if (!changePictureTextField.getText().equals("")) {
				if (currentProfile != null) {
					if (db.containsProfile(nameTextField.getText())) {
						GImage image = null; 
						try { 
							image = new GImage(changePictureTextField.getText()); 
						} catch (ErrorException ex) { 

						} 
						if (image !=null) {
							db.getProfile(nameTextField.getText()).setImage(image);
							canvas.displayProfile(currentProfile);
							//println("Picture updated");
							//println("--> Current profile: " + currentProfile.toString());
						}
					} else {
						//println("Profile doesn't exist");
						//println("--> Current profile: " + currentProfile.toString());
					}
					
				} else {
					//println("Select a profile to change picture");
					//println("--> No Current profile");
				}
			}
		}
		// Add Friend
		if (e.getSource() == addFriendButton || e.getSource() == addFriendTextField) {
			if (!addFriendTextField.getText().equals("")) {
				if (currentProfile != null) {
					if (db.containsProfile(addFriendTextField.getText())) {
						if (db.containsProfile(nameTextField.getText())) {
							db.getProfile(nameTextField.getText()).addFriend(db.getProfile(addFriendTextField.getText()));
							db.getProfile(addFriendTextField.getText()).addFriend(db.getProfile(nameTextField.getText()));
							//println(addFriendTextField.getText() + " added as a friend");
							//println("--> Current profile: " + currentProfile.toString());
						} else {
							//println("Profile " + nameTextField.getText() + " doesn't exist");
							//println("--> Current profile: " + currentProfile.toString());
						}
					} else {
						//println("Profile doesn't exist");
						//println("--> Current profile: " + currentProfile.toString());
					}
				} else {
					//println("Select a profile to Add Friend");
					//println("--> No Current profile");
				}
			}
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
    private FacePamphletProfile currentProfile;
    private FacePamphletCanvas canvas;
}
