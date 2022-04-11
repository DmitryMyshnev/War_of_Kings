package com.softserve;

import lombok.Getter;

@Getter
public class Defender extends Warrior {
    private int protection;

    public Defender() {
        super(60, new Attack(3));
        setProtection(2);
    }

    protected void setProtection(int protection) {
        this.protection = protection;
    }

    @Override
    public void receiveDamage(Attack attack) {
        int damage = attack.getAttack() > protection ? attack.getAttack() - getProtection() : 0;
         super.receiveDamage(new Attack(damage));
    }

}
