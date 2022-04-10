package com.softserve.weapons;

import java.util.EnumMap;

public interface Weapons {

   EnumMap<Property,Integer> getWeaponProperties();

    enum Property {
        HEALTH,
        ATTACK,
        DEFENSE,
        VAMPIRISM,
        HEAL_POWER
    }
}
