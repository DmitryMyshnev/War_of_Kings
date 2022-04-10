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
    public void makeDamage(Warrior opponent, Attack attack) {
        int haelthBeforeAttack = opponent.getHealth();
        super.makeDamage(opponent,attack);
        int successAttackLevel = haelthBeforeAttack - opponent.getHealth();
        Warrior previousWarrior = opponent.getPreviousWarrior();
        if (previousWarrior != null) {
            int damege = successAttackLevel * this.getExtraDamage() / PROCENT;
            previousWarrior.receiveDamage(new Attack(damege));
        }
    }
}
