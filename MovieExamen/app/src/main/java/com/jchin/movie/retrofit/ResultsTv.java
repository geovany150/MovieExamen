package com.jchin.movie.retrofit;

import com.google.gson.annotations.SerializedName;
import com.jchin.movie.model.ModelMovie;
import com.jchin.movie.model.ModelTV;

import java.util.List;

public class ResultsTv {
    public static final String RESULTS = "results";
    @SerializedName(RESULTS)
    private List<ModelTV> results;
    public static final String PAGE = "page";
    @SerializedName(PAGE)
    private int page;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<ModelTV> getResults() {
        return results;
    }

    public void setResults(List<ModelTV> results) {
        this.results = results;
    }
}
