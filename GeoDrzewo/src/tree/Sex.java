package tree;

public enum Sex {
    MALE, FEMALE;

    public static Sex fromName(String name) {
        if (name.endsWith("a")) {
            return FEMALE;
        } return MALE;
    }
}
