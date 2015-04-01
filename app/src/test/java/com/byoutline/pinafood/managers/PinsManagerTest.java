package com.byoutline.pinafood.managers;

import com.byoutline.pinafood.api.parse.ParseService;

import org.junit.Before;
import org.junit.Test;

import retrofit.Callback;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PinsManagerTest {

    private PinsManager pinsManager;
    private ParseService parseServiceMock;

    @Before
    public void setUp() throws Exception {
        parseServiceMock = mock(ParseService.class);
        pinsManager = new PinsManager(parseServiceMock, null);
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        //given
        //when
        //then
        assertNotNull(pinsManager);
    }

    @Test
    public void shouldCallApiInFetchPins() throws Exception {
        //given

        //when
        pinsManager.fetchPins();

        //when
        verify(parseServiceMock).getPins(any(Callback.class));

    }
}