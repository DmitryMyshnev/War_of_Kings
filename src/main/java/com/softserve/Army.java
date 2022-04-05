package com.softserve;

import java.util.LinkedList;


public class Army {
    private LinkedList<Warrior> warriors;
    private boolean hasNext;

    public Army() {
        this.warriors = new LinkedList<>();
        hasNext = true;
    }

    public void addUtil(Class<? extends Warrior> type, int count) {
        for (int i = 0; i < count; i++) {
            warriors.add(Warrior.typeOf(type));
        }
    }

    public void getPunch() {
        if (!warriors.isEmpty())
            warriors.poll();
    }

    public Warrior nextWarrior() {
        if (!warriors.isEmpty())
            return warriors.peek();
        return null;
    }

    public boolean hasNext() {
        if (warriors.isEmpty()) {
            hasNext = false;
        }
        return hasNext;
    }
}
