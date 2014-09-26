package com.byoutline.pinafood.api;

import com.byoutline.pinafood.activities.LoginActivity;
import com.byoutline.pinafood.activities.PinnedFoodActivity;
import com.byoutline.pinafood.api.parse.ApiHeaders;
import com.byoutline.pinafood.api.parse.ParseService;
import com.byoutline.pinafood.api.tumblr.TumblrApi;
import com.byoutline.pinafood.fragments.AddPinFragment;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Module(
        complete = false,
        injects = {
                PinnedFoodActivity.class,
                LoginActivity.class,
                AddPinFragment.class
        }

)
public class ApiModule {

    @Qualifier
    @Documented
    @Retention(RUNTIME)
    public @interface NamedApi {
        String value() default "";
    }


    public static final String PARSE_API_URL = "https://api.parse.com/1";
    public static final String PARSE = "parse";

    @Provides
    @Singleton
    @NamedApi(PARSE)
    Endpoint provideParseApiEndpoint() {
        return Endpoints.newFixedEndpoint(PARSE_API_URL);
    }

    @Provides
    @Singleton
    @NamedApi(PARSE)
    RestAdapter provideParseApiRestAdapter(@NamedApi(PARSE) Endpoint endpoint, ApiHeaders headers) {
        return new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor(headers)
                .build();
    }

    @Provides
    @Singleton
    ParseService provideParseService(@NamedApi(PARSE) RestAdapter restAdapter) {
        return restAdapter.create(ParseService.class);
    }


    public static final String TUMBLR = "tumblr";
    public static final String TUMBLR_API_URL = "http://api.tumblr.com";

    @Provides
    @Singleton
    @NamedApi(TUMBLR)
    Endpoint provideTumblrApiEndpoint() {
        return Endpoints.newFixedEndpoint(TUMBLR_API_URL);
    }

    @Provides
    @Singleton
    @NamedApi(TUMBLR)
    RestAdapter provideTumblrApiRestAdapter(@NamedApi(TUMBLR) Endpoint endpoint) {
        return new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .build();
    }

    @Provides
    @Singleton
    TumblrApi provideTumblrApi(@NamedApi(TUMBLR) RestAdapter restAdapter) {
        return restAdapter.create(TumblrApi.class);
    }
}
