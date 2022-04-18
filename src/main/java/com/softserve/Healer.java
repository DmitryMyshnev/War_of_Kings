package com.softserve;

import com.softserve.weapon.Weapon;
import lombok.Getter;

@Getter
public class Healer extends Warrior {
    private int treatment;

    public Healer() {
        super(50, new Attack(0));
        treatment = 2;
    }

    protected void setTreatment(int treatment) {
        this.treatment = Math.max(treatment, 0);
    }

    public void heal(Warrior warrior) {
        warrior.setHealth(warrior.getHealth() + this.getTreatment());
    }

    @Override
    public void makeDamage(Warrior opponent, Attack attack) {
        super.makeDamage(opponent,attack);
        receiveDamage(new Attack(this.getMaxHealth()));
    }

    @Override
    public void equipWeapon(Weapon weapon) {
      int healPowerValue = weapon.getWeaponProperties().getOrDefault(Weapon.Property.HEAL_POWER,0);
      setTreatment(getTreatment() + healPowerValue);
    }
}
