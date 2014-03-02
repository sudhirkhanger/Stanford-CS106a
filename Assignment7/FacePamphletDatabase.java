/*
 * File: FacePamphletDatabase.java
 * Name: Sudhir Khanger
 * -------------------------------
 * This class keeps track of the profiles of all users in the
 * FacePamphlet application.  Note that profile names are case
 * sensitive, so that "ALICE" and "alice" are NOT the same name.
 */

import java.util.*;

public class FacePamphletDatabase implements FacePamphletConstants {

	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the database.
	 */
	public FacePamphletDatabase() {
		// You fill this in
	}
	
	
	/** 
	 * This method adds the given profile to the database.  If the 
	 * name associated with the profile is the same as an existing 
	 * name in the database, the existing profile is replaced by 
	 * the new profile passed in.
	 */
	public void addProfile(FacePamphletProfile profile) {
		if (!masterList.containsKey(profile.getName())) {
			masterList.put(profile.getName(), profile);
		} else {
			deleteProfile(profile.getName());
			masterList.put(profile.getName(), profile);
		}
	}
	
	/** 
	 * This method returns the profile associated with the given name 
	 * in the database.  If there is no profile in the database with 
	 * the given name, the method returns null.
	 */
	public FacePamphletProfile getProfile(String name) {
		if (masterList.containsKey(name)) {
			return masterList.get(name);
		} else {
			return null;
		}
	}
	
	
	/** 
	 * This method removes the profile associated with the given name
	 * from the database.  It also updates the list of friends of all
	 * other profiles in the database to make sure that this name is
	 * removed from the list of friends of any other profile.
	 * 
	 * If there is no profile in the database with the given name, then
	 * the database is unchanged after calling this method.
	 */
	public void deleteProfile(String name) {
		FacePamphletProfile profileToBeDeleted = masterList.get(name);
		Iterator<FacePamphletProfile> it = profileToBeDeleted.getFriends();
			// Remove all instances of deleted profile from all other profiles.
			while (it.hasNext()) {
				FacePamphletProfile friendToRemoveProfileFrom = (FacePamphletProfile) it.next();
				friendToRemoveProfileFrom.removeFriend(profileToBeDeleted);
			}
		masterList.remove(name);
	}
	
	/** 
	 * This method returns true if there is a profile in the database 
	 * that has the given name.  It returns false otherwise.
	 */
	public boolean containsProfile(String name) {
		if (masterList.containsKey(name)) {
			return true;
		} else {
			return false;
		}
	}
	
	public Iterator<String> masterListIterator() {
		return masterList.keySet().iterator();
	}
	
	/* Private Instance Variables */
	private HashMap<String, FacePamphletProfile> masterList = new HashMap<String, FacePamphletProfile>();
}
