package com.softserve;

import lombok.Getter;

import java.lang.reflect.Constructor;

@Getter
public class Warrior {
    private int health;
    private final int attack;
    private boolean isAlive;

    public Warrior() {
       this(50,5);
    }

    protected Warrior(int health, int attack) {
        this.health = health;
        this.attack = attack;
        isAlive = true;
    }

    public boolean isAlive() {
        return isAlive;
    }

    protected void getPunch(int attack) {
        health -= attack;
        if (health <= 0) {
            isAlive = false;
        }
    }
    public static Warrior typeOf(Class<? extends Warrior> type){
        Warrior warrior = null;
        try {
            Constructor<? extends Warrior> constructor =  type.getDeclaredConstructor();
            warrior = constructor.newInstance();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return warrior;
    }

}
