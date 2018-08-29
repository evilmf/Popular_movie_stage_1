package com.apptivedeals.moviedb.utilities;

import com.apptivedeals.moviedb.BuildConfig;

import java.util.Properties;

public class MovieDbProperties {

    private static final Properties instance;

    static {
        instance = new Properties();

        instance.setProperty("MOVIEDB_BASE_URL", "http://api.themoviedb.org/");
        instance.setProperty("MOVIEDB_IMAGE_BASE_URL", "http://image.tmdb.org/t/p/");
        instance.setProperty("API_KEY", BuildConfig.API_KEY);
        instance.setProperty("API_VERSION", "3");
    }

    private MovieDbProperties() {

    }

    public static Properties getInstance() {
        return instance;
    }
}
