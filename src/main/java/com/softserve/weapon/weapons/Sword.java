package com.softserve.weapon.weapons;

import com.softserve.weapon.Weapon;

import java.util.EnumMap;

public class Sword implements Weapon {

    private final EnumMap<Property, Integer> weaponProperties;

    public Sword() {
        weaponProperties = new EnumMap<>(Property.class);
        weaponProperties.put(Property.HEALTH, 5);
        weaponProperties.put(Property.ATTACK, 2);
    }

    @Override
    public EnumMap<Property, Integer> getWeaponProperties() {
        return weaponProperties;
    }
}
