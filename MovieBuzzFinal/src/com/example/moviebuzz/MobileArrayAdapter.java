package com.example.moviebuzz;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;

import com.example.moviebuzz.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import net.sf.jtmdb.CastInfo;
import net.sf.jtmdb.GeneralSettings;
import net.sf.jtmdb.Movie;
import net.sf.jtmdb.Auth;
import net.sf.jtmdb.MovieImages;
import net.sf.jtmdb.MoviePoster;
 
public class MobileArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	static List<String> values = new ArrayList<String>();
	private static List<Movie> searchResults = new ArrayList<Movie>();
	static List<String> moviesURl = new ArrayList<String>(); 
	private static final int BUFFER_IO_SIZE = 8000;
	
 
	public MobileArrayAdapter(Context context, List<String> movieNames, List<String> movieURL) {
		super(context, R.layout.screen2, movieNames);
		this.context = context;
		this.values = movieNames;
		this.moviesURl = movieURL;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.screen2, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		try {
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		textView.setText(values.get(position));
 


		
		String movieImageUrl = moviesURl.get(position);

		
		Bitmap mBitmap;
		try {
			mBitmap = BitmapFactory.decodeStream((InputStream) new URL(movieImageUrl.toString()).getContent());
			imageView.setImageBitmap(mBitmap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		Bitmap drawable = loadImageFromUrl(movieImageUrl);
        imageView.setImageBitmap(drawable);
 
		}
		catch (IndexOutOfBoundsException e) {}
		return rowView;
		

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
    
	public void setMoviesURL(List<String> URLs) throws IOException, JSONException {
		
		moviesURl.clear();
		moviesURl.addAll(URLs);

	}
	
	public List<String> getMoviesURL() {
		return moviesURl;
	}
}