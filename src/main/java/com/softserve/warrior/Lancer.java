package com.softserve.warrior;

import com.softserve.weapon.Weapon;
import lombok.Getter;

@Getter
public class Lancer extends Warrior {
    private int piercingDamage;
    private static final int PROCENT = 100;

    public Lancer() {
        super(50, new Attack(6),40);
        piercingDamage = 50;
    }

    protected void setPiercingDamage(int piercingDamage) {
        this.piercingDamage = piercingDamage;
    }

    @Override
    public void makeDamage(Warrior opponent, Attack attack) {
        int haelthBeforeAttack = opponent.getHealth();
        super.makeDamage(opponent, attack);
        int successAttackLevel = haelthBeforeAttack - opponent.getHealth();
        Warrior previousWarrior = opponent.getPreviousWarrior();
        if (previousWarrior != null) {
            int damage = successAttackLevel * getPiercingDamage() / PROCENT;
            previousWarrior.receiveDamage(new Attack(damage));
        }
    }

    @Override
    public boolean equipWeapon(Weapon weapon) {
        if (weapon == null) {
            return false;
        }
        super.equipWeapon(weapon);
        int piercingDamageValue = weapon.getWeaponProperties().getOrDefault(Weapon.Property.PIERCING_DAMAGE, 0);
        setPiercingDamage(getPiercingDamage() + piercingDamageValue);
        return true;
    }
}
