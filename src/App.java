import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        List<Person> peopleList = getPeople();

        // Imperative approach
        /*List<Person> females = new ArrayList<>();

        for(Person person : peopleList) {
            if(Gender.FEMALE.equals(person.getGender())) {
                females.add(person);
            }
        }

        females.forEach(System.out::println);*/

        //Declarative
        List<Person> females = peopleList.stream()
                .filter(p -> p.getGender().equals(Gender.FEMALE))
                .sorted(Comparator.comparing(Person::getAge)
                        .thenComparing(Person::getName))
                .collect(Collectors.toList());

        females.forEach(System.out::println);

        //AnyMatch
        Boolean hasTeenager = peopleList.stream()
                .anyMatch(p -> p.getAge() < 20);
        System.out.println(hasTeenager);

        //Group
        Map<Gender, List<Person>> groupByGender = peopleList.stream()
                .collect(Collectors.groupingBy(Person::getGender));

        groupByGender.forEach((gender, people) -> {
            System.out.println(gender);
            people.forEach(System.out::println);
            System.out.println();
        });

        //Max
        Optional<String> oldestFemaleAge = peopleList.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);
        oldestFemaleAge.ifPresent(System.out::println);
    }

    private static List<Person> getPeople() {
        return List.of(
                new Person("Ada Lovelace", 58, Gender.FEMALE),
                new Person("Greice Hopper", 58, Gender.FEMALE),
                new Person("Allan Turing", 43, Gender.MALE),
                new Person("Steve Jobs", 66, Gender.OTHER),
                new Person("Fernanda Torres", 21, Gender.FEMALE),
                new Person("Carla Torres", 21, Gender.FEMALE),
                new Person("Laura Hopper", 33, Gender.FEMALE),
                new Person("Camila Sampaio", 44, Gender.FEMALE),
                new Person("Amanda Cruz", 19, Gender.FEMALE),
                new Person("Linuz Torvalds", 20, Gender.MALE),
                new Person("Joana Souza", 16, Gender.OTHER)
        );
    }
}
