package tree;

public class Person {
    private final String name;
    private final String surname;
    private final Sex sex;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.sex = Sex.fromName(name);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Sex getSex() {
        return sex;
    }
}