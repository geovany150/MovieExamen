package com.jchin.movie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jchin.movie.MovieActivity;
import com.jchin.movie.R;
import com.jchin.movie.adapter.MovieAdapter;
import com.jchin.movie.model.ModelMovie;
import com.jchin.movie.realm.RealmHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jchin on 24-11-2021.
 */

public class FragmentFavoriteMovie extends Fragment implements MovieAdapter.onSelectData {

    private RecyclerView rvMovieFav;
    private List<ModelMovie> modelMovie = new ArrayList<>();
    private RealmHelper helper;
    private TextView txtNoData;

    public FragmentFavoriteMovie() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorite_film, container, false);

        helper = new RealmHelper(getActivity());

        txtNoData = rootView.findViewById(R.id.tvNotFound);
        rvMovieFav = rootView.findViewById(R.id.rvMovieFav);
        rvMovieFav.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMovieFav.setAdapter(new MovieAdapter(getActivity(), modelMovie, this));
        rvMovieFav.setHasFixedSize(true);

        setData();

        return rootView;
    }

    private void setData() {
        modelMovie = helper.showFavoriteMovie();
        if (modelMovie.size() == 0) {
            txtNoData.setVisibility(View.VISIBLE);
            rvMovieFav.setVisibility(View.GONE);
        } else {
            txtNoData.setVisibility(View.GONE);
            rvMovieFav.setVisibility(View.VISIBLE);
            rvMovieFav.setAdapter(new MovieAdapter(getActivity(), modelMovie, this));
        }
    }

    @Override
    public void onSelected(ModelMovie modelMovie) {
        /*
        Intent intent = new Intent(getActivity(), MovieActivity.class);
        intent.putExtra("detailMovie", modelMovie);
        startActivity(intent);*/
    }

    @Override
    public void onResume() {
        super.onResume();
        setData();
    }
}