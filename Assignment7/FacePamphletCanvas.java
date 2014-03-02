/*
 * File: FacePamphletCanvas.java
 * Name: Sudhir Khanger
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		// You fill this in
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		// remove all objects from the canvas
		removeAll();
		
		// add profile name
		GLabel profileName = new GLabel(profile.getName());
		profileName.setColor(Color.blue);
		profileName.setFont(PROFILE_NAME_FONT);
		add(profileName, LEFT_MARGIN, TOP_MARGIN);
		
		// No image rectangle
		GRect rect = new GRect(IMAGE_WIDTH, IMAGE_HEIGHT);
		add(rect, LEFT_MARGIN, TOP_MARGIN + IMAGE_MARGIN);
		GLabel noImg = new GLabel("No Image");
		noImg.setFont(PROFILE_IMAGE_FONT);
		noImg.setLocation(LEFT_MARGIN + (IMAGE_WIDTH/2) - (noImg.getWidth()/2) , TOP_MARGIN + IMAGE_MARGIN + (IMAGE_HEIGHT/2) - (noImg.getAscent()/2));
		add(noImg);
		
		// add image
		if (profile.getImage() != null) {
			GImage picture = profile.getImage();
			picture.scale(IMAGE_WIDTH, IMAGE_HEIGHT);
			picture.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
			add(picture, LEFT_MARGIN, IMAGE_MARGIN);
		}
		
		// status
		GLabel status;
		if (profile.getStatus().equals("")) {
			status = new GLabel("No current status");
		} else {
			status = new GLabel(profile.getStatus());
		}
			status.setFont(PROFILE_STATUS_FONT);
			status.setLocation(LEFT_MARGIN, rect.getY() + IMAGE_HEIGHT + STATUS_MARGIN - status.getAscent()/2 );
			add(status);
	}
	
}
