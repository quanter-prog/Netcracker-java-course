package com.netcracker.lab

import com.netcracker.lab.entities.Division
import com.netcracker.lab.entities.IPerson
import com.netcracker.lab.entities.Person
import com.netcracker.lab.entities.comparators.PersonAgeComparator

import com.netcracker.lab.entities.comparators.PersonNameComparator
import com.netcracker.lab.entities.comparators.PersonSalaryComparator
import com.netcracker.lab.entities.enums.Gender
import com.netcracker.lab.repository.Repository

import java.util.function.Predicate

/**
 * Класс Groovy тестов для Repository.
 */
class RepositoryTest extends GroovyTestCase {
    /**
     * Тест, который возвращает экзампляр класса Person по его индексу в репозитории
     */
    void testGetPerson() {
        Repository rep = new Repository()
        Division marketingDivision = new Division("Marketing")
        Person human1 = new Person("Илья", "Марков",
                "1998-11-18", Gender.MALE, (BigDecimal)100000,
                marketingDivision)
        Person human2 = new Person("Петр", "Краснов",
                "1990-01-07", Gender.MALE, (BigDecimal)150000,
                marketingDivision)
        Person human3 = new Person("Анька", "Жданова",
                "1999-08-22", Gender.FEMALE, (BigDecimal)200000,
                marketingDivision)
        rep.add(human1)
        rep.add(human2)
        rep.add(human3)
        println(rep.toString())
    }
/**
 * Тест, который возвращает экзампляр класса Person по его id
 */
    void testGetPersonById() {
        Repository rep = new Repository()
        Division marketingDivision = new Division("Marketing")
        Person human1 = new Person("Илья", "Марков",
                "1998-11-18", Gender.MALE, (BigDecimal)100000,
                marketingDivision)
        Person human2 = new Person("Петр", "Краснов",
                "1990-01-07", Gender.MALE, (BigDecimal)150000,
                marketingDivision)
        Person human3 = new Person("Анька", "Жданова",
                "1999-08-22", Gender.FEMALE, (BigDecimal)200000,
                marketingDivision)
        rep.add(human1)
        rep.add(human2)
        rep.add(human3)
        println(rep.getPersonById(3).toString())
    }
/**
 * Проверка фукции удаления как по индексу, так и по конкретному экземпляру класса Person
 */
    void testDelete() {
        Repository rep = new Repository()
        Division marketingDivision = new Division("Marketing")
        Person human1 = new Person("Илья", "Марков",
                "1998-11-18", Gender.MALE, (BigDecimal)100000,
                marketingDivision)
        Person human2 = new Person("Петр", "Краснов",
                "1990-01-07", Gender.MALE, (BigDecimal)150000,
                marketingDivision)
        Person human3 = new Person("Анька", "Жданова",
                "1999-08-22", Gender.FEMALE, (BigDecimal)200000,
                marketingDivision)
        rep.add(human1)
        rep.add(human2)
        rep.add(human3)
        println("Репозиторий до удаления: \n" + rep.toString())

        rep.delete(2)
        println("Репозиторий после удаления Аньки: \n" + rep.toString())

        rep.delete(1)
        println("Репозиторий после удаления Пети: \n" + rep.toString())

        rep.delete(0)
        println("Репозиторий после удаления Ильи: \n" + rep.toString())
    }
/**
 * Тест перегрузки метода toString()
 */
    void testSet() {
        Repository rep = new Repository(1)
        Division marketingDivision = new Division("Marketing")
        Person human1 = new Person("Илья", "Марков",
                "1998-11-18", Gender.MALE, (BigDecimal)100000,
                marketingDivision)
        Person human2 = new Person("Петр", "Краснов",
                "1990-01-07", Gender.MALE, (BigDecimal)150000,
                marketingDivision)
        Person human3 = new Person("Анька", "Жданова",
                "1999-08-22", Gender.FEMALE, (BigDecimal)200000,
                marketingDivision)

        rep.add(human1)
        rep.set(0, human2)
        rep.add(human3)
        println(rep.toString())
    }

    void testToList() {
        Repository rep = new Repository(1)
        Division marketingDivision = new Division("Marketing")
        Person human1 = new Person("Илья", "Марков",
                "1998-11-18", Gender.MALE, (BigDecimal)100000,
                marketingDivision)
        Person human2 = new Person("Петр", "Краснов",
                "1990-01-07", Gender.MALE, (BigDecimal)150000,
                marketingDivision)
        Person human3 = new Person("Анька", "Жданова",
                "1999-08-22", Gender.FEMALE, (BigDecimal)200000,
                marketingDivision)

        rep.add(human1)
        rep.set(0, human2)
        rep.add(human3)
        println(rep.toList().toString())
    }

    void testSortBy() {
        Repository rep = new Repository(1)
        Division marketingDivision = new Division("Marketing")
        Person human1 = new Person("Илья", "Марков",
                "1998-11-18", Gender.MALE, (BigDecimal)100000,
                marketingDivision)
        Person human2 = new Person("Петр", "Краснов",
                "1990-01-07", Gender.MALE, (BigDecimal)150000,
                marketingDivision)
        Person human3 = new Person("Анька", "Жданова",
                "1995-08-22", Gender.FEMALE, (BigDecimal)200000,
                marketingDivision)

        rep.add(human1)
        rep.set(0, human2)
        rep.add(human3)

        Comparator<IPerson> ageComp = new PersonAgeComparator()
        Comparator<IPerson> nameComp = new PersonNameComparator()
        Comparator<IPerson> salaryComp = new PersonSalaryComparator()

        println(rep.toString())
        //rep.sortBy(ageComp)
        //rep.sortBy(nameComp)
        rep.sortBy(salaryComp)
        println("\n" + rep.toString())
    }

    void testSearch() {
        Repository rep = new Repository(1)
        Division marketingDivision = new Division("Marketing")
        Person human1 = new Person("Илья", "Марков",
                "1998-11-18", Gender.MALE, (BigDecimal)100000,
                marketingDivision)
        Person human2 = new Person("Петр", "Краснов",
                "1990-01-07", Gender.MALE, (BigDecimal)150000,
                marketingDivision)
        Person human3 = new Person("Анька", "Жданова",
                "1995-08-22", Gender.FEMALE, (BigDecimal)200000,
                marketingDivision)

        rep.add(human1)
        rep.set(0, human2)
        rep.add(human3)

        println(rep.toString())

        Predicate<IPerson> isSalaryHigh = new Predicate<IPerson>() {
            @Override
            boolean test(IPerson iPerson) {
                return iPerson.getSalary() > 130000
            }
        }
        println(rep.searchBy(isSalaryHigh).toString())
    }
}