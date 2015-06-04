package com.csci3308.precrastinate;

/**
 * Stores name and color data for each user-created task group, and contains get
 * and set methods to retrieve or edit this data.
 * 
 * @author Tom Kelly
 * 
 * @version 1.0, 06/27/14
 * 
 */
public class Group {
	
	private String name;
	private int color;
	
	/**
	 * Blank group class constructor.
	 */
	public Group() {
		this.name = "";
		this.color = 5;
	}
	
	/**
	 * Group class constructor.
	 * 
	 * @param  groupName  The name of the group.
	 * @param  groupColor  The color value of the group (0 = green, 1 = blue,
	 * 2 = purple, 3 = red, 4 = orange, 5 = null/white).
	 */
	public Group(String groupName, int groupColor) {
		this.name = groupName;
		this.color = groupColor;
	}
	
	/**
	 * Retrieves the name of this Group object.
	 * 
	 * @return The name of the group.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Retrieves the color value of this Group object.
	 * 
	 * @return The color value of the group (0 = green, 1 = blue, 2 = purple,
	 * 3 = red, 4 = orange, 5 = null/white).
	 */
	public int getColor() {
		return this.color;
	}
	
	/**
	 * Edits the name of this Group object.
	 * 
	 * @param  newName  The new name of the group.
	 */
	public void setName(String newName) {
		this.name = newName;
	}
	
	/**
	 * Edits the color value of this Group object.
	 * 
	 * @param  newColor  The new color value of the group (0 = green, 1 = blue,
	 * 2 = purple, 3 = red, 4 = orange, 5 = null/white).
	 */
	public void setColor(int newColor) {
		this.color = newColor;
	}
	
}