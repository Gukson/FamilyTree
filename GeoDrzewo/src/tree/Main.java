package tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Map<String, Person> people = new HashMap<>();
        Map<Person, Person> kidFather = new HashMap<>();
        Map<Person, Person> partnerships = new HashMap<>();

        try {
            Scanner scanner = new Scanner(new File("GeoDrzewo/persons.txt"));
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] splitted = data.split(";");
                for (String s : splitted) {
                    if (!s.trim().equals("-")){
                        if (!people.containsKey(s)) {
                            String[] person = s.split(" ");
                            people.put(s, new Person(person[0], person[1]));
                        }
                    }
                }

                Person person = people.get(splitted[0]);
                Person father = people.getOrDefault(splitted[1], null);
                Person partner = people.getOrDefault(splitted[2], null);

                kidFather.put(person, father);
                partnerships.put(person, partner);
                partnerships.put(partner, person);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        /*
        1. Create node per person. Include partner and father info.
        2. Fill in children.
         */
    }
}
