package com.softserve;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
        Warrior warriorOne = firstArmy.getAliveWarrior();
        Warrior warriorTwo = secondArmy.getAliveWarrior();

        while (warriorOne != null && warriorTwo != null) {
            if (fight(warriorOne, warriorTwo)) {
                if (secondArmy.strike()) {
                    return true;
                } else {
                    warriorTwo = secondArmy.getAliveWarrior();
                }
            } else {
                if (!firstArmy.strike()) {
                    warriorOne = firstArmy.getAliveWarrior();
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
