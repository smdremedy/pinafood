package com.byoutline.pinafood.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.byoutline.pinafood.adapters.TumblrPostsAdapter;
import com.byoutline.pinafood.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddPinFragment extends Fragment {

    ListView listView;
    private TumblrPostsAdapter adapter;


    public AddPinFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_pin, container, false);
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new TumblrPostsAdapter(getActivity());
    }
}
