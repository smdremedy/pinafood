package com.byoutline.pinafood.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.byoutline.pinafood.activities.PinnedFoodActivity;
import com.byoutline.pinafood.api.parse.ParseService;
import com.byoutline.pinafood.PinAFoodApp;
import com.byoutline.pinafood.adapters.PinsAdapter;
import com.byoutline.pinafood.R;
import com.byoutline.pinafood.api.parse.model.PinsResult;
import com.byoutline.pinafood.events.PinsFetchedEvent;
import com.byoutline.pinafood.managers.PinsManager;
import com.etsy.android.grid.StaggeredGridView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import timber.log.Timber;

public class PinnedFoodFragment extends Fragment {

    StaggeredGridView staggeredGridView;

    private PinsAdapter adapter;


    public PinnedFoodFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pinned_food, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new PinsAdapter(getActivity().getApplicationContext());
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
