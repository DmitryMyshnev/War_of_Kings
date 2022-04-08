package com.softserve;

import lombok.Getter;

import java.lang.reflect.Constructor;

@Getter
public class Warrior implements Fights {
    private int health;
    private  int maxHealth;
    private final Attack attack;
    private boolean isAlive;

    public Warrior() {
        this(50, new Attack(5));
    }

    protected Warrior(int health, Attack attack) {
        this.health = health;
        this.attack = attack;
        isAlive = true;
        maxHealth = this.health;
    }

    @Override
    public void takeDamage(Attack attack) {
        health -= attack.getSimpleAttack();

        if (health <= 0) {
            isAlive = false;
        }
    }

    @Override
    public void makeDamage(Warrior warrior, Attack attack) {
        warrior.takeDamage(attack);
    }

    public static Warrior typeOf(Class<? extends Warrior> type) {
        Warrior warrior = null;
        try {
            Constructor<? extends Warrior> constructor = type.getDeclaredConstructor();
            warrior = constructor.newInstance();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return warrior;
    }

}
