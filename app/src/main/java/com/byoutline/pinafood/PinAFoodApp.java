package com.byoutline.pinafood;

import android.app.Application;

import dagger.ObjectGraph;
import timber.log.Timber;

public class PinAFoodApp extends Application {

    private static ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(new PinAFoodModule(this));
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static void doDaggerInject(Object o) {
        objectGraph.inject(o);
    }
}
