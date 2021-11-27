package com.jchin.movie.retrofit;

import com.jchin.movie.networking.ApiEndpoint;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET(ApiEndpoint.MOVIE_POPULAR + ApiEndpoint.APIKEY + ApiEndpoint.LANGUAGE)
    Call<Result> getMovie();

    @GET(ApiEndpoint.MOVIE_PLAYNOW + ApiEndpoint.APIKEY + ApiEndpoint.LANGUAGE)
    Call<Result> getMovieHorizontal();

    @GET(ApiEndpoint.SEARCH_MOVIE + ApiEndpoint.APIKEY + ApiEndpoint.LANGUAGE)
    Call<Result> setSearchMovie(@Query("query") String query);

    @GET(ApiEndpoint.TV_POPULAR + ApiEndpoint.APIKEY + ApiEndpoint.LANGUAGE)
    Call<ResultsTv> getFilmTV();

    @GET(ApiEndpoint.TV_PLAYNOW + ApiEndpoint.APIKEY + ApiEndpoint.LANGUAGE)
    Call<ResultsTv> getTvHorizontal();

    @GET(ApiEndpoint.SEARCH_TV + ApiEndpoint.APIKEY + ApiEndpoint.LANGUAGE)
    Call<ResultsTv> setSearchTv(@Query("query") String query);


}