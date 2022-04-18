package com.softserve;

import com.softserve.weapon.Weapon;
import lombok.Getter;

@Getter
public class Lancer extends Warrior {
    private int piercingDamage;
    private static final int PROCENT = 100;

    public Lancer() {
        super(50, new Attack(6));
        piercingDamage = 50;
    }

    protected void setPiercingDamage(int piercingDamage) {
        this.piercingDamage = piercingDamage;
    }

    @Override
    public void makeDamage(Warrior opponent, Attack attack) {
        int haelthBeforeAttack = opponent.getHealth();
        // opponent.receiveDamage(attack);
        super.makeDamage(opponent, attack);
        int successAttackLevel = haelthBeforeAttack - opponent.getHealth();
        Warrior previousWarrior = opponent.getPreviousWarrior();
        if (previousWarrior != null) {
            int damage = successAttackLevel * getPiercingDamage() / PROCENT;
            previousWarrior.receiveDamage(new Attack(damage));
        }
        //  treatmentByHealer();
    }

    @Override
    public void equipWeapon(Weapon weapon) {
        super.equipWeapon(weapon);
        int piercingDamageValue = weapon.getWeaponProperties().getOrDefault(Weapon.Property.PIERCING_DAMAGE, 0);
        setPiercingDamage(getPiercingDamage() + piercingDamageValue);
    }
}
