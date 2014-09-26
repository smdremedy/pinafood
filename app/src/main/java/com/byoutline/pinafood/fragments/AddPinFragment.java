package com.byoutline.pinafood.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.byoutline.pinafood.adapters.TumblrPostsAdapter;
import com.byoutline.pinafood.api.tumblr.TumblrApi;
import com.byoutline.pinafood.api.parse.ParseService;
import com.byoutline.pinafood.PinAFoodApp;
import com.byoutline.pinafood.events.PinAddedEvent;
import com.byoutline.pinafood.api.tumblr.model.Post;
import com.byoutline.pinafood.R;
import com.byoutline.pinafood.api.tumblr.model.TumblrResponse;
import com.byoutline.pinafood.managers.PinsManager;
import com.byoutline.pinafood.managers.UserManager;
import com.byoutline.pinafood.api.parse.model.Pin;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddPinFragment extends Fragment {

    @InjectView(R.id.blogs_lv)
    ListView listView;
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
        View rootView = inflater.inflate(R.layout.fragment_add_pin, container, false);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @OnItemClick(R.id.blogs_lv)
    public void itemClicked(int position) {

        final Post item = adapter.getItem(position);
        Pin pin = new Pin(item, userManager.userObjectId);
        pinsManager.postNewPin(pin);
    }

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
        adapter = new TumblrPostsAdapter(getActivity());


        tumblrApi.getMunchiesPosts(new Callback<TumblrResponse>() {
            @Override
            public void success(TumblrResponse tumblrResponse, retrofit.client.Response response) {

                adapter.addAll(tumblrResponse.getResponse().getPosts());
                listView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
