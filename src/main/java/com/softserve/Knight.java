package com.softserve;

public class Knight extends Warrior {

    public Knight() {
        super(50, 7);
    }

    protected Knight(int health, int attack) {
        super(health, attack);
    }
}
