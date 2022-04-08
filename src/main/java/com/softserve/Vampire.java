package com.softserve;

import lombok.Getter;

@Getter
public class Vampire extends Warrior {
    private final int vampirism;

    public Vampire() {
        super(40, new Attack(4));
        vampirism = 50;
    }

    @Override
    public void makeDamage(Warrior warrior, Attack attack) {
        int healthBeforeAttack = warrior.getHealth();
        warrior.takeDamage(attack);
        int currentHealth = warrior.getHealth();
        int damage = ((healthBeforeAttack - currentHealth) * vampirism) / 100;
        if (this.getHealth() + damage > this.getMaxHealth()) {
            damage = this.getMaxHealth() - this.getHealth();
        } else {
            damage *= -1;
        }
        super.makeDamage(this, new Attack(damage));
    }
}
