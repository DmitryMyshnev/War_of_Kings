package com.softserve.weapon.weapons;

import com.softserve.weapon.Weapon;

import java.util.EnumMap;

public class CastomWeapon implements Weapon {
    private EnumMap<Property, Integer> weaponProperties;

    private CastomWeapon(Builder builder) {
        weaponProperties = new EnumMap<>(Property.class);
        weaponProperties.put(Property.HEALTH, builder.healse);
        weaponProperties.put(Property.ATTACK, builder.atack);
        weaponProperties.put(Property.DEFENSE, builder.defense);
        weaponProperties.put(Property.VAMPIRISM, builder.vampirism);
        weaponProperties.put(Property.HEAL_POWER, builder.healPower);
    }

    @Override
    public EnumMap<Property, Integer> getWeaponProperties() {
        return weaponProperties;
    }

    public static class Builder {
        private int healse;
        private int atack;
        private int defense;
        private int vampirism;
        private int healPower;

        public Builder() {
        }

        public Builder healse(int healse) {
            this.healse = healse;
            return this;
        }

        public Builder atack(int atack) {
            this.atack = atack;
            return this;
        }

        public Builder defanse(int defense) {
            this.defense = defense;
            return this;
        }

        public Builder vampirism(int vampirism) {
            this.vampirism = vampirism;
            return this;
        }

        public Builder healPower(int healPower) {
            this.healPower = healPower;
            return this;
        }

        public CastomWeapon build() {
            return new CastomWeapon(this);
        }
    }
}
