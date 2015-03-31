package com.byoutline.pinafood.activities;

import com.byoutline.pinafood.managers.CustomRobolectricRunner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(CustomRobolectricRunner.class)
public class LoginActivityTest {

    @Test
    public void testName() throws Exception {
        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);
        assertThat(loginActivity).isNotNull();
        assertThat(loginActivity.userManager.isNoUserLogged()).isFalse();

    }
}