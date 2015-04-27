package com.byoutline.pinafood.activities;

import android.widget.Button;

import com.byoutline.pinafood.R;
import com.byoutline.pinafood.events.UserLoginFailedEvent;
import com.byoutline.pinafood.managers.CustomRobolectricRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowToast;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(CustomRobolectricRunner.class)
public class LoginActivityTest {

    private LoginActivity loginActivity;

    @Before
    public void setUp() throws Exception {
        loginActivity = Robolectric.setupActivity(LoginActivity.class);
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        //given
        //when
        //then
        assertThat(loginActivity).isNotNull();
    }

    @Test
    public void shouldHaveBus() throws Exception {
        //given
        //when
        //then
        assertThat(loginActivity.bus).isNotNull();
    }

    @Test
    public void shouldShowToastOnLoginFailed() throws Exception {
        //given

        //when
        String errorBody = "error body";
        loginActivity.bus.post(new UserLoginFailedEvent(errorBody));

        //when
        assertThat(ShadowToast.getTextOfLatestToast()).isEqualTo(errorBody);
    }

    @Test
    public void shouldHaveUserValidation() throws Exception {
        //given

        Button button = mock(Button.class);
        when(button.getId()).thenReturn(R.id.sign_in_btn);
        //when
        loginActivity.signUp(button);

        //when
        assertThat(loginActivity.usernameEditText.getError().toString())
                .isEqualTo(Robolectric.application.getString(R.string.empty_field_warning)  );

    }


}