package com.softserve.utils;

import com.softserve.Army;
import com.softserve.warrior.Attack;
import com.softserve.warrior.Warrior;
import com.softserve.warrior.Witcher;
import lombok.NonNull;

import java.util.*;

public class ArmyMediator implements Mediator<Army> {
    private final Map<String, Army> armies;
    private static ArmyMediator armyMediator;

    private ArmyMediator() {
        armies = new HashMap<>();
    }

    @Override
    public void inform(Army sender, String action) {
        if ("Damnation".equalsIgnoreCase(action)) {
            armies.forEach((key, army) -> {
                if (!key.equals(sender.getName())) {
                    Iterator<Warrior> iterator = army.allAliveWarrior();
                    while (iterator.hasNext()) {
                        iterator.next().receiveDamage(new Attack(Witcher.DAMNATION));
                    }
                }
            });
        }
    }

    @Override
    public void register(@NonNull Army army) {
        armies.put(army.getName(), army);
    }

    public static ArmyMediator getInstance() {
        if (armyMediator == null) {
            armyMediator = new ArmyMediator();
        }
        return armyMediator;
    }
}
