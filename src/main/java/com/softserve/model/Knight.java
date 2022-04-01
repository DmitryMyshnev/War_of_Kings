package com.softserve.model;

import lombok.Data;

@Data
public class Knight extends Warrior {

    public Knight() {
        super.setHealth(50);
        super.setAttack(7);
        super.setAlive(true);
    }
}
