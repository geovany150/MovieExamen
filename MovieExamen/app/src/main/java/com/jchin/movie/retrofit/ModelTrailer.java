package com.jchin.movie.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelTrailer {
    public static final String RESULTS = "results";
    @SerializedName(RESULTS)
    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public static class Result {
        public static final String KEY = "key";
        public static final String TYPE = "type";

        @SerializedName(KEY)
        private String key;
        @SerializedName(TYPE)
        private String type;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}