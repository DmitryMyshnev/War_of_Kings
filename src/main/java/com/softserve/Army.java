package com.softserve;

import com.softserve.warrior.Warrior;

import java.util.Iterator;
import java.util.List;

public interface Army {
    String getName();

    Iterator<Warrior> allAliveWarrior();

    boolean isDied();

    Warrior nextAliveWarrior();

    void lineUp();

    void addUnits(Class<? extends Warrior> type, int count);
}
