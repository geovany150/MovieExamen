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

import com.jchin.movie.R;
import com.jchin.movie.TelevisionActivity;
import com.jchin.movie.adapter.TvAdapter;
import com.jchin.movie.adapter.TvHorizontalAdapter;
import com.jchin.movie.model.ModelTV;
import com.jchin.movie.retrofit.ApiAdapter;
import com.jchin.movie.retrofit.ResultsTv;
import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jchin on 24-11-2021.
 */

public class FragmentTV extends Fragment implements TvHorizontalAdapter.onSelectData, TvAdapter.onSelectData {

    private RecyclerView rvNowPlaying, rvFilmRecommend;
    private TvHorizontalAdapter tvHorizontalAdapter;
    private TvAdapter tvAdapter;
    private ProgressDialog progressDialog;
    private SearchView searchFilm;
    private List<ModelTV> tvPlayNow = new ArrayList<>();
    private List<ModelTV> tvPopular = new ArrayList<>();

    public FragmentTV() {
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
                setSearchTv(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals(""))
                    getFilmTV();
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

        getTvHorizontal();
        getFilmTV();

        return rootView;
    }

    private void setSearchTv(String query) {
        progressDialog.show();
        Call<ResultsTv> call = ApiAdapter.getApiService().setSearchTv(query);
        call.enqueue(new Callback<ResultsTv>() {
            @Override
            public void onResponse(Call<ResultsTv> call, Response<ResultsTv> response) {
                progressDialog.dismiss();
                tvPopular = response.body().getResults();
                showFilmTV();
            }

            @Override
            public void onFailure(Call<ResultsTv> call, Throwable t) {
                progressDialog.dismiss();
                t.getMessage();
            }

        });
    }

    private void getTvHorizontal() {
        progressDialog.show();
        Call<ResultsTv> call = ApiAdapter.getApiService().getTvHorizontal();
        call.enqueue(new Callback<ResultsTv>() {
            @Override
            public void onResponse(Call<ResultsTv> call, Response<ResultsTv> response) {
                progressDialog.dismiss();
                tvPlayNow = response.body().getResults();
                showMovieHorizontal();
            }

            @Override
            public void onFailure(Call<ResultsTv> call, Throwable t) {
                progressDialog.dismiss();
                t.getMessage();
            }

        });
    }

    private void getFilmTV() {
        progressDialog.show();
        Call<ResultsTv> call = ApiAdapter.getApiService().getFilmTV();
        call.enqueue(new Callback<ResultsTv>() {
            @Override
            public void onResponse(Call<ResultsTv> call, Response<ResultsTv> response) {
                progressDialog.dismiss();
                tvPopular = response.body().getResults();
                showFilmTV();
            }

            @Override
            public void onFailure(Call<ResultsTv> call, Throwable t) {
                progressDialog.dismiss();
                t.getMessage();
            }

        });
    }

    private void showMovieHorizontal() {
        tvHorizontalAdapter = new TvHorizontalAdapter(getActivity(), tvPlayNow, this);
        rvNowPlaying.setAdapter(tvHorizontalAdapter);
        tvHorizontalAdapter.notifyDataSetChanged();
    }

    private void showFilmTV() {
        tvAdapter = new TvAdapter(getActivity(), tvPopular, this);
        rvFilmRecommend.setAdapter(tvAdapter);
        tvAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSelected(ModelTV modelTV) {
        Intent intent = new Intent(getActivity(), TelevisionActivity.class);
        intent.putExtra("detailTV", modelTV);
        startActivity(intent);
    }
}
