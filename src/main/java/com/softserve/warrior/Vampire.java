package com.softserve.warrior;

import com.softserve.weapon.Weapon;
import lombok.Getter;

@Getter
public class Vampire extends Warrior {
    private int vampirism;
    private static final int PROCENT = 100;

    public Vampire() {
        super(40, new Attack(4),50);
        vampirism = 50;
    }

    protected void setVampirism(int vampirism) {
        this.vampirism = Math.max(vampirism, 0);
    }

    @Override
    public void makeDamage(Warrior opponent, Attack attack) {
        int haelthBeforeAttack = opponent.getHealth();
        super.makeDamage(opponent, attack);
        int successAttackLevel = haelthBeforeAttack - opponent.getHealth();
        int health = getHealth() + (successAttackLevel * getVampirism()) / PROCENT;
        setHealth(health);
    }

    @Override
    public boolean equipWeapon(Weapon weapon) {
        if (weapon == null) {
            return false;
        }
        super.equipWeapon(weapon);
        int vampireValue = weapon.getWeaponProperties().getOrDefault(Weapon.Property.VAMPIRISM, 0);
        setVampirism(getVampirism() + vampireValue);
        return true;
    }
}
