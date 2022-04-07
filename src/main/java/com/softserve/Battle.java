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
            secondWarrior.hit(firstWarrior.getAttack());
            if (!secondWarrior.isAlive()) {
                break;
            } else {
                firstWarrior.hit(secondWarrior.getAttack());
            }
        }

        return firstWarrior.isAlive();
    }


    public static boolean fight(Army firstArmy, Army secondArmy) {
        Warrior warriorOne = firstArmy.getAliveWarrior();
        Warrior warriorTwo = secondArmy.getAliveWarrior();

        while (warriorOne != null && warriorTwo != null) {
            if (fight(warriorOne, warriorTwo)) {
                if (secondArmy.isStrike()) {
                    return true;
                } else {
                    warriorTwo = secondArmy.getAliveWarrior();
                }
            } else {
                if (!firstArmy.isStrike())
                    warriorOne = firstArmy.getAliveWarrior();
            }
        }
        return warriorOne != null;
    }

}
