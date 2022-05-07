package com.softserve.utils;

import lombok.NonNull;

public interface Mediator<T> {
    void inform(T sender, String action);

    void register(@NonNull T object);
}
