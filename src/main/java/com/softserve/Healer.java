package com.softserve;

import lombok.Getter;

@Getter
public class Healer extends Warrior {
    private int treatment;
    private WarriorType type;

    public Healer() {
        super(50, new Attack(0));
        treatment = 2;
    }

    public void heal(Warrior warrior) {
        warrior.setHealth(warrior.getHealth() + this.getTreatment());
    }

    @Override
    public void makeDamage(Warrior warrior, Attack attack) {
        super.makeDamage(warrior, attack);
        receiveDamage(new Attack(this.getMaxHealth()));
    }
}
