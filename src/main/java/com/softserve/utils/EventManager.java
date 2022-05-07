package com.softserve.utils;

import lombok.NonNull;

import java.util.*;

public class EventManager {

    private final Map<Event, Set<EventListener>> listeners = new EnumMap<>(Event.class);

    public EventManager() {
        for (Event event : Event.values()) {
            listeners.put(event, new HashSet<>());
        }
    }

    public void subscribe(Event event, @NonNull EventListener listener) {
        listeners.get(event).add(listener);
    }

    public void unsubscribe(Event event, @NonNull EventListener listener) {
        listeners.get(event).remove(listener);
    }

    public void notifyEvent(Event event) {
        listeners.get(event).forEach(eventListener -> eventListener.update(event));
    }

   public enum Event {
        MAKE_DAMAGE,
        RECEIVE_DAMAGE,
        DIED
    }
}
