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
		GLabel appMessage = new GLabel(msg);
		appMessage.setFont(MESSAGE_FONT);
		double x = (getWidth() - appMessage.getWidth())/2;
		double y = getHeight() - BOTTOM_MESSAGE_MARGIN;
		if (getElementAt(x, y) != null) remove(getElementAt(x, y));
		add(appMessage, x, y);
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
		
		if (profile == null) {
			removeAll();
		} else {
			double x;
			double y;
			
			// add profile name
			GLabel profileName;
			profileName = new GLabel(profile.getName());
			profileName.setColor(Color.blue);
			profileName.setFont(PROFILE_NAME_FONT);
			x = LEFT_MARGIN;
			y = TOP_MARGIN + profileName.getAscent();
			add(profileName, x, y);
			
			// No image rectangle
			GRect rect = new GRect(IMAGE_WIDTH, IMAGE_HEIGHT);
			x = LEFT_MARGIN;
			y = TOP_MARGIN + profileName.getAscent() + IMAGE_MARGIN;
			add(rect, x, y );
			GLabel noImg = new GLabel("No Image");
			noImg.setFont(PROFILE_IMAGE_FONT);
			x = LEFT_MARGIN + (IMAGE_WIDTH - noImg.getWidth())/2;
			y = TOP_MARGIN + profileName.getAscent() + IMAGE_MARGIN + (IMAGE_HEIGHT + noImg.getAscent())/2;
			add(noImg, x, y);
			
			// add image
			if (profile.getImage() != null) {
				GImage image = profile.getImage();
				image.scale(IMAGE_WIDTH, IMAGE_HEIGHT);
				image.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
				x = LEFT_MARGIN;
				y = TOP_MARGIN + profileName.getAscent() + IMAGE_MARGIN;
				add(image, x, y);
			}
			
			// status
			GLabel status;
			if (profile.getStatus().equals("")) {
				status = new GLabel("No current status");
			} else {
				status = new GLabel(profile.getName() + " is " + profile.getStatus());
			}
				status.setFont(PROFILE_STATUS_FONT);
				status.setLocation(LEFT_MARGIN, rect.getY() + IMAGE_HEIGHT + STATUS_MARGIN - status.getAscent()/2 );
				x = LEFT_MARGIN;
				y = TOP_MARGIN + profileName.getAscent() + IMAGE_MARGIN + IMAGE_HEIGHT + status.getAscent() + STATUS_MARGIN;
				add(status, x, y);
				
			// Friends
			GLabel friendLabel = new GLabel("Friends:");
			friendLabel.setFont(PROFILE_FRIEND_LABEL_FONT);
			x = getWidth()/2;
			y = TOP_MARGIN + profileName.getAscent() + IMAGE_MARGIN;
			add(friendLabel, x, y);
			Iterator<FacePamphletProfile> it = profile.getFriends();
				while (it.hasNext()) {
					FacePamphletProfile friends = (FacePamphletProfile) it.next();
					GLabel friendName = new GLabel(friends.getName());
					friendName.setFont(PROFILE_FRIEND_FONT);
					x = getWidth()/2;
					y += friendName.getAscent();
					add(friendName, x, y);
				}
		}
	}
	
}
