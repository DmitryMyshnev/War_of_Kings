package com.softserve.warrior;

import com.softserve.weapon.Weapon;
import lombok.Getter;

@Getter
public class Healer extends Warrior {
    private int treatment;
    private int healCount;

    public Healer() {
        super(50, new Attack(0),60);
        treatment = 2;
        healCount = 250;
    }

    protected void setTreatment(int treatment) {
        this.treatment = Math.max(treatment, 0);
    }

    public void heal(Warrior warrior) {
        if (healCount > 0 && warrior.getHealth() < warrior.getMaxHealth()) {
            warrior.setHealth(warrior.getHealth() + getTreatment());
            healCount--;
        }
    }

    @Override
    public void makeDamage(Warrior opponent, Attack attack) {
        super.makeDamage(opponent, attack);
        receiveDamage(new Attack(getMaxHealth()));
    }

    @Override
    public boolean equipWeapon(Weapon weapon) {
        if (weapon == null) {
            return false;
        }
        int healthValue = weapon.getWeaponProperties().getOrDefault(Weapon.Property.HEALTH, 0);
        increaseMaxHealth(healthValue);
        setHealth(getHealth() + healthValue);
        int healPowerValue = weapon.getWeaponProperties().getOrDefault(Weapon.Property.HEAL_POWER, 0);
        setTreatment(getTreatment() + healPowerValue);
        return true;
    }
}
