package com.softserve;

import com.softserve.warrior.Warrior;

import java.util.Iterator;

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
            firstWarrior.makeDamage(secondWarrior, firstWarrior.getAttack());
            if (!secondWarrior.isAlive()) {
                break;
            } else {
                secondWarrior.makeDamage(firstWarrior, secondWarrior.getAttack());
            }
        }

        return firstWarrior.isAlive();
    }


    public static boolean fight(Army firstArmy, Army secondArmy) {
        Warrior warriorOne = firstArmy.nextAliveWarrior();
        Warrior warriorTwo = secondArmy.nextAliveWarrior();

        while (warriorOne != null && warriorTwo != null) {
            if (fight(warriorOne, warriorTwo)) {
                if (secondArmy.isDied()) {
                    return true;
                } else {
                    warriorTwo = secondArmy.nextAliveWarrior();
                }
            } else {
                if (!firstArmy.isDied()) {
                    warriorOne = firstArmy.nextAliveWarrior();
                }
            }
        }
        return warriorOne != null;
    }

    public static boolean straightFight(Army firstArmy, Army secondArmy) {
        firstArmy.lineUp();
        secondArmy.lineUp();
        Iterator<Warrior> survivorsOfFirstArmy = firstArmy.allAliveWarrior();
        Iterator<Warrior> survivorsOfSecondArmy = secondArmy.allAliveWarrior();
        while (survivorsOfFirstArmy.hasNext() && survivorsOfSecondArmy.hasNext()) {
            while (survivorsOfFirstArmy.hasNext() && survivorsOfSecondArmy.hasNext()) {
                fight(survivorsOfFirstArmy.next(), survivorsOfSecondArmy.next());
            }
            survivorsOfFirstArmy = firstArmy.allAliveWarrior();
            survivorsOfSecondArmy = secondArmy.allAliveWarrior();
        }
        return survivorsOfFirstArmy.hasNext();
    }
}
