package com.softserve.action;

import com.softserve.model.Warrior;


public class Battle {

    private Battle() {
    }

    public static boolean fight(Warrior firstWarrior, Warrior secondWarrior) {

        while (firstWarrior.isAlive() && secondWarrior.isAlive()) {
            secondWarrior.setHealth(secondWarrior.getHealth() - firstWarrior.getAttack());
            if (secondWarrior.getHealth() < 0) {
                secondWarrior.setAlive(false);
            } else {
                firstWarrior.setHealth(firstWarrior.getHealth() - secondWarrior.getAttack());
                if (firstWarrior.getHealth() < 0) {
                    firstWarrior.setAlive(false);
                }
            }
        }

        return firstWarrior.isAlive();
    }
}
