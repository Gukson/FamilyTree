package tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Family {

    private Set<Person> familyMembers;
    private Map<Person, Node> relationships;
    Map<BiFunction<Node, Node, Boolean>, String> relationshipDefinitions = new HashMap<>();

    public Family() {

        BiFunction<Node, Node, Boolean> isWife = (person, to) -> person.getPerson().getSex() == Sex.FEMALE && person.getPartner().equals(to.getPerson());
        BiFunction<Node, Node, Boolean> isHusband = (person, to) -> person.getPerson().getSex() == Sex.MALE && person.getPartner().equals(to.getPerson());

        relationshipDefinitions.put(isWife, "żona");
        relationshipDefinitions.put(isHusband, "mąż");
    }


    public String getRelationship(Node personFrom, Node personTo) {
        for (BiFunction<Node, Node, Boolean> relationshipCheck : this.relationshipDefinitions.keySet()) {
            if (relationshipCheck(personFrom, personTo)) {
                return this.relationshipDefinitions(relationshipCheck)
            }
        }
    }


}
