package com.byoutline.pinafood.managers;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.byoutline.pinafood.api.parse.ParseService;
import com.byoutline.pinafood.api.parse.model.ErrorResponse;
import com.byoutline.pinafood.api.parse.model.User;
import com.byoutline.pinafood.api.parse.model.UserCreatedResponse;
import com.byoutline.pinafood.events.UserLoginFailedEvent;
import com.byoutline.pinafood.events.UserLoggedEvent;
import com.squareup.otto.Bus;

import javax.inject.Inject;
import javax.inject.Singleton;

import hugo.weaving.DebugLog;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class UserManager {

    public static final String SESSION_TOKEN = "sessionToken";
    public static final String USER_OBJECT_ID = "userObjectId";

    public String sessionToken;
    public String userObjectId;

    private final SharedPreferences sharedPreferences;


    public UserManager(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;

        sessionToken = sharedPreferences.getString(SESSION_TOKEN, null);
        userObjectId = sharedPreferences.getString(USER_OBJECT_ID, null);
    }

    public void saveUser(String sessionToken, String userObjectId) {
        this.sessionToken = sessionToken;
        this.userObjectId = userObjectId;

        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(SESSION_TOKEN, sessionToken);
        edit.putString(USER_OBJECT_ID, userObjectId);
        edit.apply();
    }

    public void logoutUser() {
        this.sessionToken = null;
        this.userObjectId = null;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(SESSION_TOKEN);
        editor.remove(USER_OBJECT_ID);
        editor.apply();
    }


    public boolean isNoUserLogged() {
        return TextUtils.isEmpty(sessionToken) || TextUtils.isEmpty(userObjectId);
    }

    public void signUpUser(String email, String password) {

        final User user = getUser(email, password);

    }

    private User getUser(String email, String password) {
        final User user = new User();
        user.username = email;
        user.password = password;
        return user;
    }

    public void signInUser(String email, String password) {

    }
}
