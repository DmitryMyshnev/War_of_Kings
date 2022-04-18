package com.softserve.weapon.weapons;

import com.softserve.weapon.Weapon;

import java.util.EnumMap;

public class Katana implements Weapon {

    private final EnumMap<Property, Integer> weaponProperties;

    public Katana() {
        weaponProperties = new EnumMap<>(Property.class);
        weaponProperties.put(Property.HEALTH, -20);
        weaponProperties.put(Property.ATTACK, 6);
        weaponProperties.put(Property.DEFENSE, -5);
        weaponProperties.put(Property.VAMPIRISM, 50);
    }

    @Override
    public EnumMap<Property, Integer> getWeaponProperties() {
        return weaponProperties;
    }
}
