package com.example.moviebuzz;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import org.json.JSONException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import net.sf.jtmdb.CastInfo;
import net.sf.jtmdb.GeneralSettings;
import net.sf.jtmdb.Movie;
import net.sf.jtmdb.Auth;
import net.sf.jtmdb.MovieImages;
import net.sf.jtmdb.MoviePoster;
import net.sf.jtmdb.MoviePoster.Size;

public class MovieDb extends AsyncTask<String, Void, String>{

	String moviename = "";
	String posterurl = "";
	String largerposterurl="";
	String movieName = "";
	String overview = "";
	String trailerURL = "";
	String releaseDate = "";
	List<Movie> searchList = new ArrayList<Movie>();
	private double rating;
	private String cast = "";
	//aff
	static int ID;
	
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		//GeneralSettings.setApiKey("9f4e8abc637cc4dacd0ae71c7a92a14c");
		Context context = null;
		try {
			System.out.println(moviename);
			//System.out.println(getMovieName());
		    List<Movie> movies = Movie.search(moviename);
		    movieList(movies);
		    /*for (Movie movie : movies) {
		        System.out.println("====================");
		        System.out.println(movie.getName());
		        System.out.println(movie.getOverview());
		        //MovieImages poster = movie.getImages();
		        //movieName = movie.getName();
		         MovieImages poster= Movie.getImages(movie.getID());
		       System.out.println(poster.posters);
		       //ImageView image = (ImageView) findViewById(R.id.movieImage);
		       Set<MoviePoster> temp = new HashSet<MoviePoster>();
		       for(MoviePoster a: poster.posters) {
		    	   
		    	   //URL url = new URL(a.getSmallestImage().toString());
		    	   //Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
		    	   //image.setImageBitmap(bmp);


		    	   posterurl = a.getSmallestImage().toString();
		    	   System.out.println(a.getSmallestImage());
		    	   break;
		       }
		        //ImageView image = new ImageView(context);
		        //image =  (ImageView) poster.posters;
		        //image.setImageResource(resId);
		        System.out.println("Cast:");
		        // Get the full decsription of the movie.
		        Movie moviedetail = Movie.getInfo(movie.getID());
		        movieName = moviedetail.getOriginalName();
		        overview = moviedetail.getOverview();
		        trailerURL = moviedetail.getTrailer().toString();
		        //aff 
		        ID = movie.getID();
		        //System.out.println("Printing trailer url"+ u);
//		        for (CastInfo cast : movieFull.getCast()) {
	//	            System.out.println("   " + cast.getName() + " as "
		//                    + cast.getCharacterName()	);
		  //      }
		        //break;
		    }*/
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		return null;
	}
	
	public String getPosterUrl() {
		return posterurl;
	}
	
	public String getLargerPosterUrl() {
		return largerposterurl;
	}
	
	public String getMovieName() {
		return movieName;
	}

	public String getMovieOverview() {
		return overview;		
	}
	
	public String getMovieTrailer() {
		return trailerURL;
	}
	
	public String getReleaseDate() {
		return releaseDate;
	}
	
	public double getRating() {
		return rating;
	}
	
	public String getCast() {
		return cast;
	}
	
    @Override
    protected void onPostExecute(String result) {
    	//ImageView image = (ImageView) findViewById(R.id.movieImage);
    }
    
	//aff
	public static int getID() {
		return ID;
	}
	
    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onProgressUpdate(Void... values) {
    }
    
	public void setMovieName(String movie) {
		moviename = movie;
	}
	
	public void movieList(List<Movie> movies) throws IOException, JSONException {
		
		System.out.println("in Movie List");
        int maxnum=10;
        if (maxnum>movies.size()) {
                maxnum=movies.size();
        }

        for (int i = 0; i<maxnum; i++) {
                searchList.add(movies.get(i));
        } 
	}
	
	public List<Movie> getMovieList() throws IOException, JSONException {
		return searchList;
	}
	
	// find the detailes of a specific movie by passing a movie object
	public void movieDetailes (Movie movie) throws IOException, JSONException{
		
	     MovieImages poster= movie.getImages(movie.getID());
	       System.out.println(poster.posters);
	     
	       Set<MoviePoster> temp = new HashSet<MoviePoster>();
	       for(MoviePoster a: poster.posters) {
	    	   
	    	   posterurl = "";
	    	   largerposterurl = "";
	    	   if(a.getSmallestImage() != null){
	    		   posterurl = a.getSmallestImage().toString();
	    	   }
	    	   if(a.getImage(Size.MID) != null){
	    		   largerposterurl = a.getImage(Size.MID).toString();
	    	   }
	    	   //System.out.println(a.getSmallestImage());
	    	   break;
	       }

	        System.out.println("Cast:");
	        // Get the full decsription of the movie.
	        Movie moviedetail = Movie.getInfo(movie.getID());
	        if(moviedetail != null){
	        	movieName = moviedetail.getOriginalName();
		        overview = moviedetail.getOverview();
		        if(moviedetail.getTrailer() != null){
		        	trailerURL = moviedetail.getTrailer().toString();
		        }
		        if(moviedetail.getReleasedDate() != null){
		        	releaseDate = moviedetail.getReleasedDate().toString();
		        }
		        rating = moviedetail.getRating();
		        cast = "\n\nCast: \n";
		        for (CastInfo moviecast : moviedetail.getCast()) {
			            System.out.println("   " + moviecast.getName() + " as "
			                    + moviecast.getCharacterName()	);
			            cast = cast + "   " + moviecast.getName() + "\n";
			    }
		        
		        ID = moviedetail.getID();
	        }
	        
	}
	
	public void movieInfo(List<Movie> movies) throws IOException, JSONException{
		for (Movie movie : movies) {
	        System.out.println("====================");
	        System.out.println(movie.getName());
	        System.out.println(movie.getOverview());
//	        System.out.println(movie.getCast());
	        //MovieImages poster = movie.getImages();
	        //movieName = movie.getName();
	        MovieImages poster= movie.getImages(movie.getID());
	       System.out.println(poster.posters);
	     
	       //ImageView image = (ImageView) findViewById(R.id.movieImage);
	       Set<MoviePoster> temp = new HashSet<MoviePoster>();
	       for(MoviePoster a: poster.posters) {
	    	   
	    	   //URL url = new URL(a.getSmallestImage().toString());
	    	   //Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
	    	   //image.setImageBitmap(bmp);


	    	   posterurl = a.getSmallestImage().toString();
	    	   System.out.println(a.getSmallestImage());
	    	   break;
	       }
	        //ImageView image = new ImageView(context);
	        //image =  (ImageView) poster.posters;
	        //image.setImageResource(resId);
	        System.out.println("Cast:");
	        // Get the full decsription of the movie.
	        Movie moviedetail = Movie.getInfo(movie.getID());
	        movieName = moviedetail.getOriginalName();
	        overview = moviedetail.getOverview();
	        trailerURL = moviedetail.getTrailer().toString();
	        releaseDate = moviedetail.getReleasedDate().toString();
	        //System.out.println(moviedetail.getCast());
	        //System.out.println("Printing trailer url"+ u);
	        //for (CastInfo cast : moviedetail.getCast()) {
	        //    System.out.println("   " + cast.getName() + " as "
	        //            + cast.getCharacterName()	);
	        //}
	        break;
	    }
		

	}
	
}


