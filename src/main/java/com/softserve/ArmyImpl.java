package com.softserve;

import com.softserve.utils.ArmyMediator;
import com.softserve.utils.EventListener;
import com.softserve.utils.EventManager;
import com.softserve.utils.Mediator;
import com.softserve.warrior.Healer;
import com.softserve.warrior.Warlord;
import com.softserve.warrior.Warrior;
import com.softserve.warrior.Witcher;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class ArmyImpl implements Army, EventListener {
    private final LinkedList<Warrior> warriors;
    private Warlord warlord = null;
    private final String name;
    private final Mediator<Army> armyMediator;

    public ArmyImpl(String name) {
        this.warriors = new LinkedList<>();
        this.name = name;
        armyMediator = ArmyMediator.getInstance();
        armyMediator.register(this);
    }

    @Override
    public void update(EventManager.Event event) {
        switch (event) {
            case MAKE_DAMAGE -> healerOption();
            case RECEIVE_DAMAGE -> daminationOption();
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
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
            next.eventManager.subscribe(EventManager.Event.MAKE_DAMAGE, this);
            next.eventManager.subscribe(EventManager.Event.RECEIVE_DAMAGE, this);

            if (type != Warlord.class || warlord == null) {
                if (type == Warlord.class) {
                    warlord = (Warlord) next;
                }
                warriors.add(next);
                previous = next;
            }
        }
        if (warlord != null) {
            warriors.forEach(w -> {
                w.eventManager.subscribe(EventManager.Event.DIED, warlord);
                w.setNextWarrior(null);
                w.setPreviousWarrior(null);
            });
            warlord.setWarriors(warriors);
            warlord.moveUnit();
        }
    }

    @Override
    public void lineUp() {
        warriors.forEach(warrior -> {
            warrior.setPreviousWarrior(null);
            warrior.setNextWarrior(null);
        });
    }

    @Override
    public Iterator<Warrior> allAliveWarrior() {
        return warriors.stream().filter(Warrior::isAlive)
                .collect(Collectors.toCollection(LinkedList::new))
                .iterator();
    }

    @Override
    public boolean isDied() {
        return warriors.isEmpty();
    }

    @Override
    public Warrior nextAliveWarrior() {
        if (warriors.isEmpty()) {
            return null;
        }
        while (warriors.peek() != null && !warriors.peek().isAlive()) {
            warriors.poll();
        }
        return warriors.peek();
    }

    public Warrior getWarrior(int position) {
        if (position >= 0 && position < warriors.size()) {
            return warriors.get(position);
        }
        return null;
    }

    private void healerOption() {
        this.warriors.stream()
                .filter(Healer.class::isInstance)
                .map(Healer.class::cast)
                .forEach(warrior -> {
                    Warrior ahead = warrior.getNextWarrior();
                    if (ahead != null && ahead.isAlive()) {
                        warrior.heal(ahead);
                    }
                });
    }

    private void daminationOption() {
        this.warriors.stream()
                .filter(Witcher.class::isInstance)
                .findFirst()
                .ifPresent(w -> armyMediator.inform(this, "Damnation"));

    }

}
