package tree;

import java.util.ArrayList;
import java.util.List;

public class Node {
    //fields
    private Person person;
    private Node father;
    private Node partner;
    private List<Node> children;

    public Node(Person person, Node father, Node partner, List<Node> children) {
        this.person = person;
        this.father = father;
        this.partner = partner;
        this.children = children;
    }

    public Node(Person person){
        this(person, null, null, new ArrayList<>());
    }

    public Node(Person person, Node father, Node partner) {
        this(person, father, partner, new ArrayList<>());
    }

    public Person getPerson(){
        return this.person;
    }

    public Node getFather(){
        return this.father;
    }

    public Node getPartner(){
        return this.partner;
    }

    public List<Node> get_children() {
        return this.children;
    }

    public void display(){
        //todo
    }
}