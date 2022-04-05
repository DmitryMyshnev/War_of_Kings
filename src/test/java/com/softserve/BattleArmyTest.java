package com.softserve;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BattleArmyTest {
    Army firstArmy;
    Army secondArmy;

    @BeforeEach
    public void init() {
        firstArmy = new Army();
        secondArmy = new Army();
    }

    @Test
    void fightOne() {
        firstArmy.addUtil(Warrior.class, 1);
        secondArmy.addUtil(Warrior.class, 2);
        Assertions.assertFalse(Battle.fight(firstArmy, secondArmy));
    }

    @Test
    void fightTwo() {
        firstArmy.addUtil(Warrior.class, 2);
        secondArmy.addUtil(Warrior.class, 3);
        Assertions.assertFalse(Battle.fight(firstArmy, secondArmy));
    }

    @Test
    void fightThree() {
        firstArmy.addUtil(Warrior.class, 5);
        secondArmy.addUtil(Warrior.class, 7);
        Assertions.assertFalse(Battle.fight(firstArmy, secondArmy));
    }

    @Test
    void fightFour() {
        firstArmy.addUtil(Warrior.class, 20);
        secondArmy.addUtil(Warrior.class, 21);
        Assertions.assertTrue(Battle.fight(firstArmy, secondArmy));
    }

    @Test
    void fightFive() {
        firstArmy.addUtil(Warrior.class, 10);
        secondArmy.addUtil(Warrior.class, 11);
        Assertions.assertTrue(Battle.fight(firstArmy, secondArmy));
    }

    @Test
    void fightSix() {
        firstArmy.addUtil(Warrior.class, 11);
        secondArmy.addUtil(Warrior.class, 7);
        Assertions.assertTrue(Battle.fight(firstArmy, secondArmy));
    }
}
