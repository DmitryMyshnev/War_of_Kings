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


    public static boolean fight(Army firstArmy, Army secondArmy) {
        if (!firstArmy.hasNext()) {
            return false;
        }
        if (!secondArmy.hasNext()) {
            return true;
        }
        Warrior warriorOne = firstArmy.nextWarrior();
        Warrior warriorTwo = secondArmy.nextWarrior();
        do {
            if (fight(warriorOne, warriorTwo)) {
                secondArmy.getPunch();
                if (secondArmy.hasNext())
                    warriorTwo = secondArmy.nextWarrior();

            } else {
                firstArmy.getPunch();
                if (firstArmy.hasNext())
                    warriorOne = firstArmy.nextWarrior();
            }
        }
        while (firstArmy.hasNext() && secondArmy.hasNext());

        return firstArmy.hasNext();
    }

}
