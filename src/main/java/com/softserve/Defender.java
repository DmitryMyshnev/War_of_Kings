package com.softserve;

import lombok.Getter;

@Getter
public class Defender extends Warrior {
    private final int protection;

    public Defender() {
        super(60, new Attack(3));
        protection = 2;
    }

    @Override
    public void takeDamage(Attack attack) {
      int damage = attack.getSimpleAttack() > protection? attack.getSimpleAttack() - protection : 0;
      super.takeDamage(new Attack(damage));
    }

}
