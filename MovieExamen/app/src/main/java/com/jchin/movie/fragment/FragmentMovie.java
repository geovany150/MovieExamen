package com.jchin.movie.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jchin.movie.MovieActivity;
import com.jchin.movie.R;
import com.jchin.movie.adapter.MovieAdapter;
import com.jchin.movie.adapter.MovieHorizontalAdapter;
import com.jchin.movie.model.ModelMovie;
import com.jchin.movie.retrofit.ApiAdapter;
import com.jchin.movie.retrofit.Result;
import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jchin on 26-11-2021.
 */

public class FragmentMovie extends Fragment implements MovieHorizontalAdapter.onSelectData, MovieAdapter.onSelectData {

    private RecyclerView rvNowPlaying, rvFilmRecommend;
    private MovieHorizontalAdapter movieHorizontalAdapter;
    private MovieAdapter movieAdapter;
    private ProgressDialog progressDialog;
    private SearchView searchFilm;
    private List<ModelMovie> moviePlayNow = new ArrayList<>();
    private List<ModelMovie> moviePopular = new ArrayList<>();

    public FragmentMovie() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_film, container, false);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle(getResources().getString(R.string.wait));
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getResources().getString(R.string.showtrailler));

        searchFilm = rootView.findViewById(R.id.searchFilm);
        searchFilm.setQueryHint(getString(R.string.search_film));
        searchFilm.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                setSearchMovie(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals(""))
                    getMovie();
                return false;
            }
        });

        int searchPlateId = searchFilm.getContext().getResources()
                .getIdentifier("android:id/search_plate", null, null);
        View searchPlate = searchFilm.findViewById(searchPlateId);
        if (searchPlate != null) {
            searchPlate.setBackgroundColor(Color.TRANSPARENT);
        }

        rvNowPlaying = rootView.findViewById(R.id.rvNowPlaying);
        rvNowPlaying.setHasFixedSize(true);
        rvNowPlaying.setLayoutManager(new CardSliderLayoutManager(getActivity()));
        new CardSnapHelper().attachToRecyclerView(rvNowPlaying);

        rvFilmRecommend = rootView.findViewById(R.id.rvFilmRecommend);
        rvFilmRecommend.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFilmRecommend.setHasFixedSize(true);

        getMovieHorizontal();
        getMovie();

        return rootView;
    }

    private void setSearchMovie(String query) {
        progressDialog.show();
        Call<Result> call = ApiAdapter.getApiService().setSearchMovie(query);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                moviePopular = response.body().getResults();
                showMovie();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                t.getMessage();
            }
        });
    }

    private void getMovieHorizontal() {
        progressDialog.show();
        Call<Result> call = ApiAdapter.getApiService().getMovieHorizontal();
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                moviePlayNow = response.body().getResults();
                showMovieHorizontal();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                t.getMessage();
            }
        });
    }

    private void getMovie() {
        progressDialog.show();
        Call<Result> call = ApiAdapter.getApiService().getMovie();
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                moviePopular = response.body().getResults();
                showMovie();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                t.getMessage();
            }

        });
    }

    private void showMovieHorizontal() {
        movieHorizontalAdapter = new MovieHorizontalAdapter(getActivity(), moviePlayNow, this);
        rvNowPlaying.setAdapter(movieHorizontalAdapter);
        movieHorizontalAdapter.notifyDataSetChanged();
    }

    private void showMovie() {
        movieAdapter = new MovieAdapter(getActivity(), moviePopular, this);
        rvFilmRecommend.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSelected(ModelMovie modelMovie) {
        Intent intent = new Intent(getActivity(), MovieActivity.class);
        intent.putExtra("detailMovie", modelMovie);
        startActivity(intent);
    }


}