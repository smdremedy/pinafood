package com.byoutline.pinafood.activities;

import android.app.Activity;
import android.os.Bundle;
import com.byoutline.pinafood.R;
import com.byoutline.pinafood.fragments.AddPinFragment;


public class AddPinActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pin);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new AddPinFragment())
                    .commit();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
