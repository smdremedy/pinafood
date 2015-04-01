package com.byoutline.pinafood.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.byoutline.pinafood.PinAFoodApp;
import com.byoutline.pinafood.R;
import com.byoutline.pinafood.adapters.PinsAdapter;
import com.byoutline.pinafood.api.parse.ParseService;
import com.byoutline.pinafood.events.PinsFetchedEvent;
import com.byoutline.pinafood.managers.PinsManager;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PinnedFoodFragment extends Fragment {

    @InjectView(R.id.pins_sgv)
    RecyclerView recyclerView;

    private PinsAdapter adapter;

    @Inject
    ParseService parseService;
    @Inject
    Bus bus;
    @Inject
    PinsManager pinsManager;

    public PinnedFoodFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pinned_food, container, false);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PinAFoodApp.doDaggerInject(this);
        adapter = new PinsAdapter(getActivity().getApplicationContext(), bus);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        pinsManager.fetchPins();
    }

    @Override
    public void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Subscribe
    public void onPinsFetched(PinsFetchedEvent event) {
        adapter.addAll(event.pins);
    }
}
