package com.softserve;

import com.softserve.weapon.Weapon;
import lombok.Getter;

@Getter
public class Defender extends Warrior {
    private int protection;

    public Defender() {
        super(60, new Attack(3));
        setProtection(2);
    }

    protected void setProtection(int protection) {
        this.protection = Math.max(0, protection);
    }

    @Override
    public void receiveDamage(Attack attack) {
        int damage = attack.getAttack() > protection ? attack.getAttack() - getProtection() : 0;
        super.receiveDamage(new Attack(damage));
    }

    @Override
    public void equipWeapon(Weapon weapon) {
        super.equipWeapon(weapon);
        int defenseValue = weapon.getWeaponProperties().getOrDefault(Weapon.Property.DEFENSE, 0);
        setProtection(getProtection() + defenseValue);
    }
}
