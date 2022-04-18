package com.softserve.weapon.weapons;

import com.softserve.weapon.Weapon;

import java.util.EnumMap;

public class GreatAxe implements Weapon {

    private final EnumMap<Property, Integer> weaponProperties;

    public GreatAxe() {
        weaponProperties = new EnumMap<>(Property.class);
        weaponProperties.put(Property.HEALTH, -15);
        weaponProperties.put(Property.ATTACK, 5);
        weaponProperties.put(Property.DEFENSE, -2);
        weaponProperties.put(Property.VAMPIRISM, 10);
    }

    @Override
    public EnumMap<Property, Integer> getWeaponProperties() {
        return weaponProperties;
    }
}
