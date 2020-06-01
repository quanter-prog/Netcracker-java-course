package com.netcracker.lab.entities.comparators;

import ru.vsu.lab.entities.IPerson;

import java.util.Comparator;

/**
 * Компаратор для сравнения людей по возрасту.
 */
public class PersonAgeComparator implements Comparator<IPerson> {
    @Override
    public int compare(IPerson p1, IPerson p2) {
        return p1.getAge().compareTo(p2.getAge());
    }
}