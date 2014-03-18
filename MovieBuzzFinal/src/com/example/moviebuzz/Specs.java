package com.example.moviebuzz;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewDebug.IntToString;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import net.sf.jtmdb.CastInfo;
import net.sf.jtmdb.GeneralSettings;
import net.sf.jtmdb.Movie;
import net.sf.jtmdb.Auth;
import net.sf.jtmdb.MovieImages;
import net.sf.jtmdb.MoviePoster;

public class Specs extends Activity{

	private static final int BUFFER_IO_SIZE = 8000;
	private static final String TAG = "Specs";
	private static String movieUrl;
	private static String movieName;
	private static String movieOverview;
	private static String movieURL;
	private static String movieReleaseDate;
	private static int movieID;
	private static double rating;
	private static String cast;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.specs);
		
		
		
		
		
		if (movieUrl != null && movieName != null && movieURL != null && movieID != 0){
			
		
		
		System.out.println("Ths is test");
			
			
			RelativeLayout layout = (RelativeLayout) findViewById(R.id.specsLayout);
			ImageView image = (ImageView) layout.findViewById(R.id.movieImage2);
			TextView txtview = (TextView) layout.findViewById(R.id.movieName1);
			TextView txtoverview = (TextView) layout.findViewById(R.id.movieOverview);
			//aff
			ListView listView = (ListView) findViewById(R.id.listOfComments);
			TextView txtrating = (TextView) layout.findViewById(R.id.movieRating1);
			//TextView casting = (TextView) layout.findViewById(R.id.casting1);
			//VideoView video=(VideoView) layout.findViewById(R.id.movieTrailer1);
			//Drawable drawable = LoadImageFromWeb(posterurl);
			
			String movieImageUrl = getMovieUrl();
			//MediaController mc = new MediaController(this);
			//mc.setAnchorView(video);
			//mc.setMediaPlayer(video);
			//Uri uri=Uri.parse(movieURL);
			
			System.out.println("movie ID" + movieID);
			
			
			
			
			Bitmap mBitmap;
			try {
				if(movieImageUrl == null || movieImageUrl.isEmpty()){
					mBitmap = ImageSizerUtility.decodeSampledBitmapFromResource(getResources(), R.drawable.nophoto, 50, 60);
				}else{
					//mBitmap = BitmapFactory.decodeStream((InputStream) new URL(movieImageUrl.toString()).getContent());
					mBitmap = ImageSizerUtility.decodeSampledBitmapFromUrl(movieImageUrl, 60, 319);
				}
			} catch (Exception e) {
				Log.e(TAG, "while decoding bitmap", e);
				throw new RuntimeException (e);
			}
			image.setImageBitmap(mBitmap);
			
	        txtview.setText(movieName);
	        txtoverview.setText(movieOverview + cast);
	        txtrating.setText("Rating : " + Double.toString(rating));
	        
	        //If trailer url is empty then donot show the play trailer button
	        if(movieURL.isEmpty()){
	        	Button btn =  (Button) findViewById(R.id.button1);
	        	btn.setVisibility(View.GONE);
	        }
			
		}
	}
	
	public void playTrailer(View view) {
		startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(movieURL)));
        Log.i("Video", "Video Playing....");
	}
	
	
   public static void setMovieID(int id) {
	   movieID = id;
	}

	public void setMovieUrl(String movieImageUrl) {
		movieUrl = movieImageUrl;
	}
	
	public String getMovieUrl(){
		return movieUrl;
	}
	
	public void setMovieName(String name) {
		movieName = name;
	}
	
	public String getMovieName() {
		return movieName;
	}
	
	public void setMovieOverview(String overview) {
		movieOverview = overview;
	}
	
	public String getMovieOverview() {
		return movieOverview;
	}
	
	public void setMovieURL(String url) {
		movieURL = url;
	}
	
	public String getMovieURL() {
		return movieURL;
	}
	
	public void setReleaseDate(String releaseDate) {
		movieReleaseDate = releaseDate;
	}
	
	public String getReleaseDate() {
		return movieReleaseDate;
	}
	
	public void setMovieRating(double movierating) {
		rating = movierating;
	}
	
	public double getMovieRating() {
		return rating;
	}
	
	public void setCast(String moviecast) {
		cast = moviecast;
	}
	
	public String getCast() {
		return cast;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
	    if ((keyCode == KeyEvent.KEYCODE_BACK))
	    {
	        finish();
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
	@SuppressWarnings("unused")
	public void render_to_screen(String movieImageUrl) {
		System.out.println("Ths is test");
		//Intent i = new Intent(getApplicationContext(), Specs.class);
        //startActivity(i);
		try {
			RelativeLayout layout = (RelativeLayout) findViewById(R.id.specsLayout);
			ImageView image = (ImageView) layout.findViewById(R.id.movieImage2);
			//Drawable drawable = LoadImageFromWeb(posterurl);
			
			Bitmap mBitmap = BitmapFactory.decodeStream((InputStream) new URL(movieImageUrl.toString()).getContent());
			image.setImageBitmap(mBitmap);
			
			Bitmap drawable = loadImageFromUrl(movieImageUrl);
	        image.setImageBitmap(drawable);
	        //setContentView(R.layout.moviedb);
			} catch (Exception e) {
				System.out.println("I am in exception");
			}
		
		setContentView(R.layout.specs);
	}
	
	private Bitmap loadImageFromUrl(final String url) {
        try {
            // Addresses bug in SDK :
            // http://groups.google.com/group/android-developers/browse_thread/thread/4ed17d7e48899b26/
            BufferedInputStream bis = new BufferedInputStream(new URL(url).openStream(), BUFFER_IO_SIZE);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(baos, BUFFER_IO_SIZE);
            copy(bis, bos);
            bos.flush();
            return BitmapFactory.decodeByteArray(baos.toByteArray(), 0, baos.size());
        } catch (IOException e) {
            // handle it properly
        	return null;
        }
    }

    private void copy(final InputStream bis, final OutputStream baos) throws IOException {
        byte[] buf = new byte[256];
        int l;
        while ((l = bis.read(buf)) >= 0) baos.write(buf, 0, l);
    }

    /*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/
    //aff
   public void goPostComment(View Y){
	   if (movieID == 0) {
		   throw new IllegalStateException ("movieID can't be zero here");
	   }
	//ToDo
	   Intent intent = new Intent(this, PostComment.class);
	   intent.putExtra("movieId", movieID);
	   startActivity(intent);
   }
    
   public void goViewComments(View Y){
	   if (movieID == 0) {
		   throw new IllegalStateException ("movieID can't be zero here");
	   }
		//ToDo
		   Intent intent = new Intent(this, ViewCommentsActivity.class);
		   intent.putExtra("movieId", movieID);
		   startActivity(intent);
	   }

}