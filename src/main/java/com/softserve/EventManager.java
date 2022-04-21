package com.softserve;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class EventManager {

    private final Map<Event, List<EventListener>> listeners = new EnumMap<>(Event.class);
    private static EventManager instance;

    private EventManager() {
        for (Event name : Event.values()) {
            listeners.put(name, new ArrayList<>());
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

    public static EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
            return instance;
        }
        return instance;
    }

    enum Event {
        TREATMENT,
        RECEIVE_DAMAGE
    }
}
