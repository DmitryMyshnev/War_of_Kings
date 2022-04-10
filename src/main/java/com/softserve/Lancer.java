package com.softserve;

import lombok.Getter;

@Getter
public class Lancer extends Warrior {
    private int extraDamage;
    private static final int PROCENT = 100;

    public Lancer() {
        super(50, new Attack(6));
        extraDamage = 50;
        setType(WarriorType.LANSER);
    }

    protected void setExtraDamage(int extraDamage) {
        this.extraDamage = extraDamage;
    }

    @Override
    public void makeDamage(Warrior warrior, Attack attack) {
        int haelthBeforeAttack = warrior.getHealth();
        super.makeDamage(warrior,attack);
        int successAttackLevel = haelthBeforeAttack - warrior.getHealth();
        Warrior previousWarrior = warrior.getPreviousWarrior();
        if (previousWarrior != null) {
            int damege = successAttackLevel * this.getExtraDamage() / PROCENT;
            previousWarrior.receiveDamage(new Attack(damege));
        }
    }
}
