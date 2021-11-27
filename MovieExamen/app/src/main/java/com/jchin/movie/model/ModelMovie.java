package com.jchin.movie.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by jchin on 26-11-2021.
 */

public class ModelMovie extends RealmObject implements Serializable {
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String VOTEAVERAGE = "vote_average";
    public static final String OVERVIEW = "overview";
    public static final String RELEASEDATE = "release_date";
    public static final String POSTERPATH = "poster_path";
    public static final String BACKDROPPATH = "backdrop_path";
    public static final String POPULARITY = "popularity";

    @SerializedName(ID)
    private int Id;
    @SerializedName(TITLE)
    private String Title;
    @SerializedName(VOTEAVERAGE)
    private double VoteAverage;
    @SerializedName(OVERVIEW)
    private String Overview;
    @SerializedName(RELEASEDATE)
    private String ReleaseDate;
    @SerializedName(POSTERPATH)
    private String PosterPath;
    @SerializedName(BACKDROPPATH)
    private String BackdropPath;
    @SerializedName(POPULARITY)
    private String Popularity;


    public ModelMovie() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public double getVoteAverage() {
        return VoteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        VoteAverage = voteAverage;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String overview) {
        Overview = overview;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        ReleaseDate = releaseDate;
    }

    public String getPosterPath() {
        return PosterPath;
    }

    public void setPosterPath(String posterPath) {
        PosterPath = posterPath;
    }

    public String getBackdropPath() {
        return BackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        BackdropPath = backdropPath;
    }

    public String getPopularity() {
        return Popularity;
    }

    public void setPopularity(String popularity) {
        Popularity = popularity;
    }
}
