package com.softserve.weapon;

import java.util.EnumMap;

public interface Weapon {

   EnumMap<Property,Integer> getWeaponProperties();

    enum Property {
        HEALTH,
        ATTACK,
        DEFENSE,
        VAMPIRISM,
        HEAL_POWER,
        PIERCING_DAMAGE
    }
}
