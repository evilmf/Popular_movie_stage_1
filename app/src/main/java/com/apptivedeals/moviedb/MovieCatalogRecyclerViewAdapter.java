package com.apptivedeals.moviedb;

import android.content.Context;
import android.graphics.Movie;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.apptivedeals.moviedb.to.MovieList;
import com.apptivedeals.moviedb.utilities.MovieDbProperties;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Properties;

public class MovieCatalogRecyclerViewAdapter extends RecyclerView.Adapter<MovieCatalogRecyclerViewAdapter.MovieThumbnailViewHolder> {

    private List<MovieList.MovieBasic> movieList;
    private MovieListQueryTask.SortBy sortBy;
    private Long currentPage;
    private long totalPage;
    final private MovieListClickHandler movieListClickHandler;
    private static final Properties properties = MovieDbProperties.getInstance();
    private static final String TAG = "MovieCatalogAdapter";

    public MovieCatalogRecyclerViewAdapter(MovieListClickHandler movieListClickHandler) {
        Log.i(TAG, "constructor");
        this.movieListClickHandler = movieListClickHandler;
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    public void setMovieList(List<MovieList.MovieBasic> movieList) {
        this.movieList = movieList;
    }

    public void appendToMovieList(List<MovieList.MovieBasic> movieList) {
        this.movieList.addAll(movieList);
    }

    public MovieListQueryTask.SortBy getSortBy() {
        return sortBy;
    }

    public void setSortBy(MovieListQueryTask.SortBy sortBy) {
        this.sortBy = sortBy;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public interface MovieListClickHandler {
        void onClickHandler(MovieList.MovieBasic movieBasic);
    }

    public class MovieThumbnailViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        View view;

        MovieThumbnailViewHolder(View view) {
            super(view);
            this.view = view;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            movieListClickHandler.onClickHandler(movieList.get(pos));
        }
    }

    @Override
    public MovieThumbnailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int gridItemLayoutId = R.layout.movie_grid_item;
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.movie_grid_item, parent, false);
        MovieThumbnailViewHolder viewHolder = new MovieThumbnailViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieThumbnailViewHolder holder, int position) {
        ImageView imageView = holder.view.findViewById(R.id.iv_movie_detail_thumbnail);
        Context context = holder.view.getContext();
        String imagePath = movieList.get(position).getPosterPath();
        Uri imageUri = Uri.parse(properties.getProperty("MOVIEDB_IMAGE_BASE_URL")).buildUpon()
                .appendPath("w185")
                .appendPath(imagePath.replace("/", ""))
                .build();
        String imageUrl = imageUri.toString();

        Picasso.with(context).load(imageUrl).into(imageView);
    }

    @Override
    public int getItemCount() {

        return movieList == null ? 0 : movieList.size();
    }
}
