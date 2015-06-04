package com.csci3308.precrastinate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.DatePicker;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.view.View.OnClickListener;

/** Add new task activity screen.  It contains the ability to add task title, due date, group
 * and priority.
 * 
 * @author Matt Hong
 * 
 * @version 1.0, 06/27/14
 *
 */
public class AddTask extends Activity {
	
	EditText dueDate;
	Spinner groupSpinner;
	List<String> listGroupNames;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_task);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		listGroupNames = new ArrayList<String>();
		for(int i = 0; i < MainActivity.listGroupObjs.size(); i++)
			listGroupNames.add(MainActivity.listGroupObjs.get(i).getName());
		
		// Assign Due Date
	    dueDate = (EditText) findViewById(R.id.addTaskDate);
		
		 // EditText click listener
        dueDate.setOnClickListener(new OnClickListener() {
       	
       	@Override
            public void onClick(View v) {
       			Toast.makeText(getApplicationContext(),
           		"Please select the deadline or due date for this task",
           		Toast.LENGTH_LONG).show();
       			Calendar mCurrentDate = Calendar.getInstance();
       			int day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
       			int month = mCurrentDate.get(Calendar.MONTH);
       			int year = mCurrentDate.get(Calendar.YEAR);
       			DatePickerDialog mDatePicker;
       			mDatePicker = new DatePickerDialog(AddTask.this, new DatePickerDialog.OnDateSetListener() {
           		@Override
                public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                dueDate.setText((selectedMonth + 1) + " / " + selectedDay + " / " + selectedYear);
                
                }
           	}, year, month, day);
               mDatePicker.setTitle("Select Due Date");
               mDatePicker.show();
               
           }
       	
       	
        });

		// Create Spinner
		groupSpinner = (Spinner) findViewById(R.id.addTaskGroup);
		ArrayAdapter<String> groupsAdapter = new ArrayAdapter<String>(this, android.R.layout
				.simple_spinner_item, listGroupNames);
		groupsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupSpinner.setAdapter(groupsAdapter);
        


	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_task, menu);
        return super.onCreateOptionsMenu(menu);
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
        	case R.id.action_preferences:
        		setPrefs();
        		return true;
        	default:
        		return super.onOptionsItemSelected(item);
        }
    }


	
	/**
	 * Commits new task data to the saved data when the 'Save Task' Button view is clicked.
	 * 
	 * @param view The 'Save Task' Button view that was clicked.
	 */
	public void onSaveTaskButtonClicked(View view) {
		EditText taskTitleName = (EditText) findViewById(R.id.addTaskName);
		RatingBar priority = (RatingBar) findViewById(R.id.addTaskPriority);
		
		String chosenTitle = taskTitleName.getText().toString();
		String chosenDate = dueDate.getText().toString();
		int chosenGroup = groupSpinner.getSelectedItemPosition();
		float chosenRating = priority.getRating();
		
		MainActivity.listTaskObjs.add(new Task(chosenTitle, chosenDate, chosenRating, chosenGroup, false));
		MainActivity.sortTasks();
		
		Toast.makeText(this, "Task saved!", Toast.LENGTH_SHORT).show();
		finish();
	}
	
	// launches the Preferences activity
	/**
	 * Pulls up the Preferences screen.
	 */
	public void setPrefs() {
		MainActivity.loadGroupData();
		MainActivity.loadRemTime();
		Intent prefs = new Intent(this, Preferences.class);
		startActivity(prefs);
	}
	
}