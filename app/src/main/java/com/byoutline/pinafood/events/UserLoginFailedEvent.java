package com.byoutline.pinafood.events;

public class UserLoginFailedEvent {
    public final String errorBody;

    public UserLoginFailedEvent(String errorBody) {
        this.errorBody = errorBody;
    }
}
