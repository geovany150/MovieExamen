package com.jchin.movie.retrofit;

import com.google.gson.annotations.SerializedName;
import com.jchin.movie.model.ModelMovie;

import java.util.List;

public class Result {
    public static final String RESULTS = "results";
    @SerializedName(RESULTS)
    private List<ModelMovie> results;
    public static final String PAGE = "page";
    @SerializedName(PAGE)
    private int page;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<ModelMovie> getResults() {
        return results;
    }

    public void setResults(List<ModelMovie> results) {
        this.results = results;
    }

}
