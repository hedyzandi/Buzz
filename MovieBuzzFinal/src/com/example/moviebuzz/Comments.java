package com.example.moviebuzz;

import android.net.Uri;
import android.provider.BaseColumns;

public final class Comments {

	
	    public static final String AUTHORITY = "com.google.provider.Comment";

	    // This class cannot be instantiated
	    private Comments() {
	    }

	    /**
	     * Comments table contract
	     */
	    public static final class Comment implements BaseColumns {

	        // This class cannot be instantiated
	        private Comment() {}

	        /**
	         * The table name offered by this provider
	         */
	        
	        public static final String TABLE_NAME = "Comments";

	        /*
	         * URI definitions
	         */

	        /**
	         * The scheme part for this provider's URI
	         */
	        private static final String SCHEME = "content://";

	        /**
	         * Path parts for the URIs
	         */

	         /**
	         * Path part for the Comment ID URI
	         */
	        private static final String PATH_COMMENT_ID = "/comment/";
	        
	        /**
	         * Path part for the movie ID URI
	         */
	        private static final String PATH_MOVIE_ID = "/movie/";

	        /**
	         * 0-relative position of a comment ID segment in the path part of a comment ID URI
	         */
	        public static final int COMMENT_ID_PATH_POSITION = 1;
	        
	        /**
	         * 0-relative position of a movie ID segment in the path part of a movie ID URI
	         */
	        public static final int MOVIE_ID_PATH_POSITION = 1;

	        /**
	         * Path part for the Live Folder URI
	         */
	        private static final String PATH_LIVE_FOLDER = "/live_folders/comments";

	        /**
	         * The content:// style URL for this table
	         */
	        public static final Uri CONTENT_URI =  Uri.parse(SCHEME + AUTHORITY+ "/");

	        /**
	         * The content URI base for a single comment. Callers must
	         * append a numeric comment id to this Uri to retrieve a comment
	         */
	        public static final Uri CONTENT_ID_URI_BASE
	            = Uri.parse(SCHEME + AUTHORITY + PATH_COMMENT_ID);

	        /**
	         * The content URI match pattern for a single comment, specified by its ID. Use this to match
	         * incoming URIs or to construct an Intent.
	         */
	        public static final Uri CONTENT_ID_URI_PATTERN
	            = Uri.parse(SCHEME + AUTHORITY + PATH_COMMENT_ID + "/#");
	        
	        /**
	         * The content URI base for a single movie. Callers must
	         * append a numeric movie id to this Uri to retrieve a movie's comments
	         */
	        public static final Uri MOVIE_ID_URI_BASE
	            = Uri.parse(SCHEME + AUTHORITY + PATH_MOVIE_ID);

	        /**
	         * The content URI match pattern for a single movie, specified by its ID. Use this to match
	         * incoming URIs or to construct an Intent.
	         */
	        public static final Uri MOVIE_ID_URI_PATTERN
	            = Uri.parse(SCHEME + AUTHORITY + PATH_MOVIE_ID + "/#");

	       

	        /*
	         * MIME type definitions
	         */

	        /**
	         * The MIME type of {@link #CONTENT_URI} providing a directory of comments.
	         */
	        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.comment";

	        /**
	         * The MIME type of a {@link #CONTENT_URI} sub-directory of a single
	         * comment.
	         */
	        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.comment";

	        /**
	         * The default sort order for this table
	         */
	        public static final String DEFAULT_SORT_ORDER = "created DESC";

	        /*
	         * Column definitions
	         */

	        /**
	         * Column name for the Movie ID of the comment
	         * <P>Type: TEXT</P>
	         */
	        public static final String COLUMN_MOVIE_ID = "movie_id";

	        /**
	         * Column name of the comment content
	         * <P>Type: TEXT</P>
	         */
	        public static final String COLUMN_NAME_COMMENT = "comment";

	        /**
	         * Column name for the creation timestamp
	         * <P>Type: INTEGER (long from System.curentTimeMillis())</P>
	         */
	        public static final String COLUMN_NAME_CREATE_DATE = "created";

	       }
	}

