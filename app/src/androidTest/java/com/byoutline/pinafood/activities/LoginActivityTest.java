package com.byoutline.pinafood.activities;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.EditText;

import com.byoutline.pinafood.PinAFoodApp;
import com.byoutline.pinafood.PinAFoodModule;
import com.byoutline.pinafood.R;
import com.byoutline.pinafood.api.ApiModule;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.spoon.Spoon;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.matchers.TypeSafeMatcher;
import org.junit.runner.RunWith;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {



    @Rule
    public final ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @BeforeClass
    public static void setUpClass() throws Exception {

        PinAFoodApp.useMocks = true;

    }

    @Test
    public void shouldShowErrorOnEmptyUsername() throws Exception {
        //given

        //when
        onView(withId(R.id.sign_in_btn)).perform(click());
        //then
        onView(withId(R.id.username_et)).check(matches(withError(R.string.empty_field_warning)));

    }

    @Test
    public void shouldShowErrorOnEmptyPassword() throws Exception {
        //given

        //when
        onView(withId(R.id.sign_in_btn)).perform(click());
        //then
        onView(withId(R.id.password_et)).check(matches(withError(R.string.empty_field_warning)));

    }

    @Test
    public void shouldLoginWithApi() throws Exception {
        //given
        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody("{\"objectId\": \"g7y9tkhB7O\"," +
                "\"sessionToken\": \"pnktnjyb996sj4p156gjtp4im\"}"));
        server.start(9999);
        Intents.init();

        //when
        onView(withId(R.id.username_et)).perform(typeText("test@test.com\n"), closeSoftKeyboard());
        onView(withId(R.id.password_et)).perform(typeText("password\n"), closeSoftKeyboard());
        onView(withId(R.id.sign_in_btn)).perform(click());
        Spoon.screenshot(activityTestRule.getActivity(), "after_login");

        //then
        intended(hasComponent(hasShortClassName(".activities.PinnedFoodActivity")));

    }

    private static Matcher<View> withError(final int expectedResourceId) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof EditText)) {
                    return false;
                }
                EditText editText = (EditText) view;
                CharSequence error = editText.getError();

                return error != null && error.toString().
                        equals(view.getResources().getString(expectedResourceId));
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }
}