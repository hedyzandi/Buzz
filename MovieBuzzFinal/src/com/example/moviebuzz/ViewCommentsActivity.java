package com.example.moviebuzz;

import com.example.moviebuzz.R;
import com.example.moviebuzz.Comments;
import com.example.moviebuzz.MovieDb;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.support.v4.app.NavUtils;

public class ViewCommentsActivity extends Activity {
	
	private static final String TAG = "ViewCommentsActivity";
	int movieID = MovieDb.getID();
	private SimpleCursorAdapter mAdapter;
	private int movieId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_comments);
		// Show the Up button in the action bar.
		setupActionBar();
		
		movieId = getIntent().getIntExtra("movieId",0);
		if (movieId == 0) {
			throw new IllegalArgumentException("Value of 0 was passed to movie ID" );
		}
		//aff
		
		Uri movieUri = android.net.Uri.parse(Comments.Comment.CONTENT_URI + "movie/" + this.movieID);
		ContentResolver contentResolver = getContentResolver();
		Cursor cursor = contentResolver.query(movieUri, null , null, null, null);
		startManagingCursor(cursor);
		
		logCursor(cursor);
		//int colNoteIndex = cursor.getColumnIndex(Comments.Comment.COLUMN_NAME_COMMENT);
		
//        String text = cursor.getString(colNoteIndex);
//        log.i(text);
//		
//		//Log.d("CURSOR_CONTENT", text);
//		// The desired columns to be bound
		String[] columns = new String[] {
				    // These should be the names of the columns from Comments.Comment that you want to display
		//		    Comments.Comment.COLUMN_NAME_CREATE_DATE,
				    Comments.Comment.COLUMN_NAME_COMMENT
			 };
		
		 Log.i(TAG, "MovieID " + this.movieID);
		
		
//		
		int[] to = new int[] { 
//			    // These should be IDs for TextViews in your target view
		//	    R.id.date,
			    R.id.commentText
			    };
//				 
//				  // create the adapter using the cursor pointing to the desired data 
//				  //as well as the layout information
		mAdapter = new SimpleCursorAdapter(
			    this, 
			    // This should be the ID for your target view
			    R.layout.list_view, 
			    cursor, 
			    columns, 
			    to,
			    0);
			
		ListView listView = (ListView) findViewById(R.id.viewCommentsList);
		listView.setAdapter(mAdapter);
	}

	private void logCursor(Cursor cursor) {
		if (cursor.moveToFirst()) {
			int colNoteIndex = cursor.getColumnIndex(Comments.Comment.COLUMN_NAME_COMMENT);
			int colNoteStamp = cursor.getColumnIndex(Comments.Comment.COLUMN_NAME_CREATE_DATE);
			int colNoteCID = cursor.getColumnIndex(Comments.Comment._ID);
			int colNoteMID = cursor.getColumnIndex(Comments.Comment.COLUMN_MOVIE_ID);
			
	        String text = cursor.getString(colNoteIndex);
	        int date = cursor.getInt(colNoteStamp);
	        //TODO  These are hard coded, and shouldn't be
	        Log.i(TAG, "Comment text: " + text);
	        Log.i(TAG, "Comment date: " + date);
		}
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_comments, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
