package com.softserve;

import java.util.LinkedList;


public class Army {
    private LinkedList<Warrior> warriors;

    public Army() {
        this.warriors = new LinkedList<>();
    }

    public void addUnits(Class<? extends Warrior> type, int count) {
        Warrior previous;
        Warrior next;
        for (int i = 0; i < count; i++) {
            if (warriors.isEmpty()) {
                warriors.add(Warrior.typeOf(type));
            } else {
                previous = warriors.getLast();
                next = Warrior.typeOf(type);
                next.setPreviousWarrior(previous);
                warriors.add(next);
            }
        }
    }

    public boolean isStrike() {
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
