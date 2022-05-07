package com.softserve;

import com.softserve.warrior.Defender;
import com.softserve.warrior.Lancer;
import com.softserve.warrior.Vampire;
import com.softserve.warrior.Warrior;
import com.softserve.weapon.Weapon;
import com.softserve.weapon.weapons.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WeaponTest {
    ArmyImpl firstArmy;
    ArmyImpl secondArmy;

    @BeforeEach
    public void init() {
        firstArmy = new ArmyImpl("Lion");
        secondArmy = new ArmyImpl("Tiger");
    }

    @Test
    void notAliveAfterAddManyWeaponsTest() {
        Warrior warrior = new Warrior();
        warrior.equipWeapon(new Katana());
        warrior.equipWeapon(new Katana());
        warrior.equipWeapon(new Katana());
        Assertions.assertFalse(warrior.isAlive());
    }

    @Test
    void Weapon1Test() {
        Warrior warrior = new Warrior();
        Warrior vampire = new Vampire();
        Weapon castomWeapon = new CastomWeapon.Builder()
                .healse(-10)
                .atack(5)
                .defanse(0)
                .vampirism(40)
                .healPower(0)
                .build();
        Weapon sword = new Sword();
        warrior.equipWeapon(castomWeapon);
        vampire.equipWeapon(sword);
        Assertions.assertTrue(Battle.fight(warrior,vampire));
    }

    @Test
    void Weapon2Test() {
        Warrior lancer = new Lancer();
        Warrior defender = new Defender();
        Weapon shield = new Shield();
        Weapon greatAxe = new GreatAxe();
        lancer.equipWeapon(greatAxe);
        defender.equipWeapon(shield);
        Assertions.assertFalse(Battle.fight(defender,lancer));
    }
}
