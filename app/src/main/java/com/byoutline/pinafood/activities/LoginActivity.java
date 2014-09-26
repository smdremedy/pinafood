package com.byoutline.pinafood.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import com.byoutline.pinafood.R;


public class LoginActivity extends Activity {

    EditText emaiEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

}
