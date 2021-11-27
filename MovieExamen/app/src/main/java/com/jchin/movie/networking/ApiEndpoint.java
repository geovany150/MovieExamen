package com.jchin.movie.networking;

/**
 * Created by jchin on 26-11-2021.
 */

public interface ApiEndpoint {

    String BASEURL = "http://api.themoviedb.org/3/";
    String URLIMAGE = "https://image.tmdb.org/t/p/w780/";
    String URLFILM = "https://www.themoviedb.org/movie/";
    String APIKEY = "api_key=2fd703f83eab1f649659738ddcc0ae45";
    String LANGUAGE = "&language=es";
    String SEARCH_MOVIE = "search/movie?";
    String SEARCH_TV = "search/tv?";
    String MOVIE_PLAYNOW = "movie/now_playing?";
    String MOVIE_POPULAR = "discover/movie?";
    String TV_PLAYNOW = "tv/on_the_air?";
    String TV_POPULAR = "discover/tv?";
}
