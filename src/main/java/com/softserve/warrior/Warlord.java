package com.softserve.warrior;

import com.softserve.utils.EventListener;
import com.softserve.utils.EventManager;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Getter
public class Warlord extends Defender implements EventListener {
    private List<Warrior> warriors;

    public Warlord() {
        super(100, new Attack(4), 2, 100);
        warriors = new LinkedList<>();
    }

    public void setWarriors(List<Warrior> warriors) {
        this.warriors = warriors;
    }

    @Override
    public void update(EventManager.Event event) {
        if (event.equals(EventManager.Event.DIED)) {
            moveUnit();
        }
    }

    public void moveUnit() {
        LinkedList<Warrior> allAliveWarrior;
        Optional<Warrior> warlord;

        allAliveWarrior = warriors.stream()
                .filter(Warrior::isAlive)
                .collect(Collectors.toCollection(LinkedList::new));
        warlord = allAliveWarrior.stream()
                .filter(lord -> lord.getPriority() == 100).findFirst();

        Predicate<Warrior> war = w -> w.getPriority() == 10;
        Predicate<Warrior> def = d -> d.getPriority() == 30;
        Predicate<Warrior> vamp = v -> v.getPriority() == 50;
        Predicate<Warrior> kngt = k -> k.getPriority() == 20;

        LinkedList<Warrior> anotherWarriors;
        anotherWarriors = allAliveWarrior.stream()
                .filter(war.or(def).or(vamp).or(kngt))
                .collect(Collectors.toCollection(LinkedList::new));

        LinkedList<Warrior> rearrangedArmy = new LinkedList<>();
        allAliveWarrior.stream().filter(lancer -> lancer.getPriority() == 40).findFirst()
                .ifPresentOrElse(t -> {
                            rearrangedArmy.add(t);
                            allAliveWarrior.remove(t);
                        },
                        () -> anotherWarriors.stream()
                                .findFirst()
                                .ifPresent(w -> rearrangedArmy.add(anotherWarriors.poll())));
        rearrangedArmy.addAll(allAliveWarrior.stream()
                .filter(healer -> healer.getPriority() == 60)
                .collect(Collectors.toCollection(LinkedList::new)));

        rearrangedArmy.addAll(allAliveWarrior.stream()
                .filter(lancer -> lancer.getPriority() == 40)
                .collect(Collectors.toCollection(LinkedList::new)));

        rearrangedArmy.addAll(anotherWarriors);

        rearrangedArmy.addAll(allAliveWarrior.stream()
                .filter(witcher -> witcher.getPriority() == 90)
                .collect(Collectors.toCollection(LinkedList::new)));

        warlord.ifPresent(rearrangedArmy::add);

        warriors.clear();
        warriors.addAll(rearrangedArmy);
        updateLinks();
    }

    private void updateLinks() {
        ListIterator<Warrior> it = warriors.listIterator();
        Warrior prevoius = null;
        Warrior next;
        while (it.hasNext()) {
            next = it.next();
            if (prevoius != null) {
                next.setNextWarrior(prevoius);
                prevoius.setPreviousWarrior(next);
            }
            prevoius = next;
        }
    }
}
