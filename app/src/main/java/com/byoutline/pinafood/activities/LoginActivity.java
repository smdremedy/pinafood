package com.byoutline.pinafood.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.byoutline.pinafood.PinAFoodApp;
import com.byoutline.pinafood.R;
import com.byoutline.pinafood.events.UserLoggedEvent;
import com.byoutline.pinafood.events.UserLoginFailedEvent;
import com.byoutline.pinafood.managers.UserManager;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class LoginActivity extends Activity {

    @InjectView(R.id.username_et)
    EditText emaiEditText;
    @InjectView(R.id.password_et)
    EditText passwordEditText;

    @Inject
    UserManager userManager;
    @Inject
    Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.inject(this);
        PinAFoodApp.doDaggerInject(this);
    }

    @OnClick({R.id.sign_up_btn, R.id.sign_in_btn})
    public void signUp(Button button) {
        String email = emaiEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if(TextUtils.isEmpty(email)) {
            emaiEditText.setError(getString(R.string.empty_email_warning));
        }
        if(button.getId() == R.id.sign_in_btn) {
            userManager.signInUser(email, password);
        } else {
            userManager.signUpUser(email, password);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bus.register(this);

    }

    @Subscribe
    public void onUserLogged(UserLoggedEvent event) {
        Intent intent = new Intent(getApplicationContext(), PinnedFoodActivity.class);
        startActivity(intent);
    }

    @Subscribe
    public void onUserLoginFailed(UserLoginFailedEvent event) {
        Toast.makeText(this, event.errorBody, Toast.LENGTH_SHORT).show();
    }
}
