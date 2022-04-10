package com.softserve;

import lombok.Getter;

@Getter
public class Vampire extends Warrior {
    private int vampirism;
    private static final int PROCENT = 100;

    public Vampire() {
        super(40, new Attack(4));
        vampirism = 50;
        setType(WarriorType.VAMPIRE);
    }

    protected void setVampirism(int vampirism) {
        this.vampirism = vampirism;
    }

    @Override
    public void makeDamage(Warrior warrior, Attack attack) {
        int haelthBeforeAttack = warrior.getHealth();
        super.makeDamage(warrior,attack);
        int successAttackLevel = haelthBeforeAttack - warrior.getHealth();
        int health = this.getHealth() + (successAttackLevel * this.getVampirism()) / PROCENT;
        this.setHealth(health);
    }
}
