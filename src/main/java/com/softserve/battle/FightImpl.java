package com.softserve.battle;

import com.softserve.Warrior;

public class FightImpl implements Fight{
    @Override
    public boolean fight(Warrior firstWarrior, Warrior secondWarrior) {
        if (firstWarrior == null) {
            return false;
        }
        if (secondWarrior == null) {
            return true;
        }
        while (firstWarrior.isAlive() && secondWarrior.isAlive()) {
            firstWarrior.makeDamage(secondWarrior, firstWarrior.getAttack());
            if (!secondWarrior.isAlive()) {
                break;
            } else {
                secondWarrior.makeDamage(firstWarrior, secondWarrior.getAttack());
            }
        }

        return firstWarrior.isAlive();
    }
}
