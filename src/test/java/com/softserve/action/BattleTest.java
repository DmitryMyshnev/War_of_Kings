package com.softserve.action;

import com.softserve.model.Knight;
import com.softserve.model.Warrior;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleTest {

    @Test
    @DisplayName("Warrior vs Knight")
    void fightOne() {
        var carl = new Warrior();
        var jim = new Knight();
        Battle.fight(carl, jim);
        Assertions.assertFalse(Battle.fight(carl, jim));
    }

    @Test
    @DisplayName("Knight vs Warrior")
    void fightTwo() {
        var ramon = new Warrior();
        var slevin = new Knight();
        Assertions.assertTrue(Battle.fight(slevin, ramon));
    }

    @Test
    @DisplayName("Warrior vs Warrior")
    void fightThree() {
        var bob = new Warrior();
        var mars = new Warrior();
        Battle.fight(bob, mars);
        Assertions.assertTrue(bob.isAlive());
    }

    @Test
    @DisplayName("Knight vs Warrior. Knight has been a win")
    void fightFour() {
        var zeus = new Knight();
        var godKiller = new Warrior();
        Battle.fight(zeus, godKiller);
        Assertions.assertTrue(zeus.isAlive());
    }

    @Test
    @DisplayName("Warrior vs Warrior.  Second warrior has been a lose")
    void fightFive() {
        var husband = new Warrior();
        var wife = new Warrior();
        Battle.fight(husband, wife);
        Assertions.assertFalse(wife.isAlive());
    }

    @Test
    @DisplayName("Warrior vs Knight.  Knight has been a win")
    void fightSix() {
        var dragon = new Warrior();
        var knight = new Knight();
        Battle.fight(dragon, knight);
        Assertions.assertTrue(knight.isAlive());
    }
    @Test
    @DisplayName("Warrior_1 vs Knight and Knight vs Warrior_2")
    void fightSeven() {
        var unit_1  = new Warrior();
        var unit_2  = new Knight();
        var unit_3  = new Warrior();
        Battle.fight(unit_1 , unit_2 );
        Assertions.assertFalse(Battle.fight(unit_2,unit_3));
    }
}