package com.apptivedeals.moviedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.apptivedeals.moviedb.to.MovieList;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements MovieCatalogRecyclerViewAdapter.MovieListClickHandler
{

    private static final String TAG = "MainActivity";

    private Integer sortMenuSelected = null;
    private MovieCatalogRecyclerViewAdapter adapter = null;
    private MovieListQueryTask movieListQueryTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView movieRecyclerView = findViewById(R.id.rv_movie_thumbnail);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        movieRecyclerView.setLayoutManager(layoutManager);

        adapter = new MovieCatalogRecyclerViewAdapter(this);

        RecyclerView.OnScrollListener onScrollListener = new MovieListScrollListener(adapter);
        movieRecyclerView.addOnScrollListener(onScrollListener);

        movieRecyclerView.setAdapter(adapter);

        executeMovieDbQueryTask(MovieListQueryTask.SortBy.POPULAR, 1L);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_menu, menu);
        sortMenuSelected = R.id.sort_by_popularity;
        MenuItem item = menu.findItem(sortMenuSelected);
        item.setEnabled(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        sortMenuSelected = item.getItemId();
        switch (sortMenuSelected) {
            case R.id.sort_by_popularity:
                Log.i(TAG, "Clicked menu option - sort_by_popularity");
                executeMovieDbQueryTask(MovieListQueryTask.SortBy.POPULAR, 1L);
                return true;
            case R.id.sort_by_top_rated:
                Log.i(TAG, "Clicked menu option - sort_by_top_rated");
                executeMovieDbQueryTask(MovieListQueryTask.SortBy.TOP_RATED, 1L);
                return true;
            default:
                Log.i(TAG, "Default menu option handling");
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu (Menu menu) {
        menu.setGroupEnabled(R.id.sort_group, true);
        if (sortMenuSelected != null) {
            MenuItem menuItem = menu.findItem(sortMenuSelected);
            menuItem.setEnabled(false);
        }

        return true;
    }

    private void executeMovieDbQueryTask(MovieListQueryTask.SortBy sortBy, Long page) {
        if (movieListQueryTask != null) {
            movieListQueryTask.cancel(true);
        }

        movieListQueryTask = new MovieListQueryTask(adapter);
        URL url = MovieListQueryTask.buildMovieListUrl(sortBy, page);
        movieListQueryTask.execute(url);
    }

    @Override
    public void onClickHandler(MovieList.MovieBasic movieBasic) {
        Log.i(TAG, "onClickHandler");

        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("com.apptivedeals.moviedb.to.MovieList.MovieBasic", movieBasic);
        startActivity(intent);
    }

    class MovieListScrollListener extends RecyclerView.OnScrollListener {

        private static final String TAG = "MovieListScrollListener";
        private MovieCatalogRecyclerViewAdapter adapter;

        MovieListScrollListener(MovieCatalogRecyclerViewAdapter adapter) {
            this.adapter = adapter;
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if (!recyclerView.canScrollVertically(1)) {
                Long currentPage = adapter.getCurrentPage();
                Long totalPage = adapter.getTotalPage();

                if (currentPage == null || totalPage == null) {
                    executeMovieDbQueryTask(MovieListQueryTask.SortBy.POPULAR, 1L);
                    return;
                }

                if (currentPage < totalPage) {
                    long page = currentPage + 1;
                    if (sortMenuSelected == R.id.sort_by_popularity) {
                        executeMovieDbQueryTask(MovieListQueryTask.SortBy.POPULAR, page);
                    }
                    else if (sortMenuSelected == R.id.sort_by_top_rated) {
                        executeMovieDbQueryTask(MovieListQueryTask.SortBy.TOP_RATED, page);
                    }
                }
            }
        }

    }
}
