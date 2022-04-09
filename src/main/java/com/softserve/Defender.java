package com.softserve;

import lombok.Getter;

@Getter
public class Defender extends Warrior {
    private int protection;

    public Defender() {
        super(60, new Attack(3));
       setProtection(2);
       setType(WarriorType.DEFENDER);
    }

    protected void setProtection(int protection) {
        this.protection = protection;
    }

    @Override
    public int receiveDamage(Attack attack) {
        int damage = attack.getAttack() > protection ? attack.getAttack() - getProtection() : 0;
        return super.receiveDamage(new Attack(damage));
    }

}
