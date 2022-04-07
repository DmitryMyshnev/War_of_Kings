package com.softserve;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BattleTest {
    Warrior warrior;
    Knight knight;
    Defender defender;
    Rookie rookie;

    @BeforeEach
    public void init() {
        warrior = new Warrior();
        knight = new Knight();
        defender = new Defender();
        rookie = new Rookie();
    }

    @Test
    @DisplayName("Warrior vs Knight")
    void fightOne() {
        var carl = warrior;
        var jim = knight;
        Assertions.assertFalse(Battle.fight(carl, jim));
    }

    @Test
    @DisplayName("Knight vs Warrior")
    void fightTwo() {
        var ramon = warrior;
        var slevin = knight;
        Assertions.assertTrue(Battle.fight(slevin, ramon));
    }

    @Test
    @DisplayName("Warrior vs Warrior")
    void fightThree() {
        var bob = warrior;
        var mars = new Warrior();
        Battle.fight(bob, mars);
        Assertions.assertTrue(bob.isAlive());
    }

    @Test
    @DisplayName("Knight vs Warrior. Knight has been a win")
    void fightFour() {
        var zeus = knight;
        var godKiller = warrior;
        Battle.fight(zeus, godKiller);
        Assertions.assertTrue(zeus.isAlive());
    }

    @Test
    @DisplayName("Warrior vs Warrior.  Second warrior has been a lose")
    void fightFive() {
        var husband = warrior;
        var wife = warrior;
        Battle.fight(husband, wife);
        Assertions.assertFalse(wife.isAlive());
    }

    @Test
    @DisplayName("Warrior vs Knight.  Knight has been a win")
    void fightSix() {
        var dragon = warrior;
        var knight = this.knight;
        Battle.fight(dragon, knight);
        Assertions.assertTrue(knight.isAlive());
    }

    @Test
    @DisplayName("Warrior_1 vs Knight and Knight vs Warrior_2")
    void fightSeven() {
        var unit_1 = warrior;
        var unit_2 = knight;
        var unit_3 = new Warrior();
        Battle.fight(unit_1, unit_2);
        Assertions.assertFalse(Battle.fight(unit_2, unit_3));
    }
    @Test
    @DisplayName("Defender vs Rookie")
    void fightEight(){
        var unit_1 = defender;
        var unit_2 = rookie;
        Battle.fight(unit_1, unit_2);
        Assertions.assertEquals(60, unit_1.getHealth());
    }
    @Test
    @DisplayName("Defender vs Rookie and Defender vs Warrior")
    void fightThen() {
        var unit_1 = defender;
        var unit_2 = rookie;
        var unit_3 = new Warrior();
        Battle.fight(unit_1, unit_2);
        Assertions.assertTrue(Battle.fight(unit_1, unit_3));
    }

    static class Rookie extends Warrior {
      public Rookie() {
          super(50, 1);
      }
  }
}