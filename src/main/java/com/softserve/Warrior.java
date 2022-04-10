package com.softserve;

import com.softserve.weapons.WeaponType;
import com.softserve.weapons.Weapons;
import lombok.Getter;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

@Getter
public class Warrior implements Fights {
    private WarriorType type;
    private int health;
    private int maxHealth;
    private final Attack attack;
    private boolean isAlive;
    private Warrior previousWarrior;
    private Map<WeaponType, Weapons> weapons;

    public Warrior() {
        this(50, new Attack(5));
        type = WarriorType.WARRIOR;
    }

    protected Warrior(int health, Attack attack) {
        this.health = health;
        this.attack = attack;
        isAlive = true;
        maxHealth = this.health;
        weapons = new HashMap<>();
    }


    protected void setType(WarriorType type) {
        this.type = type;
    }

    protected void setHealth(int health) {
        this.health = Math.min(health, maxHealth);
    }

    protected void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    protected void setPreviousWarrior(Warrior previousWarrior) {
        this.previousWarrior = previousWarrior;
    }

    @Override
    public int receiveDamage(Attack attack) {
        int priviousHealth = health;
        health -= attack.getAttack();
        if (health <= 0) {
            isAlive = false;
            return priviousHealth;
        }
        return priviousHealth - health;
    }

    @Override
    public void makeDamage(Warrior opponent, Attack attack) {
        opponent.receiveDamage(attack);
    }

    public void treatmentByHealer() {
        if (previousWarrior != null && previousWarrior instanceof Healer healer) {
            healer.heal(this);
        }
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
