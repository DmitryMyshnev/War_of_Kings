package com.softserve;

import java.util.LinkedList;


public class Army {
    private LinkedList<Warrior> warriors;

    public Army() {
        this.warriors = new LinkedList<>();
    }

    public void addUnits(Class<? extends Warrior> type, int count) {
        Warrior previous = null;
        Warrior next;
        for (int i = 0; i < count; i++) {
            next = Warrior.typeOf(type);
            if (previous != null) {
                previous.setPreviousWarrior(next);
            }
            warriors.add(next);
            previous = next;
        }
    }

    public boolean strike() {
        if (warriors.isEmpty()) {
            return true;
        }
        warriors.poll();
        return false;
    }

    public Warrior getAliveWarrior() {
        if (!warriors.isEmpty())
            return warriors.peek();
        return null;
    }
}
