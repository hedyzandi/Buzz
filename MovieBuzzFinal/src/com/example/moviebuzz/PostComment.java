package com.example.moviebuzz;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class PostComment extends Activity {

	private int movieId;
	private EditText mEditText;
	private static final String TAG = "PostComment";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_comment);
		mEditText = (EditText) findViewById(R.id.commenttextbox);
		movieId = getIntent().getIntExtra("movieId",0);
		if (movieId == 0) {
			throw new IllegalArgumentException("Value of 0 was passed to movie ID" );
		}
	}
	
	//what movie ID are you posting a comment for?
	public int getMovieId (){
	return movieId;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.post_comment, menu);
		return true;
	}
	
	public void cancelOnClick(View v) {
        finish();
    }
	
	public void postOnClick(View z) {
		Log.w(TAG, "begining of postOnClick");
		String commenttext = mEditText.getText().toString();
		Log.w(TAG, "after commenttext");
		Uri movieUri = android.net.Uri.parse(Comments.Comment.CONTENT_URI + "movie/" + this.movieId);
		ContentResolver contentResolver = getContentResolver();
		ContentValues values = new ContentValues();
		values.put(Comments.Comment.COLUMN_NAME_COMMENT, commenttext);
		Log.w(TAG, "after put statement");
		Uri commentUri = contentResolver.insert(movieUri, values);
		Log.w(TAG, "after ContentResolver insert");
		finish();
		Log.w(TAG, "after finish");
		//TODO Should we pass CommentID or COmmentURI back to specs?
	}
		
	}
	


