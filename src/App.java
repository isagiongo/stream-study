import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {

    public static void main(String[] args) {

        IntStream.range(1,10).skip(5).forEach(System.out::println);

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
        System.out.println();
        System.out.println("Filtra por gender female, ordenando por idade e depois por nome e printa essa lista");
        List<Person> females = peopleList.stream()
                .filter(p -> p.getGender().equals(Gender.FEMALE))
                .sorted(Comparator.comparing(Person::getAge)
                        .thenComparing(Person::getName))
                .collect(Collectors.toList());

        females.forEach(System.out::println);

        //AnyMatch
        System.out.println();
        System.out.println("Printa true se houver qualquer item com age menor que 20");
        Boolean hasTeenager = peopleList.stream()
                .anyMatch(p -> p.getAge() < 20);
        System.out.println(hasTeenager);

        //Group for gender
        System.out.println();
        System.out.println("Agrupa por gender e printa separadamente");
        Map<Gender, List<Person>> groupByGender = peopleList.stream()
                .collect(Collectors.groupingBy(Person::getGender));

        groupByGender.forEach((gender, people) -> {
            System.out.println(gender);
            people.forEach(System.out::println);
            System.out.println();
        });

        //mais velha por genero female e printar só nome
        System.out.println();
        System.out.println("Filtro por gender, pegando a pessoa mais velha e printar só nome");
        Optional<String> oldestFemaleAge = peopleList.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);
        oldestFemaleAge.ifPresent(System.out::println);

        //mais novo
        System.out.println();
        System.out.println("Orderna por idade, pega a primeira e printa");
        peopleList.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .findFirst()
                .ifPresent(System.out::println);

        //mais novo female
        System.out.println();
        System.out.println("Filtra por gender, ordena por idade reversa, pega a primeira e printa");
        peopleList.stream()
                .filter(p -> p.getGender().equals(Gender.FEMALE))
                .sorted(Comparator.comparing(Person::getAge)
                        .reversed())
                .findFirst()
                .ifPresent(System.out::println);

        //filtrar por nomes começados por A ordenando por idade
        System.out.println();
        System.out.println("Filtra por nomes começados por A do gender female e ordena por idade");
        peopleList.stream()
                .filter(p -> p.getName().startsWith("A"))
                .filter(p -> p.getGender().equals(Gender.FEMALE))
                .sorted(Comparator.comparing(Person::getAge))
                .forEach(System.out::println);
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
