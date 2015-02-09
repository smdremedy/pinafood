package com.byoutline.pinafood.activities;

import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.byoutline.pinafood.R;
import com.byoutline.pinafood.api.parse.model.Pin;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import timber.log.Timber;

public class PinDetailsActivity extends ActionBarActivity {

    public static final String PIN_EXTRA = "pin";
    public static final String EXTRA_IMAGE = "image";
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    public Toolbar getToolbar() {
        return toolbar;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_details);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            Pin pin = (Pin) getIntent().getSerializableExtra(PIN_EXTRA);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, PlaceholderFragment.newInstance(pin))
                    .commit();
        }
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        @InjectView(R.id.pin_details_image_iv)
        ImageView imageView;

        @InjectView(R.id.pin_details_content_wv)
        WebView contentWebView;

        @InjectView(R.id.pin_details_more_tv)
        TextView showMoreTextView;

        private Toolbar toolbar;
        private Pin pin;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_pin_details, container, false);
            return rootView;
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            ButterKnife.inject(this, view);
            ViewCompat.setTransitionName(imageView, PinDetailsActivity.EXTRA_IMAGE);
            pin = (Pin) getArguments().getSerializable(PIN_EXTRA);
            Picasso.with(getActivity().getApplicationContext()).load(pin.photoUrl).into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    Drawable drawable = imageView.getDrawable();
                    if(drawable instanceof BitmapDrawable) {
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                        Palette.generateAsync(bitmapDrawable.getBitmap(), new PaletteListener());

                    }
                }

                @Override
                public void onError() {

                }
            });

            contentWebView.getSettings().setJavaScriptEnabled(true); // enable javascript


            contentWebView.setWebViewClient(new WebViewClient() {
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    Timber.e("Webview:" + description);
                }
            });
            contentWebView.setVisibility(View.GONE);
            contentWebView.loadUrl(pin.linkUrl);


        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            toolbar = ((PinDetailsActivity)getActivity()).getToolbar();


            toolbar.setTitle(Html.fromHtml(pin.caption).toString());

        }

        @OnClick(R.id.pin_details_more_tv)
        public void showMore() {
            contentWebView.setVisibility(View.VISIBLE);
        }

        public static Fragment newInstance(Pin pin) {
            PlaceholderFragment placeholderFragment = new PlaceholderFragment();
            Bundle arguments = new Bundle();
            arguments.putSerializable(PIN_EXTRA, pin);

            placeholderFragment.setArguments(arguments);
            return placeholderFragment;
        }

        private class PaletteListener implements Palette.PaletteAsyncListener {
            @Override
            public void onGenerated(Palette palette) {
                int lightVibrantColorFromImage = palette.getLightVibrantColor(R.color.accent);
                showMoreTextView.setBackgroundColor(lightVibrantColorFromImage);
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
                    toolbar.setBackgroundColor(lightVibrantColorFromImage);
            }
        }
    }
}
