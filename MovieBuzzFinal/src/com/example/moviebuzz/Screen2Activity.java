
package com.example.moviebuzz;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.*;

import org.json.JSONException;

import com.example.moviebuzz.MobileArrayAdapter;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import net.sf.jtmdb.CastInfo;
import net.sf.jtmdb.GeneralSettings;
import net.sf.jtmdb.Movie;
import net.sf.jtmdb.Auth;
import net.sf.jtmdb.MovieImages;
import net.sf.jtmdb.MoviePoster;

public class Screen2Activity extends ListActivity  {
	
	private static final int BUFFER_IO_SIZE = 8000;
	private static String movieName;
	private static List<Movie> searchResults = new ArrayList<Movie>();
	static List<String> movieNames = new ArrayList<String>();
	static List<String> moviesURL = new ArrayList<String>();
	static int movieIndex = -1; 
	

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
		
			System.out.println("TEST444");

			movieNames.clear();
			moviesURL.clear();
			for (Movie movie : searchResults) {
				movieNames.add(movie.getName());
				
				System.out.println(movie.getName());
				
			     MovieImages poster;
				try {
					poster = movie.getImages(movie.getID());
				     Set<MoviePoster> temp = new HashSet<MoviePoster>();
				     if(poster!= null && poster.posters!= null && poster.posters.size() == 0){
				    	 moviesURL.add("http://www.designofsignage.com/application/symbol/building/image/600x600/no-photo.jpg");
				     }
				       for(MoviePoster a: poster.posters) {

				    	   moviesURL.add(a.getSmallestImage().toString());
				    	   System.out.println(a.getSmallestImage());
				    	   break;
				       }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			     

			}
						
			setListAdapter(new MobileArrayAdapter(this, movieNames, moviesURL));
		}
		
		@Override
		protected void onListItemClick(ListView l, View v, int position, long id) {
	 
			MovieDb moviedb = new MovieDb();
			
			try {
				moviedb.movieDetailes(searchResults.get(position));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String posterurl = moviedb.getLargerPosterUrl();
			
			Specs spec = new Specs();
			spec.setMovieUrl(posterurl);
			spec.setMovieName(moviedb.getMovieName());
			spec.setMovieOverview(moviedb.getMovieOverview());
			spec.setReleaseDate(moviedb.getReleaseDate());
			spec.setMovieURL(moviedb.getMovieTrailer());
			spec.setMovieID(moviedb.getID());
			spec.setMovieRating(moviedb.getRating());
			spec.setCast(moviedb.getCast());
			
			Intent intent = new Intent(Screen2Activity.this, Specs.class);  
			startActivity(intent);   
//			//get selected items
//			String selectedValue = (String) getListAdapter().getItem(position);
//			Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
	 
		}
		

		public void setMovieName(String name) {
			movieName = name;
		}
		
		public String getMovieName() {
			return movieName;
		}
		@SuppressWarnings("unchecked")
		public void setMovies(List<Movie> movies) throws IOException, JSONException {
			
			searchResults.clear();
			searchResults.addAll(movies);

		}
		
		public List<Movie> getMovies() {
			return searchResults;
		}
	}
