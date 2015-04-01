package com.byoutline.pinafood.managers;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.squareup.otto.Bus;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(CustomRobolectricRunner.class)
public class UserManagerTest {

    private SharedPreferences sharedPreferences;
    private Bus busMock;
    private UserManager userManager;

    @Before
    public void setUp() throws Exception {


        sharedPreferences = mock(SharedPreferences.class);
        busMock = mock(Bus.class);

    }

    @Test
    public void shouldBeNotNull() throws Exception {

        userManager = new UserManager(sharedPreferences, null, busMock);
        assertNotNull(userManager);

    }

    @Test
    public void shouldHaveBusRegistered() throws Exception {


        userManager = new UserManager(sharedPreferences, null, busMock);
        verify(busMock).register(any());


    }

    @Test
    public void shouldReadSessionTokenFromPrefs() throws Exception {

        //given
        String expected = "1234567";
        when(sharedPreferences.getString(anyString(), anyString())).thenReturn(expected);
//
        //when
        userManager = new UserManager(sharedPreferences, null, busMock);
//
        //then
        assertEquals(expected, userManager.sessionToken);

    }

    @Test
    public void shouldReadNameFromPrefs() throws Exception {
        //given
        String qweqwe = "qweqwe";
        when(sharedPreferences.getString(UserManager.NAME, null)).thenReturn(qweqwe);

        //when
        userManager = new UserManager(sharedPreferences, null, busMock);

        //when
        assertEquals(qweqwe, userManager.name);
    }

    @Test
    public void shouldDoSomething() throws Exception {
        //given
        userManager = new UserManager(sharedPreferences, null, busMock);

        SharedPreferences.Editor editorMock = mock(SharedPreferences.Editor.class);
        when(sharedPreferences.edit()).thenReturn(editorMock);

        //when
        userManager.logoutUser();

        //then
        verify(editorMock).remove(UserManager.SESSION_TOKEN);
    }

    @Test
    public void shouldWork() throws Exception {
        //given

        //when

        //when
        assertTrue(TextUtils.isEmpty(""));
    }
}