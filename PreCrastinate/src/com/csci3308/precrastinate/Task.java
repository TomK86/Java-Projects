package com.csci3308.precrastinate;

/**
 * Stores title, due date, priority, group number, and completion status data
 * for each user-created task, and contains get and set methods to retrieve
 * and edit this data.
 * 
 * @author Tom Kelly
 * 
 * @version 1.0, 06/27/14
 * 
 */
public class Task {
	private String title;
	private long date;
	private String month;
	private String day;
	private String year;
	private float priority;
	private int group;
	private boolean complete;
	
	/**
	 * Task class constructor that takes no arguments and creates a blank task.
	 */
	public Task() {
		this.title = "";
		this.date = 0;
		this.month = "";
		this.day = "";
		this.year = "";
		this.priority = 0;
		this.group = 0;
		this.complete = false;
	}
	
	/**
	 * Task class constructor that takes a long-integer due date argument.  If
	 * this argument = 0, the task is considered to have no due date.
	 * 
	 * @param  taskTitle  The title for this task.
	 * @param  dueDate  The due date for this task, in the form 'yyyymmdd'.
	 * @param  priorityRating  The priority rating for this task (between 0 and 5,
	 * in increments of 0.5).
	 * @param  groupNumber  The group number that this task is assigned to.  Note
	 * that this corresponds to the position of the group in the list of Group
	 * objects.
	 * @param  isCompleted  Whether or not this task is completed (true = complete,
	 * false = active).
	 */
	public Task(String taskTitle, long dueDate, float priorityRating,
			int groupNumber, boolean isCompleted) {
		this.title = taskTitle;
		this.date = dueDate;
		this.month = Long.toString((dueDate % 10000) / 100);
		this.day = Long.toString(dueDate % 100);
		this.year = Long.toString(dueDate / 10000);
		this.priority = priorityRating;
		this.group = groupNumber;
		this.complete = isCompleted;
	}
	
	/**
	 * Task class constructor that takes three String due date arguments.  If these
	 * arguments are empty Strings, the task is considered to have no due date.
	 * 
	 * @param  taskTitle  The title for this task.
	 * @param  dueMonth  The month value of the due date for this task.
	 * @param  dueDay  The day value of the due date for this task.
	 * @param  dueYear  The year value of the due date for this task.
	 * @param  priorityRating  The priority rating for this task (between 0 and 5,
	 * in increments of 0.5).
	 * @param  groupNumber  The group number that this task is assigned to.  Note
	 * that this corresponds to the position of the group in the list of Group
	 * objects.
	 * @param  isCompleted  Whether or not this task is completed (true = complete,
	 * false = active).
	 */
	public Task(String taskTitle, String dueMonth, String dueDay, String dueYear,
			float priorityRating, int groupNumber, boolean isCompleted) {
		this.title = taskTitle;
		this.date = (Long.valueOf(dueYear) * 10000) + (Long.valueOf(dueMonth) * 100)
				+ Long.valueOf(dueDay);
		this.month = dueMonth;
		this.day = dueDay;
		this.year = dueYear;
		this.priority = priorityRating;
		this.group = groupNumber;
		this.complete = isCompleted;
	}
	
	/**
	 * Task class constructor that takes a String due date argument.  If
	 * this argument is an empty String, the task is considered to have no
	 * due date.
	 * 
	 * @param  taskTitle  The title for this task.
	 * @param  dueDate  The due date for this task, in the form 'mm / dd / yyyy'.
	 * @param  priorityRating  The priority rating for this task (between 0 and 5,
	 * in increments of 0.5).
	 * @param  groupNumber  The group number that this task is assigned to.  Note
	 * that this corresponds to the position of the group in the list of Group
	 * objects.
	 * @param  isCompleted  Whether or not this task is completed (true = complete,
	 * false = active).
	 */
	public Task(String taskTitle, String dueDate, float priorityRating,
			int groupNumber, boolean isCompleted) {
		this.title = taskTitle;
		this.priority = priorityRating;
		this.group = groupNumber;
		this.complete = isCompleted;
		
		if(dueDate.length() < 10) {
			this.month = "0";
			this.day = "0";
			this.year = "0";
			this.date = 0L;
		}
		else {
			String[] parts = dueDate.split(" / ");
			this.month = parts[0];
			this.day = parts[1];
			this.year = parts[2];
			this.date = (Long.valueOf(this.year) * 10000) + (Long.valueOf(this.month) * 100)
					+ Long.valueOf(this.day);
		}
	}
	
	/**
	 * Task class constructor that takes three integer due date arguments.  If
	 * these arguments = 0, the task is considered to have no due date.
	 * 
	 * @param  taskTitle  The title for this task.
	 * @param  dueMonth  The month value of the due date for this task.
	 * @param  dueDay  The day value of the due date for this task.
	 * @param  dueYear  The year value of the due date for this task.
	 * @param  priorityRating  The priority rating for this task (between 0 and 5,
	 * in increments of 0.5).
	 * @param  groupNumber  The group number that this task is assigned to.  Note
	 * that this corresponds to the position of the group in the list of Group
	 * objects.
	 * @param  isCompleted  Whether or not this task is completed (true = complete,
	 * false = active).
	 */
	public Task(String taskTitle, int dueMonth, int dueDay, int dueYear,
			float priorityRating, int groupNumber, boolean isCompleted) {
		this.title = taskTitle;
		this.date = (long) ((dueYear * 10000) + (dueMonth * 100) + dueDay);
		this.month = Integer.toString(dueMonth);
		this.day = Integer.toString(dueDay);
		this.year = Integer.toString(dueYear);
		this.priority = priorityRating;
		this.group = groupNumber;
		this.complete = isCompleted;
	}
	
	/**
	 * Retrieves the title of this Task object.
	 * 
	 * @return The title of this task.
	 */
	public String getTaskTitle() {
		return this.title;
	}
	
	/**
	 * Retrieves the due date of this Task object as a long-integer.
	 * 
	 * @return The due date of this task, in the form 'yyyymmdd'.
	 */
	public long getDueDateAsLong() {
		return this.date;
	}
	
	/**
	 * Retrieves the due date of this Task object as a String.
	 * 
	 * @return The due date of this task, in the form 'mm / dd / yyyy'.
	 */
	public String getDueDateAsString() {
		return this.month + " / " + this.day + " / " + this.year;
	}
	
	/**
	 * Retrieves the priority rating of this Task object.
	 * 
	 * @return The priority rating of this task (between 0 and 5, in increments of 0.5).
	 */
	public float getPriority() {
		return this.priority;
	}
	
	/**
	 * Retrieves the group number of this Task object.
	 * 
	 * @return The group number this task is assigned to.  Note that this
	 * corresponds to the position of the group in the list of Group objects,
	 * and that you must call getColor on that object to get its assigned
	 * color value.
	 */
	public int getGroupNum() {
		return this.group;
	}
	
	/**
	 * Retrieves the completion status of this Task object.
	 * 
	 * @return Whether or not the task has been completed (true = complete,
	 * false = active).
	 */
	public boolean getCompleted() {
		return this.complete;
	}
	
	/**
	 * Edits the title of this Task object.
	 * 
	 * @param  newTaskTitle  The new title for this task.
	 */
	public void setTaskTitle(String newTaskTitle) {
		this.title = newTaskTitle;
	}
	
	/**
	 * Edits the due date of this Task object, using a long-integer argument.  If
	 * this argument = 0, the task is considered to have no due date.
	 * 
	 * @param  newDueDate  The new due date for this task, in the form 'yyyymmdd'.
	 */
	public void setDueDateFromLong(long newDueDate) {
		this.date = newDueDate;
		this.month = Long.toString((newDueDate % 10000) / 100);
		this.day = Long.toString(newDueDate % 100);
		this.year = Long.toString(newDueDate / 10000);
	}
	
	/**
	 * Edits the due date of this Task object, using a String argument.  If
	 * this argument is an empty String, the task is considered to have no due
	 * date.
	 * 
	 * @param  newDueDate  The new due date for this task, in the form 'mm / dd / yyyy'.
	 */
	public void setDueDateFromString(String newDueDate) {
		String[] parts = newDueDate.split(" / ");
		this.month = parts[0];
		this.day = parts[1];
		this.year = parts[2];
		this.date = (Long.valueOf(this.year) * 10000) + (Long.valueOf(this.month) * 100)
				+ Long.valueOf(this.day);
	}
	
	/**
	 * Edits the due date of this Task object, using three String arguments.
	 * If these arguments are empty Strings, the task is considered to have no
	 * due date.
	 * 
	 * @param  newDueMonth  The month value of the new due date for this task.
	 * @param  newDueDay  The day value of the new due date for this task.
	 * @param  newDueYear  The year value of the new due date for this task.
	 */
	public void setDueDateFromString(String newDueMonth, String newDueDay, String newDueYear) {
		this.date = (Long.parseLong(newDueYear) * 10000) + (Long.parseLong(newDueMonth) * 100)
				+ Long.parseLong(newDueDay);
		this.month = newDueMonth;
		this.day = newDueDay;
		this.year = newDueYear;
	}
	
	/**
	 * Edits the due date of this Task object, using three integer arguments.
	 * If these arguments = 0, the task is considered to have no due date.
	 * 
	 * @param  newDueMonth  The month value of the new due date for this task.
	 * @param  newDueDay  The day value of the new due date for this task.
	 * @param  newDueYear  The year value of the new due date for this task.
	 */
	public void setDueDateFromInt(int newDueMonth, int newDueDay, int newDueYear) {
		this.date = (long) ((newDueYear * 10000) + (newDueMonth * 100) + newDueDay);
		this.month = Integer.toString(newDueMonth);
		this.day = Integer.toString(newDueDay);
		this.year = Integer.toString(newDueYear);
	}
	
	/**
	 * Edits the priority rating of this Task object.
	 * 
	 * @param  newPriorityRating  The new priority rating for this task (between
	 * 0 and 5, in increments of 0.5).
	 */
	public void setPriority(float newPriorityRating) {
		this.priority = newPriorityRating;
	}
	
	/**
	 * Edits the group number that this Task object is assigned to.
	 * 
	 * @param  newGroupNumber  The new group number for this task.  Note that this
	 * corresponds to the position of the group in the list of Group objects.
	 */
	public void setGroupNum(int newGroupNumber) {
		this.group = newGroupNumber;
	}
	
	/**
	 * Edits the completion status of this Task object.
	 * 
	 * @param  isCompleted  Whether or not this task has been completed (true =
	 * complete, false = active).
	 */
	public void setCompleted(boolean isCompleted) {
		this.complete = isCompleted;
	}

}