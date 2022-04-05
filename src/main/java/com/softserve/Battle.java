package com.softserve;

public class Battle {

    private Battle() {
    }

    public static boolean fight(Warrior firstWarrior, Warrior secondWarrior) {
        if (firstWarrior == null) {
            return false;
        }
        if (secondWarrior == null) {
            return true;
        }
        while (firstWarrior.isAlive() && secondWarrior.isAlive()) {
            secondWarrior.getPunch(firstWarrior.getAttack());
            if (!secondWarrior.isAlive()) {
                break;
            } else {
                firstWarrior.getPunch(secondWarrior.getAttack());
            }
        }

        return firstWarrior.isAlive();
    }
}
