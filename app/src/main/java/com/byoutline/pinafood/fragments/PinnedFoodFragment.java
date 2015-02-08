package com.byoutline.pinafood.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.byoutline.pinafood.activities.AddPinActivity;
import com.byoutline.pinafood.activities.PinnedFoodActivity;
import com.byoutline.pinafood.api.parse.ParseService;
import com.byoutline.pinafood.PinAFoodApp;
import com.byoutline.pinafood.adapters.PinsAdapter;
import com.byoutline.pinafood.R;
import com.byoutline.pinafood.events.PinsFetchedEvent;
import com.byoutline.pinafood.managers.PinsManager;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PinnedFoodFragment extends Fragment {

    public static final int REQUEST_CODE = 123;

    @InjectView(R.id.pins_rv)
    RecyclerView recyclerView;

    private PinsAdapter adapter;

    @Inject
    ParseService parseService;
    @Inject
    Bus bus;
    @Inject
    PinsManager pinsManager;

    public PinnedFoodFragment() {
        setHasOptionsMenu(true);
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
        adapter = new PinsAdapter(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        pinsManager.fetchPins();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.pinned_food, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            Intent intent = new Intent(getActivity(), AddPinActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
            return true;
        } else
            return super.onOptionsItemSelected(item);
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



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((PinnedFoodActivity) activity).onSectionAttached(getString(R.string.title_activity_pinned_food));
    }

    @Subscribe
    public void onPinsFetched(PinsFetchedEvent event) {
        adapter.addAll(event.pins);
    }
}
