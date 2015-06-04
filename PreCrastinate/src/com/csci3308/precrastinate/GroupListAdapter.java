package com.csci3308.precrastinate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * The group list adapter, which acts as a link between the group data and the group
 * list display.  It contains several methods which update the group ExpandableListView
 * when changes are made to the list of Group objects.
 * 
 * @author Tom Kelly
 *
 * @version 1.0, 06/27/14
 * 
 */
public class GroupListAdapter extends BaseExpandableListAdapter {
	
    private LayoutInflater mInflater;
    
    /**
     * Constructor for the GroupListAdapter class, which initializes the inflater used
     * to focus on the various views when they are updated.
     * 
     * @param  context  The Preferences activity, where this adapter was initialized.
     */
    public GroupListAdapter(Context context) {
    	this.mInflater = (LayoutInflater) context
    			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
 
    @Override
    public Group getChild(int groupPosition, int childPosition) {
    	return MainActivity.listGroupObjs.get(groupPosition);
    }
    
    @Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}
 
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    @Override
    public View getChildView(int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = mInflater.inflate(R.layout.group_list_settings, null);
        
    	String name = (String) getChild(groupPosition, 0).getName();
    	int color = (Integer) getChild(groupPosition, 0).getColor();
    	
        EditText grpName = (EditText) convertView.findViewById(R.id.grpName);
        RadioGroup grpColor = (RadioGroup) convertView.findViewById(R.id.grpColor);
        
        grpName.setText(name);
        if(color < 5)
        	grpColor.check(grpColor.getChildAt(color).getId());
        else
        	grpColor.clearCheck();
        
    	grpName.setTag(groupPosition);
    	grpColor.setTag(color);
        
        return convertView;
    }
 
    @Override
    public Group getGroup(int groupPosition) {
        return MainActivity.listGroupObjs.get(groupPosition);
    }
 
    @Override
    public int getGroupCount() {
        return MainActivity.listGroupObjs.size();
    }
 
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = mInflater.inflate(R.layout.group_list_header, null);
        
        String name = (String) getGroup(groupPosition).getName();
        int color = (Integer) getGroup(groupPosition).getColor();
        
        TextView grpHeaders = (TextView) convertView.findViewById(R.id.grpHeaders);
        ImageView colorBlock = (ImageView) convertView.findViewById(R.id.colorBlock);
        
        grpHeaders.setText(name);
        if(color == 0)
        	colorBlock.setImageResource(R.drawable.green_box);
    	if(color == 1)
    		colorBlock.setImageResource(R.drawable.blue_box);
    	if(color == 2)
    		colorBlock.setImageResource(R.drawable.purple_box);
    	if(color == 3)
    		colorBlock.setImageResource(R.drawable.red_box);
    	if(color == 4)
    		colorBlock.setImageResource(R.drawable.orange_box);
    	if(color == 5)
    		colorBlock.setImageResource(R.drawable.white_box);
        
        return convertView;
    }
 
    @Override
    public boolean hasStableIds() {
        return true;
    }
 
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}