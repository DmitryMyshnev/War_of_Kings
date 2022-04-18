package com.softserve.weapon.weapons;

import com.softserve.weapon.Weapon;

import java.util.EnumMap;

public class MagicWand implements Weapon {

    private final EnumMap<Property, Integer> weaponProperties;

    public MagicWand() {
        weaponProperties = new EnumMap<>(Property.class);
        weaponProperties.put(Property.HEALTH, 30);
        weaponProperties.put(Property.ATTACK, 3);
        weaponProperties.put(Property.HEAL_POWER, 3);
    }

    @Override
    public EnumMap<Property, Integer> getWeaponProperties() {
        return weaponProperties;
    }
}
