package com.apptivedeals.moviedb.service;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class GsonSingleton {

    private static final Gson instance;

    private GsonSingleton() {
    }

    static {
        instance = new GsonBuilder().create();
    }

    public static Gson getInstance() {
        return instance;
    }
}
