package com.byoutline.pinafood.events;

import com.byoutline.pinafood.api.parse.model.Pin;

import java.util.List;

public class PinsFetchedEvent {

    public final List<Pin> pins;

    public PinsFetchedEvent(List<Pin> pins) {

        this.pins = pins;
    }
}
