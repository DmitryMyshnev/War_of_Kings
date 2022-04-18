package com.softserve.weapon.weapons;

import com.softserve.weapon.Weapon;

import java.util.EnumMap;

public class Shield implements Weapon {

    private final EnumMap<Property, Integer> weaponProperties;

    public Shield() {
        weaponProperties = new EnumMap<>(Property.class);
        weaponProperties.put(Property.HEALTH, 20);
        weaponProperties.put(Property.ATTACK, -1);
        weaponProperties.put(Property.DEFENSE, 2);
    }

    @Override
    public EnumMap<Property, Integer> getWeaponProperties() {
        return weaponProperties;
    }
}
