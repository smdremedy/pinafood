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

    @Produce
    PinsFetchedEvent producesPinsFetchedEvent() {
        return new PinsFetchedEvent(pins);
    }

    @Inject
    public PinsManager(ParseService parseService, Bus bus) {
        this.parseService = parseService;
        this.bus = bus;
    }

    public void fetchPins() {
        parseService.getPins(new Callback<PinsResult>() {
            @Override
            public void success(PinsResult pinsResult, retrofit.client.Response response) {
                Timber.d("Results:" + pinsResult.results);
                pins = pinsResult.results;
                bus.post(new PinsFetchedEvent(pins));
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

    public List<Pin> getPins() {
        return pins;
    }

    public void postNewPin(Pin pin) {
        parseService.postNewPin(pin, new Callback<Pin>() {
            @Override
            public void success(Pin pin, Response response) {
                bus.post(new PinAddedEvent());

                fetchPins();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }
}
