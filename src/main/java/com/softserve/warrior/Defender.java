package com.softserve.warrior;

import com.softserve.weapon.Weapon;
import lombok.Getter;

@Getter
public class Defender extends Warrior {
    private int protection;

    public Defender() {
        super(60, new Attack(3),30);
        setProtection(2);
    }

    protected Defender(int health, Attack attack, int protection, int priority) {
        super(health, attack,priority);
        this.protection = protection;
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
    public boolean equipWeapon(Weapon weapon) {
        if (weapon == null) {
            return false;
        }
        super.equipWeapon(weapon);
        int defenseValue = weapon.getWeaponProperties().getOrDefault(Weapon.Property.DEFENSE, 0);
        setProtection(getProtection() + defenseValue);
        return true;
    }
}
