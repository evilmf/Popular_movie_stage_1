package com.apptivedeals.moviedb.to;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieList {

    private long page;
    @SerializedName(value = "total_results")
    private long totalResult;
    @SerializedName(value = "total_pages")
    private long totalPages;
    private List<MovieBasic> results;

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(long totalResult) {
        this.totalResult = totalResult;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public List<MovieBasic> getResults() {
        return results;
    }

    public void setResults(List<MovieBasic> results) {
        this.results = results;
    }

    public static class MovieBasic implements Parcelable {

        @SerializedName(value = "vote_count")
        private long voteCount;
        private long id;
        private boolean video;
        @SerializedName(value = "vote_average")
        private Float voteAverage;
        private String title;
        private float popularity;
        @SerializedName(value = "poster_path")
        private String posterPath;
        @SerializedName(value = "original_language")
        private String originalLanguage;
        @SerializedName(value = "original_title")
        private String originalTitle;
        @SerializedName(value = "genre_ids")
        private List<Long> genreId;
        @SerializedName(value = "backdrop_path")
        private String backdropPath;
        private boolean adult;
        private String overview;
        @SerializedName(value = "release_date")
        private String releaseDate;

        public static final Creator<MovieBasic> CREATOR = new Creator<MovieBasic>() {
            @Override
            public MovieBasic createFromParcel(Parcel in) {
                return new MovieBasic(in);
            }

            @Override
            public MovieBasic[] newArray(int size) {
                return new MovieBasic[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int flag) {
            parcel.writeLong(id);
            parcel.writeFloat(voteAverage);
            parcel.writeString(title);
            parcel.writeString(posterPath);
            parcel.writeString(overview);
            parcel.writeString(releaseDate);
        }

        private MovieBasic(Parcel in) {
            id = in.readLong();
            voteAverage = in.readFloat();
            title = in.readString();
            posterPath = in.readString();
            overview = in.readString();
            releaseDate = in.readString();
        }

        public long getVoteCount() {
            return voteCount;
        }

        public void setVoteCount(long voteCount) {
            this.voteCount = voteCount;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public boolean isVideo() {
            return video;
        }

        public void setVideo(boolean video) {
            this.video = video;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public float getPopularity() {
            return popularity;
        }

        public void setPopularity(float popularity) {
            this.popularity = popularity;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public String getOriginalLanguage() {
            return originalLanguage;
        }

        public void setOriginalLanguage(String originalLanguage) {
            this.originalLanguage = originalLanguage;
        }

        public String getOriginalTitle() {
            return originalTitle;
        }

        public void setOriginalTitle(String originalTitle) {
            this.originalTitle = originalTitle;
        }

        public List<Long> getGenreId() {
            return genreId;
        }

        public void setGenreId(List<Long> genreId) {
            this.genreId = genreId;
        }

        public String getBackdropPath() {
            return backdropPath;
        }

        public void setBackdropPath(String backdropPath) {
            this.backdropPath = backdropPath;
        }

        public boolean isAdult() {
            return adult;
        }

        public void setAdult(boolean adult) {
            this.adult = adult;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public Float getVoteAverage() {
            return voteAverage;
        }

        public void setVoteAverage(Float voteAverage) {
            this.voteAverage = voteAverage;
        }
    }
}
