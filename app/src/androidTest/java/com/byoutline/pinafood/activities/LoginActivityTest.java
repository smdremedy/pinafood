package com.byoutline.pinafood.activities;

import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.EditText;

import com.byoutline.pinafood.ActivityRule;
import com.byoutline.pinafood.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.matchers.TypeSafeMatcher;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public final ActivityRule<LoginActivity> main = new ActivityRule<>(LoginActivity.class);

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