package com.byoutline.pinafood.managers;

import com.byoutline.pinafood.api.parse.ParseService;
import com.byoutline.pinafood.api.parse.model.Pin;
import com.byoutline.pinafood.api.parse.model.PinsResult;
import com.byoutline.pinafood.events.PinAddedEvent;
import com.byoutline.pinafood.events.PinsFetchedEvent;
import com.squareup.otto.Bus;
import com.squareup.otto.Produce;

import java.util.List;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

public class PinsManager {

    private final ParseService parseService;
    private final Bus bus;

    private List<Pin> pins;

    public PinsManager(ParseService parseService, Bus bus) {
        this.parseService = parseService;
        this.bus = bus;
    }

    public void fetchPins() {


    }

    public List<Pin> getPins() {
        return pins;
    }

    public void postNewPin(Pin pin) {


    }
}
