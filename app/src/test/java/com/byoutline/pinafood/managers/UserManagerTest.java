package com.byoutline.pinafood.managers;

import android.content.SharedPreferences;

import com.squareup.otto.Bus;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        verify(busMock, times(2)).register(any());


    }

    @Test
    public void shouldReadSessionTokenFromPrefs() throws Exception {

        //given
        String expected = "1234567";
        when(sharedPreferences.getString(anyString(), anyString())).thenReturn(expected);

        //when
        userManager = new UserManager(sharedPreferences, null, busMock);

        //then
        assertEquals(expected, userManager.sessionToken);

    }

    @Test
    public void shouldDoSomething() throws Exception {
        //given

        //when

        //then
    }
}