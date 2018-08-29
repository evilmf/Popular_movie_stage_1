package com.apptivedeals.moviedb.to;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MovieDetail implements Parcelable {

    private long id;
    @SerializedName(value = "original_title")
    private String originalTitle;
    @SerializedName(value = "poster_path")
    private String posterPath;
    private String overview;
    @SerializedName(value = "vote_average")
    private float userRating;
    @SerializedName(value = "release_date")
    private String releaseDate;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public float getUserRating() {
        return userRating;
    }

    public void setUserRating(float userRating) {
        this.userRating = userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public static final Parcelable.Creator<MovieDetail> CREATOR
            = new Parcelable.Creator<MovieDetail>() {
        public MovieDetail createFromParcel(Parcel in) {
            return new MovieDetail(in);
        }

        public MovieDetail[] newArray(int size) {
            return new MovieDetail[size];
        }
    };

    private MovieDetail(Parcel in) {
        id = in.readLong();
        originalTitle = in.readString();
        posterPath = in.readString();
        overview = in.readString();
        userRating = in.readFloat();
        releaseDate = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flag) {
        parcel.writeLong(id);
        parcel.writeString(originalTitle);
        parcel.writeString(posterPath);
        parcel.writeString(overview);
        parcel.writeFloat(userRating);
        parcel.writeString(releaseDate);
    }
}
