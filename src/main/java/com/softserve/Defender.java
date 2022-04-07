package com.softserve;

import lombok.Getter;

@Getter
public class Defender extends Warrior {
    private final int protection;

    public Defender() {
        super(60, 3);
        protection = 2;
    }

    @Override
    public void hit(int attack) {
      int damage = attack > protection? attack - protection : 0;
      super.hit(damage);
    }
}
