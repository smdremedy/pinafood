package com.byoutline.pinafood.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.byoutline.pinafood.PinAFoodApp;
import com.byoutline.pinafood.R;
import com.byoutline.pinafood.adapters.TumblrPostsAdapter;
import com.byoutline.pinafood.api.parse.model.Pin;
import com.byoutline.pinafood.api.tumblr.TumblrApi;
import com.byoutline.pinafood.api.tumblr.model.Post;
import com.byoutline.pinafood.api.tumblr.model.TumblrResponse;
import com.byoutline.pinafood.managers.PinsManager;
import com.byoutline.pinafood.managers.UserManager;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddPinFragment extends Fragment implements TumblrPostsAdapter.OnPostClickListener {

    @InjectView(R.id.blogs_rv)
    RecyclerView recyclerView;

    @InjectView(R.id.add_pin_swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private TumblrPostsAdapter adapter;

    @Inject
    Bus bus;
    @Inject
    UserManager userManager;
    @Inject
    PinsManager pinsManager;

    @Inject
    TumblrApi tumblrApi;

    public AddPinFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_pin, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
        setUpAdapters();
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.accent);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_purple);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                refreshDataFromApi();
            }
        });
    }

    private void setUpAdapters() {

        adapter = new TumblrPostsAdapter(getActivity(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(adapter);

    }


    //    @OnItemClick(R.id.blogs_rv)
//    public void itemClicked(int position) {
//
//
//    }

    @Override
    public void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PinAFoodApp.doDaggerInject(this);

        refreshDataFromApi();
    }

    private void refreshDataFromApi() {
        tumblrApi.getMunchiesPosts(new Callback<TumblrResponse>() {
            @Override
            public void success(TumblrResponse tumblrResponse, retrofit.client.Response response) {

                adapter.addAll(tumblrResponse.getResponse().getPosts());
                swipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Override
    public void postClicked(Post post) {
        Pin pin = new Pin(post, userManager.userObjectId);
        pinsManager.postNewPin(pin);

    }
}
