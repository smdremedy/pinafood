package com.byoutline.pinafood.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.byoutline.pinafood.PinAFoodApp;
import com.byoutline.pinafood.R;
import com.byoutline.pinafood.managers.UserManager;
import com.byoutline.pinafood.fragments.PinnedFoodFragment;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import timber.log.Timber;

public class PinnedFoodActivity extends Activity {

    public static final int REQUEST_CODE = 123;
    @Inject
    UserManager userManager;
    @Inject
    Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PinAFoodApp.doDaggerInject(this);

        if(userManager.isNoUserLogged()) {

            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        setContentView(R.layout.activity_pinned_food);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PinnedFoodFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pinned_food, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            Intent intent = new Intent(getApplicationContext(), AddPinActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
            return true;
        } else if(id == R.id.action_logout) {
            userManager.logoutUser();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
