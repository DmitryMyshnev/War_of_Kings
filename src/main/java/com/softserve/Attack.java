package com.softserve;

import lombok.Getter;

@Getter
public class Attack {
    private int attack;

    public Attack(int attack) {
        this.attack = attack;
    }

    protected void setAttack(int attack) {
        this.attack = attack;
    }
}
