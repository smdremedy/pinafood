package com.byoutline.pinafood;

import android.app.Application;

import dagger.ObjectGraph;
import timber.log.Timber;

public class PinAFoodApp extends Application {

    private static ObjectGraph objectGraph;

    public static boolean useMocks;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        createGraph(new PinAFoodModule(this));
    }

    public static void createGraph(Object...modules) {
        objectGraph = ObjectGraph.create(modules);
    }

    public static void doDaggerInject(Object o) {
        objectGraph.inject(o);
    }
}
