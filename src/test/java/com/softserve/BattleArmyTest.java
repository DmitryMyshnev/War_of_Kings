package com.softserve;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class BattleArmyTest {
    Army firstArmy;
    Army secondArmy;

    @BeforeEach
    public void init() {
        firstArmy = new Army();
        secondArmy = new Army();
    }

    @ParameterizedTest
    @MethodSource("countArmyArguments")
    void battleOne(int countFirstArmy, int countSecondArmy, boolean result) {
        firstArmy.addUnits(Warrior.class, countFirstArmy);
        secondArmy.addUnits(Warrior.class, countSecondArmy);
        Assertions.assertEquals(Battle.fight(firstArmy, secondArmy), result);
    }

    private static Stream<Arguments> countArmyArguments() {
        return Stream.of(
                Arguments.of(1, 2, false),
                Arguments.of(2, 3, false),
                Arguments.of(5, 7, false),
                Arguments.of(20, 21, true),
                Arguments.of(10, 11, true),
                Arguments.of(11, 7, true)
        );
    }

    @ParameterizedTest
    @MethodSource("countArmyAndTypeOfWarriorArguments")
    void battleTwo(int[] countFirstArmy, int[] countSecondArmy, boolean result, Class<? extends Warrior>[]  warrior) {
        firstArmy.addUnits(warrior[0], countFirstArmy[0]);
        firstArmy.addUnits(warrior[1], countFirstArmy[1]);
        firstArmy.addUnits(warrior[2], countFirstArmy[2]);
        secondArmy.addUnits(warrior[1], countSecondArmy[0]);

        Assertions.assertEquals(Battle.fight(firstArmy, secondArmy), result);
    }

    private static Stream<Arguments> countArmyAndTypeOfWarriorArguments() {

        return Stream.of(
                Arguments.of(new int[]{5,4,5}, new int[]{4}, true, new Class[]{ Warrior.class,Defender.class,Defender.class,Warrior.class}),
                Arguments.of(new int[]{5,20,4}, new int[]{21}, true, new Class[]{ Defender.class,Warrior.class,Defender.class,Defender.class}),
                Arguments.of(new int[]{10,5,10}, new int[]{5}, true, new Class[]{ Warrior.class,Defender.class,Defender.class,Warrior.class}),
                Arguments.of(new int[]{2,1,1}, new int[]{5}, false, new Class[]{ Defender.class,Warrior.class,Defender.class,Warrior.class})
        );
    }
    @Test
    void battleThree(){
        firstArmy.addUnits(Defender.class,5);
        firstArmy.addUnits(Vampire.class,6);
        firstArmy.addUnits(Warrior.class,7);
        secondArmy.addUnits(Warrior.class,7);
        secondArmy.addUnits(Defender.class,7);
        secondArmy.addUnits(Vampire.class,7);
        Assertions.assertFalse(Battle.fight(firstArmy,secondArmy));
    }
    @Test
    void battleFor(){
        firstArmy.addUnits(Defender.class, 2);
        firstArmy.addUnits(Vampire.class, 3);
        firstArmy.addUnits(Warrior.class, 4);
        secondArmy.addUnits(Warrior.class, 4);
        secondArmy.addUnits(Defender.class, 4);
        secondArmy.addUnits(Vampire.class, 3);
        Assertions.assertFalse(Battle.fight(firstArmy,secondArmy));
    }
    @Test
    void battleFive(){
        firstArmy.addUnits(Defender.class, 11);
        firstArmy.addUnits(Vampire.class, 3);
        firstArmy.addUnits(Warrior.class, 4);
        secondArmy.addUnits(Warrior.class, 4);
        secondArmy.addUnits(Defender.class, 4);
        secondArmy.addUnits(Vampire.class, 13);
        Assertions.assertTrue(Battle.fight(firstArmy,secondArmy));
    }
    @Test
    void battleSix(){
        firstArmy.addUnits(Defender.class, 9);
        firstArmy.addUnits(Vampire.class, 3);
        firstArmy.addUnits(Warrior.class, 8);
        secondArmy.addUnits(Warrior.class, 4);
        secondArmy.addUnits(Defender.class, 4);
        secondArmy.addUnits(Vampire.class, 13);
        Assertions.assertTrue(Battle.fight(firstArmy,secondArmy));
    }
    @Test
    void battleSeven(){
        firstArmy.addUnits(Lancer.class, 5);
        firstArmy.addUnits(Vampire.class, 3);
        firstArmy.addUnits(Warrior.class, 4);
        firstArmy.addUnits(Defender.class, 2);
        secondArmy.addUnits(Warrior.class, 4);
        secondArmy.addUnits(Defender.class, 4);
        secondArmy.addUnits(Vampire.class, 6);
        secondArmy.addUnits(Lancer.class, 5);
        Assertions.assertFalse(Battle.fight(firstArmy,secondArmy));
    }
    @Test
    void battleEigth(){
        firstArmy.addUnits(Lancer.class, 7);
        firstArmy.addUnits(Vampire.class, 3);
        firstArmy.addUnits(Warrior.class, 4);
        firstArmy.addUnits(Defender.class, 2);
        secondArmy.addUnits(Warrior.class, 4);
        secondArmy.addUnits(Defender.class, 4);
        secondArmy.addUnits(Vampire.class, 6);
        secondArmy.addUnits(Lancer.class, 4);
        Assertions.assertTrue(Battle.fight(firstArmy,secondArmy));
    }
    @Test
    void battleTen(){
        firstArmy.addUnits(Lancer.class, 7);
        firstArmy.addUnits(Vampire.class, 3);
        firstArmy.addUnits(Healer.class, 1);
        firstArmy.addUnits(Warrior.class, 4);
        firstArmy.addUnits(Healer.class, 1);
        firstArmy.addUnits(Defender.class, 2);
        secondArmy.addUnits(Warrior.class, 4);
        secondArmy.addUnits(Defender.class, 4);
        secondArmy.addUnits(Healer.class, 1);
        secondArmy.addUnits(Vampire.class, 6);
        secondArmy.addUnits(Lancer.class, 4);
        Assertions.assertTrue(Battle.fight(firstArmy,secondArmy));
    }
    @Test
    void battleEleven(){
        firstArmy.addUnits(Lancer.class, 1);
        firstArmy.addUnits(Warrior.class, 3);
        firstArmy.addUnits(Healer.class, 1);
        firstArmy.addUnits(Warrior.class, 4);
        firstArmy.addUnits(Healer.class, 1);
        firstArmy.addUnits(Knight.class, 2);
        secondArmy.addUnits(Warrior.class, 4);
        secondArmy.addUnits(Defender.class, 4);
        secondArmy.addUnits(Healer.class, 1);
        secondArmy.addUnits(Vampire.class, 6);
        secondArmy.addUnits(Lancer.class, 4);
        Assertions.assertFalse(Battle.fight(firstArmy,secondArmy));
    }
    @Test
    void battleTwelwe(){
        firstArmy.addUnits(Healer.class,1);
        secondArmy.addUnits(Healer.class,1);
        Assertions.assertFalse(Battle.fight(firstArmy,secondArmy));
    }
    @Test
    void battleThirteen(){
        firstArmy.addUnits(Warrior.class,1);
        secondArmy.addUnits(Healer.class,1);
        Assertions.assertTrue(Battle.fight(firstArmy,secondArmy));
    }
}
