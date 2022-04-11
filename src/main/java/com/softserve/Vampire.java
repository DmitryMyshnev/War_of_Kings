package com.softserve;

import lombok.Getter;

@Getter
public class Vampire extends Warrior {
    private int vampirism;
    private static final int PROCENT = 100;

    public Vampire() {
        super(40, new Attack(4));
        vampirism = 50;
    }

    protected void setVampirism(int vampirism) {
        this.vampirism = vampirism;
    }

    @Override
    public void makeDamage(Warrior opponent, Attack attack) {
        int haelthBeforeAttack = opponent.getHealth();
        opponent.receiveDamage(attack);
        int successAttackLevel = haelthBeforeAttack - opponent.getHealth();
        int health = getHealth() + (successAttackLevel * getVampirism()) / PROCENT;
        setHealth(health);
        treatmentByHealer();
    }
}
