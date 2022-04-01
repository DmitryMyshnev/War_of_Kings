package com.softserve.model;

import lombok.Data;

@Data
public class Warrior {
    private int health;
    private int attack;
    private boolean isAlive;

    public Warrior() {
        health = 50;
        attack = 5;
        isAlive = true;
    }
}
