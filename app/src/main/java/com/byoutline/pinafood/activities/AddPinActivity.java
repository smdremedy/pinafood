package com.byoutline.pinafood.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.byoutline.pinafood.PinAFoodApp;
import com.byoutline.pinafood.events.PinAddedEvent;
import com.byoutline.pinafood.R;
import com.byoutline.pinafood.fragments.AddPinFragment;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import hugo.weaving.DebugLog;
import timber.log.Timber;


public class AddPinActivity extends ActionBarActivity {

    @Inject
    Bus bus;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pin);
        PinAFoodApp.doDaggerInject(this);
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
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new AddPinFragment())
                    .commit();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    @DebugLog
    @Subscribe
    public void pinAdded(PinAddedEvent event) {
        Timber.d("pin added");
        setResult(RESULT_OK);
        finish();
    }
}
