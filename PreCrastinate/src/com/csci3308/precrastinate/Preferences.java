package com.csci3308.precrastinate;

import java.util.Calendar;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.TimePicker;
import android.widget.EditText;

/**
 * The preferences screen activity, which contains several methods that set the user's
 * preferred reminder time, as well as add, edit, and delete task groups.
 * 
 * @author Tom Kelly
 * 
 * @version  1.0, 06/27/14
 * 
 */
public class Preferences extends Activity {
 
	GroupListAdapter grpListAdapter;
    ExpandableListView grpListView;
    EditText remTimeField;
    Button prefSaveBtn, addGrpBtn;
    private static int mHour = -1;
    private static int mMinute = -1;
    private static int AmPm = -1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        // get the group list view
        grpListView = (ExpandableListView) findViewById(R.id.groupExpList);
        
        // get the child views
        remTimeField = (EditText) findViewById(R.id.remTimeEdit);
        prefSaveBtn = (Button) findViewById(R.id.prefSaveBtn);
        addGrpBtn = (Button) findViewById(R.id.addGrpBtn);
        
        // set the current saved reminder time, if it exists
        mHour = MainActivity.mHour;
        mMinute = MainActivity.mMinute;
        AmPm = MainActivity.AmPm;
    	updateRemTime();
        
        // initialize the group list adapter
        grpListAdapter = new GroupListAdapter(this);
        grpListView.setAdapter(grpListAdapter);
        
        grpListView.setOnGroupExpandListener(new OnGroupExpandListener() {
        	int previousGroup = -1;
            /**
             * Called when a group in the list is expanded, this hides the
             * 'Add Group' and 'Save Preferences' Button views, and collapses
             * any other group in the list that is currently expanded.
             */
        	@Override
            public void onGroupExpand(int groupPosition) {
            	prefSaveBtn.setVisibility(View.GONE);
            	addGrpBtn.setVisibility(View.GONE);
            	if((groupPosition != previousGroup) && (previousGroup != -1))
                    grpListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });
        
        grpListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
            /**
             * Called when a group in the list is collapsed, this checks to see
             * if all groups in the list are collapsed.  If they are, the 'Add
             * Group' and 'Save Preferences' Button views will reappear.
             */
        	@Override
            public void onGroupCollapse(int groupPosition) {
            	boolean expCheck = false;
            	for(int i = 0; i < MainActivity.listGroupObjs.size(); i++)
            		expCheck = expCheck | grpListView.isGroupExpanded(i);
            	if(!expCheck) {
            		prefSaveBtn.setVisibility(View.VISIBLE);
            		addGrpBtn.setVisibility(View.VISIBLE);
            	}
            }
        });
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
    	// automatically handle clicks on the Home/Up button, so long
    	// as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()) {
	        case android.R.id.home:
	            MainActivity.loadGroupData();
	            MainActivity.saveGroupData();
	            MainActivity.loadTaskData();
	        	MainActivity.sortTasks();
	        	finish();
	            return true;
        	default:
        		return super.onOptionsItemSelected(item);
        }
    }
    
    /**
     * Sets the user's preferred reminder time upon launching the Preferences screen.
     * The arguments should be obtained directly from the saved data, with default
     * values of -1 if no such data exists.
     * 
     * @param  hour  The hour value to be set (between 1 and 12).
     * @param  minute  The minute value to be set (between 0 and 59).
     * @param  ampm  The AM/PM value to be set (0 = AM, 1 = PM).
     */
    public static void setRemTime(int hour, int minute, int ampm) {
    	mHour = hour;
    	mMinute = minute;
    	AmPm = ampm;
    }
    
    /**
     * Updates the text in the reminder time EditText view to reflect the current preferred
     * reminder time.
     */
    public void updateRemTime() {
    	if(AmPm == 0) {
        	if(mMinute < 10)
    			remTimeField.setText(mHour + ":0" + mMinute + " AM");
    		else
    			remTimeField.setText(mHour + ":" + mMinute + " AM");
    	}
    	else if(AmPm == 1) {
    		if(mMinute < 10)
    			remTimeField.setText(mHour + ":0" + mMinute + " PM");
    		else
    			remTimeField.setText(mHour + ":" + mMinute + " PM");
    	}
    }
    
    /**
     * Called when the reminder time EditText view is clicked, this launches the
     * TimePickerDialog that lets the user select their preferred reminder time.
     * 
     * @param  view  The reminder time EditText view that was clicked.
     */
    public void onReminderTimeClicked(View view) {
    	Calendar mCurrentTime = Calendar.getInstance();
    	int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
    	int minute = 0;
		if(hour == 23)
    		hour = 0;
		else
			hour++;
    	TimePickerDialog mTimePicker;
    	mTimePicker = new TimePickerDialog(Preferences.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
            	if(selectedHour == 0) {
            		AmPm = 0;
            		selectedHour = 12;
            	}
            	else if(selectedHour > 12) {
            		AmPm = 1;
            		selectedHour -= 12;
            	}
            	else if(selectedHour < 12)
            		AmPm = 0;
            	else // selectedHour == 12
            		AmPm = 1;
            	mHour = selectedHour;
            	mMinute = selectedMinute;
            	updateRemTime();
            }
    	}, hour, minute, false);
        mTimePicker.setTitle("Select Reminder Time");
        mTimePicker.show();
    }
    
    /**
     * Called when one of the group color RadioButton views is clicked, this
     * sets a tag that is used to update the group's color when the user clicks
     * the 'Update This Group' button.
     * 
     * @param  view  The group color RadioButton view that was clicked.
     */
	public void onRadioButtonClicked(View view) {
	    // Is the button now checked?
	    boolean checked = ((RadioButton) view).isChecked();
	    
	    // Check which radio button was clicked
	    RadioGroup grpColor = (RadioGroup) view.getParent();
	    switch(view.getId()) {
	        case R.id.color0:
	            if (checked)
	                grpColor.setTag(0);
	            break;
	        case R.id.color1:
	            if (checked)
	            	grpColor.setTag(1);
	            break;
	        case R.id.color2:
	            if (checked)
	            	grpColor.setTag(2);
	            break;
	        case R.id.color3:
	            if (checked)
	            	grpColor.setTag(3);
	            break;
	        case R.id.color4:
	            if (checked)
	            	grpColor.setTag(4);
	            break;
	    }
	}
	
	/**
	 * Called when the 'Update This Group' Button view is clicked, this updates the list
	 * of Group objects to reflect any changes that were made and collapses the group.
	 * Note that these changes are not permanent until the user clicks the 'Save
	 * Preferences' button, as they are not committed to the saved data.  They will
	 * therefore be reverted to the previous settings if the user clicks the 'back'
	 * or 'home' button instead.
	 * 
	 * @param  view  The 'Update This Group' Button view that was clicked.
	 */
	public void onGrpUpdateBtnClicked(View view) {
		EditText grpName = (EditText) ((View) view.getParent()).findViewById(R.id.grpName);
		RadioGroup grpColor = (RadioGroup) ((View) view.getParent()).findViewById(R.id.grpColor);
    	int position = (Integer) grpName.getTag();
    	int newColor = (Integer) grpColor.getTag();
    	String newName = grpName.getText().toString();
    	
    	MainActivity.listGroupObjs.get(position).setName(newName);
    	MainActivity.listGroupObjs.get(position).setColor(newColor);
    	grpListAdapter.notifyDataSetChanged();
    	grpListView.collapseGroup(position);
    	
    	Toast.makeText(getApplicationContext(),
        		"'" + newName + "' group updated", Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * Called when the 'Delete This Group' Button view is clicked, this removes the group
	 * from the list of Group objects and updates the group list view.  Note that this change
	 * is not permanent until the user clicks the 'Save Preferences' button, as it is not
	 * committed to the saved data.  It will therefore be reverted to the previous settings
	 * if the user clicks the 'back' or 'home' button instead.
	 * 
	 * @param  view  The 'Delete This Group' Button view that was clicked.
	 */
	public void onGrpDeleteBtnClicked(View view) {
		EditText grpName = (EditText) ((View) view.getParent()).findViewById(R.id.grpName);
		int position = (Integer) grpName.getTag();
		String deletedGrp = MainActivity.listGroupObjs.get(position).getName();
		
		MainActivity.listGroupObjs.remove(position);
		grpListAdapter.notifyDataSetChanged();
		grpListView.collapseGroup(position);
		
		Toast.makeText(getApplicationContext(),
        		"'" + deletedGrp + "' group deleted", Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * Called when the 'Add Group' Button view is clicked, this adds a new Group object to the
	 * list of Group objects (named 'New Group' with a null, or white, color value) and updates
	 * the group list view.  Note that this change is not permanent until the user clicks
	 * the 'Save Preferences' button, as it is not committed to the saved data.  It will
	 * therefore be reverted to the previous settings if the user clicks the 'back' or 'home'
	 * button instead.
	 * 
	 * @param  view  The 'Add Group' Button view that was clicked.
	 */
	public void onAddGrpBtnClicked(View view) {
		MainActivity.listGroupObjs.add(new Group("New Group", 5));
		grpListAdapter.notifyDataSetChanged();
	}
	
	/**
	 * Called when the 'Save Preferences' Button view is clicked, this commits the current
	 * list of Group objects to saved data and returns the user to the screen they were on
	 * when the Preferences screen was launched.
	 * 
	 * @param  view  The 'Save Preferences' Button view that was clicked.
	 */
	public void onPrefSaveBtnClicked(View view) {
		MainActivity.saveGroupData();
		MainActivity.saveRemTime(mHour, mMinute, AmPm);
		
		Toast.makeText(this, "Preferences saved!", Toast.LENGTH_SHORT).show();
		
		finish();
	}
    
}