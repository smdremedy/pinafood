package com.byoutline.pinafood.api.parse;

import android.text.TextUtils;

import com.byoutline.pinafood.managers.UserManager;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import retrofit.RequestInterceptor;

@Singleton
public final class ApiHeaders implements RequestInterceptor {

    private final Provider<UserManager> userManagerProvider;

    @Inject
    public ApiHeaders(Provider<UserManager> userManagerProvider) {
        this.userManagerProvider = userManagerProvider;
    }

    @Override
    public void intercept(RequestFacade request) {

        String sessionToken = userManagerProvider.get().sessionToken;
        if(!TextUtils.isEmpty(sessionToken)) {
            request.addHeader("X-Parse-Session-Token", sessionToken);
        }
        request.addHeader("X-Parse-Application-Id", "C2TBuKKsSnynfindEQiCAYbzGG9LmcApXjYPbAPC");
        request.addHeader("X-Parse-REST-API-Key", "AGQyjo05XwjHL9gWLRvjswvy2sZdZpMs1v6vgzCM");
    }
}
