package com.softserve;

public interface Fighter {

    void receiveDamage(Attack attack);

    void makeDamage(Warrior opponent, Attack attack);

    boolean isAlive();
}
