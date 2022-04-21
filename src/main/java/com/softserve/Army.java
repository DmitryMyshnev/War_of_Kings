package com.softserve;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;


public class Army implements EventListener {
    private LinkedList<Warrior> warriors;

    public Army() {
        this.warriors = new LinkedList<>();
    }

    @Override
    public void update(EventManager.Event event) {
        switch (event) {
            case TREATMENT -> healerOption();
        }
    }

    public void addUnits(Class<? extends Warrior> type, int count) {
        Warrior previous = null;
        Warrior next;
        if (!warriors.isEmpty()) {
            previous = warriors.getLast();
        }
        for (int i = 0; i < count; i++) {
            next = Warrior.typeOf(type);
            if (previous != null) {
                previous.setPreviousWarrior(next);
                next.setNextWarrior(previous);
            }
            next.em.subscribe(EventManager.Event.TREATMENT, this);
            warriors.add(next);
            previous = next;
        }
    }

    public void lineUp() {
        warriors.forEach(warrior -> {
            warrior.setPreviousWarrior(null);
            warrior.setNextWarrior(null);
        });
    }

    public void lineDown() {
        ListIterator<Warrior> it = warriors.listIterator();
        Warrior prevoius = null;
        Warrior next;
        while (it.hasNext()) {
            next = it.next();
            if (prevoius != null) {
                next.setNextWarrior(prevoius);
                prevoius.setPreviousWarrior(next);
            }
            prevoius = next;
        }
    }

    public Iterator<Warrior> allAliveWarrior() {
        return warriors.stream().filter(Warrior::isAlive).iterator();
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

    private void healerOption() {
        this.warriors.stream().filter(Healer.class::isInstance)
                .forEach(warrior -> {
                    Healer healer = (Healer) warrior;
                    Warrior next = healer.getNextWarrior();
                    if (next != null && next.isAlive()) {
                        healer.heal(healer.getNextWarrior());
                    }
                });
    }

}
