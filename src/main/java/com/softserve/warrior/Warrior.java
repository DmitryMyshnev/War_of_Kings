package com.softserve.warrior;

import com.softserve.utils.EventManager;
import com.softserve.weapon.Weapon;
import lombok.Getter;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Warrior  {
    private int health;
    private int maxHealth;
    private final Attack attack;
    private Warrior previousWarrior;
    private Warrior nextWarrior;
    private final List<Weapon> weapons;
    public final EventManager eventManager;
    private final int priority;

    public Warrior() {
        this(50, new Attack(5),10);
    }

    protected Warrior(int health, Attack attack, int priority) {
        this.health = health;
        this.attack = attack;
        maxHealth = this.health;
        weapons = new ArrayList<>();
        eventManager = new EventManager();
        this.priority = priority;
    }

    protected void increaceAttack(int additive) {
        attack.setAttack(attack.getAttack() + additive);
    }

    protected void setHealth(int health) {
        this.health = health < 0 ? 0 : Math.min(health, maxHealth);
    }

    private void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setPreviousWarrior(Warrior previousWarrior) {
        this.previousWarrior = previousWarrior;
    }

    public void setNextWarrior(Warrior nextWarrior) {
        this.nextWarrior = nextWarrior;
    }

    protected void increaseMaxHealth(int additive) {
        setMaxHealth(Math.max(getMaxHealth() + additive, 0));
    }

    public boolean isAlive() {
        return getHealth() > 0;
    }

    public void receiveDamage(Attack attack) {
        health -= attack.getAttack();
        eventManager.notifyEvent(EventManager.Event.RECEIVE_DAMAGE);
        if (!isAlive()) {
            eventManager.notifyEvent(EventManager.Event.DIED);
        }
    }

    public void makeDamage(Warrior opponent, Attack attack) {
        opponent.receiveDamage(attack);
        eventManager.notifyEvent(EventManager.Event.MAKE_DAMAGE);
    }

    public boolean equipWeapon(Weapon weapon) {
        if (weapon == null) {
            return false;
        }
        weapons.add(weapon);
        int healthValue = weapon.getWeaponProperties().getOrDefault(Weapon.Property.HEALTH, 0);
        increaseMaxHealth(healthValue);
        setHealth(getHealth() + healthValue);
        int attackValue = weapon.getWeaponProperties().getOrDefault(Weapon.Property.ATTACK, 0);
        increaceAttack(attackValue);
        return true;
    }

    public static Warrior typeOf(Class<? extends Warrior> type) {
        Warrior warrior = null;
        try {
            Constructor<? extends Warrior> constructor = type.getDeclaredConstructor();
            warrior = constructor.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException("Can not create warrior", e);
        }
        return warrior;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + getHealth();
    }

}
