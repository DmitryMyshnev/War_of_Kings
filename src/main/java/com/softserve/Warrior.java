package com.softserve;

import lombok.Getter;

@Getter
public class Warrior {
    private int health;
    private final int attack;
    private boolean isAlive;

    public Warrior() {
       this(50,5);
    }

    protected Warrior(int health, int attack) {
        this.health = health;
        this.attack = attack;
        isAlive = true;
    }

    public boolean isAlive() {
        return isAlive;
    }

    protected void getPunch(int attack) {
        health -= attack;
        if (health <= 0) {
            isAlive = false;
        }
    }
}
