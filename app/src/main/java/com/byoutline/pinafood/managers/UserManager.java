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

@Singleton
public class UserManager {

    public static final String SESSION_TOKEN = "sessionToken";
    public static final String USER_OBJECT_ID = "userObjectId";
    public static final String NAME = "name";

    public String sessionToken;
    public String userObjectId;
    public String name;

    private final SharedPreferences sharedPreferences;
    private final ParseService parseService;
    private final Bus bus;

    private Callback<UserCreatedResponse> callback = new Callback<UserCreatedResponse>() {

        @DebugLog
        @Override
        public void success(UserCreatedResponse userCreatedResponse, Response response) {
            saveUser(userCreatedResponse.sessionToken,
                    userCreatedResponse.objectId);
            bus.post(new UserLoggedEvent());
        }

        @Override
        public void failure(RetrofitError error) {
            ErrorResponse errorResponse = (ErrorResponse) error.getBodyAs(ErrorResponse.class);
            bus.post(new UserLoginFailedEvent(errorResponse.error));
            Log.d("TAG", error.toString() + " body:" + errorResponse.error);


        }
    };


    @Inject
    public UserManager(SharedPreferences sharedPreferences, ParseService parseService, Bus bus) {
        this.sharedPreferences = sharedPreferences;
        this.parseService = parseService;
        this.bus = bus;
        this.bus.register(this);

        sessionToken = sharedPreferences.getString(SESSION_TOKEN, null);
        userObjectId = sharedPreferences.getString(USER_OBJECT_ID, null);
        name = sharedPreferences.getString(NAME, null);
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
        parseService.postUser(user, callback);
    }

    private User getUser(String email, String password) {
        final User user = new User();
        user.username = email;
        user.password = password;
        return user;
    }

    public void signInUser(String email, String password) {
        parseService.getLogin(email, password, callback);
    }
}
