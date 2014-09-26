package com.byoutline.pinafood;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.byoutline.pinafood.activities.AddPinActivity;
import com.byoutline.pinafood.activities.LoginActivity;
import com.byoutline.pinafood.activities.PinnedFoodActivity;
import com.byoutline.pinafood.api.ApiModule;
import com.byoutline.pinafood.api.parse.ParseService;
import com.byoutline.pinafood.fragments.AddPinFragment;
import com.byoutline.pinafood.fragments.PinnedFoodFragment;
import com.byoutline.pinafood.managers.PinsManager;
import com.byoutline.pinafood.managers.UserManager;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = ApiModule.class,
        injects = {
                PinnedFoodActivity.class,
                PinnedFoodFragment.class,
                LoginActivity.class,
                AddPinActivity.class,
                AddPinFragment.class
        }
)

public class PinAFoodModule {

    private final PinAFoodApp app;

    public PinAFoodModule(PinAFoodApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public PinAFoodApp provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(PinAFoodApp app) {
        return PreferenceManager.getDefaultSharedPreferences(app);
    }

    @Provides
    @Singleton
    public Bus provideBus() {
        return new Bus();
    }
}
