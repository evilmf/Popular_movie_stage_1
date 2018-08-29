package com.apptivedeals.moviedb;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.apptivedeals.moviedb.to.MovieList;
import com.apptivedeals.moviedb.utilities.MovieDbProperties;
import com.squareup.picasso.Picasso;

import java.util.Properties;

public class MovieDetailActivity extends AppCompatActivity {

    private TextView tvTitle;
    private ImageView ivThumbnail;
    private TextView tvRelease;
    private TextView tvUserRating;
    private TextView tvOverview;
    private static final Properties properties = MovieDbProperties.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        tvTitle = findViewById(R.id.tv_movie_detail_title);
        ivThumbnail = findViewById(R.id.iv_movie_detail_thumbnail);
        tvRelease = findViewById(R.id.tv_movie_detail_release);
        tvUserRating = findViewById(R.id.tv_movie_detail_user_rating);
        tvOverview = findViewById(R.id.tv_movie_detail_overview);

        Intent intent = getIntent();
        if (intent.hasExtra("com.apptivedeals.moviedb.to.MovieList.MovieBasic")) {
            MovieList.MovieBasic movieBasic = intent.getParcelableExtra("com.apptivedeals.moviedb.to.MovieList.MovieBasic");

            tvTitle.setText(movieBasic.getTitle());

            String imagePath = movieBasic.getPosterPath();
            Uri imageUri = Uri.parse(properties.getProperty("MOVIEDB_IMAGE_BASE_URL")).buildUpon()
                    .appendPath("w185")
                    .appendPath(imagePath.replace("/", ""))
                    .build();
            Picasso.with(ivThumbnail.getContext()).load(imageUri.toString()).into(ivThumbnail);

            String year = movieBasic.getReleaseDate().split("-")[0];
            tvRelease.setText(year);

            tvUserRating.setText(movieBasic.getVoteAverage().toString());
            tvOverview.setText(movieBasic.getOverview());
        }
    }
}
