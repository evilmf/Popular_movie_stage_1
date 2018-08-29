package com.apptivedeals.moviedb;

import android.net.Uri;
import android.os.AsyncTask;

import com.apptivedeals.moviedb.service.GsonSingleton;
import com.apptivedeals.moviedb.to.MovieList;
import com.apptivedeals.moviedb.utilities.MovieDbProperties;
import com.apptivedeals.moviedb.utilities.NetworkUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class MovieListQueryTask extends AsyncTask<URL, Void, MovieList> {

    private static final Properties properties = MovieDbProperties.getInstance();
    private static final String MOVIEDB_BASE_URL = properties.getProperty("MOVIEDB_BASE_URL");
    private static final String PARAM_API_KEY = "api_key";
    private static final String API = "movie";
    private static final String PARAM_PAGE = "page";
    private static final String API_VERSION = properties.getProperty("API_VERSION");
    private static final String API_KEY = properties.getProperty("API_KEY");
    private static final String TAG = "MovieListQueryTask";
    private static final Gson GSON = GsonSingleton.getInstance();

    private MovieCatalogRecyclerViewAdapter adapter;

    public MovieListQueryTask(MovieCatalogRecyclerViewAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected MovieList doInBackground(URL... urls) {
        URL url = urls[0];
        String res;
        MovieList movieList = null;
        try {
            res = NetworkUtils.getResponseFromHttpUrl(url);
            movieList = GSON.fromJson(res, MovieList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movieList;
    }

    @Override
    protected void onPostExecute(MovieList movieList) {
        if (movieList == null) {
            return;
        }

        if (movieList.getPage() == 1L) {
            adapter.setMovieList(movieList.getResults());
            adapter.notifyDataSetChanged();
        }
        else {
            adapter.appendToMovieList(movieList.getResults());
            adapter.notifyDataSetChanged();
        }

        adapter.setCurrentPage(movieList.getPage());
        adapter.setTotalPage(movieList.getTotalPages());
    }

    public enum SortBy {
        POPULAR("popular"),
        TOP_RATED("top_rated");

        private final String path;

        SortBy(String path) {
            this.path = path;
        }

        public String path() {
            return this.path;
        }
    }

    public static URL buildMovieListUrl(SortBy sortBy, Long page) {
        Uri uri = Uri.parse(MOVIEDB_BASE_URL).buildUpon()
                .appendPath(API_VERSION)
                .appendPath(API)
                .appendPath(sortBy.path())
                .appendQueryParameter(PARAM_API_KEY, API_KEY)
                .appendQueryParameter(PARAM_PAGE, page.toString())
                .build();

        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
}
