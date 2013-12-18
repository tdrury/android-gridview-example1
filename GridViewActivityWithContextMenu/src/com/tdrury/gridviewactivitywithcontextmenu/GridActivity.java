package com.tdrury.gridviewactivitywithcontextmenu;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;

public class GridActivity extends Activity {

	private static final String TAG = "GridActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate: called");
		setContentView(R.layout.grid);
		
		List<String> elements = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			elements.add(String.valueOf(i));
		}
		
		GridView gridView = (GridView)findViewById(R.id.grid);
		
		GridAdapter adapter = new GridAdapter(gridView.getContext(), R.layout.grid_element, elements);
		//Log.i(TAG, "onCreate: adapter ["+adapter.toString()+"]");
		gridView.setAdapter(adapter);
		registerForContextMenu(gridView);
		
//		gridView.setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				Task selectedTask = (Task)parent.getAdapter().getItem(position);
//				Log.i(TAG, "onItemClick: selected task ID ["+selectedTask.getId()+"]");
//		    	Intent intent = new Intent(parent.getContext(), TaskViewActivity.class);
//		    	intent.putExtra(TaskViewActivity.TASK_ID, selectedTask.getId());
//		    	startActivity(intent);
//			}
//		});
		
        gridView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				String selectedElement = (String)parent.getAdapter().getItem(position);
				GridAdapter adapter = (GridAdapter)parent.getAdapter();
				Log.i(TAG, "onItemLongClick: selected element ["+selectedElement+"] adapter=["+adapter+"]");
				if (adapter != null) {
					adapter.setSelectedPosition(position);
				}
                parent.showContextMenu();      
				return true;
            }
        });			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, view, menuInfo);
    	Log.i(TAG, "onContextCreateMenu: called. menuInfo is ["+menuInfo+"]");
    	
        getMenuInflater().inflate(R.menu.grid_context_menu, menu);
//		menu.add(0, R.id.action_1, 100, R.string.action_1);
//		menu.add(0, R.id.action_2, 200, R.string.action_2);
//		menu.add(0, R.id.action_3, 300, R.string.action_3);

		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        if (info != null) Log.i(TAG, "onContextItemSelected: Holy Shit! info is not null!");
        menu.setHeaderTitle("Options");        
    } 	

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        Log.i(TAG, "onContextItemSelected: called; info=["+info+"]");


        switch (item.getItemId()) {
	        case R.id.action_1:
	        	Log.i(TAG, "onContextItemSelected: ACTION_1 selected");
	        	break;
	        case R.id.action_2:
	        	Log.i(TAG, "onContextItemSelected: ACTION_2 selected");
	        	break;
	        case R.id.action_3:
	        	Log.i(TAG, "onContextItemSelected: ACTION_3 selected");
	        	break;
        }
        return true;
//        Log.i(TAG, "onContextItemSelected: notifying adapter ["+adapter+"] data change");
//        adapter.notifyDataSetChanged();
//        ((ScrumTaskBoardActivity)this.getActivity()).getPagerAdapter().notifyDataSetChanged();
//        return true;
//    	}        
//        return super.onContextItemSelected(item);
    }	
	
}
