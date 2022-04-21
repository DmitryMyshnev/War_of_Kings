package com.softserve;

import com.softserve.weapon.Weapon;
import lombok.Getter;

import java.lang.reflect.Constructor;

@Getter
public class Warrior implements Fighter {
    private int health;
    private int maxHealth;
    private final Attack attack;
    private Warrior previousWarrior;
    private Warrior nextWarrior;
    private Weapon weapon;
    public EventManager em;

    public Warrior() {
        this(50, new Attack(5));
    }

    protected Warrior(int health, Attack attack) {
        this.health = health;
        this.attack = attack;
        maxHealth = this.health;
        em = EventManager.getInstance();
    }

    protected void increaceAttack(int additive) {
        attack.setAttack(attack.getAttack() + additive);
    }

    private void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    protected void setHealth(int health) {
        this.health = health < 0 ? 0 : Math.min(health, maxHealth);
    }

    protected void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    protected void setPreviousWarrior(Warrior previousWarrior) {
        this.previousWarrior = previousWarrior;
    }

    protected void setNextWarrior(Warrior nextWarrior) {
        this.nextWarrior = nextWarrior;
    }

    @Override
    public boolean isAlive() {
        return getHealth() > 0;
    }


    @Override
    public void receiveDamage(Attack attack) {
        health -= attack.getAttack();
    }


    @Override
    public void makeDamage(Warrior opponent, Attack attack) {
        opponent.receiveDamage(attack);
        em.notifyEvent(EventManager.Event.TREATMENT);

    }

    protected void increaseMaxHealth(int additive) {
        setMaxHealth(getMaxHealth() + additive);
    }

    public void equipWeapon(Weapon weapon) {
        setWeapon(weapon);
        if (weapon != null) {
            int healthValue = weapon.getWeaponProperties().getOrDefault(Weapon.Property.HEALTH, 0);
            increaseMaxHealth(healthValue);
            setHealth(getHealth() + healthValue);
            int attackValue = weapon.getWeaponProperties().getOrDefault(Weapon.Property.ATTACK, 0);
            increaceAttack(attackValue);
        }
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

}
