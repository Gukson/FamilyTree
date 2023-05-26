import com.sun.xml.internal.ws.wsdl.writer.document.Part;

import java.util.ArrayList;
import java.util.Objects;

public class Person {
    String Name;
    String Surname;
    String sex;

    Person Spouse;
    Person Father;
    ArrayList<Person> Kids;

    public Person(String name, String surname){
        this.Name = name;
        this.Surname = surname;
        this.sex = Sex(this.Name);
    }

    public void updatePerson(String FatherName, String PartnerName){
        if(!Objects.equals(FatherName, "-")){
            if(!(main.lud.containsKey(FatherName))){
                main.lud.put(FatherName, new Person(FatherName.split(" ")[0],FatherName.split(" ")[1]));//do Hashmapy dodajemy Ojca
            }
            this.Father = main.lud.get(FatherName);//dodaje sobie ojca
        }
        if(!Objects.equals(PartnerName, "-")){
            if(!(main.lud.containsKey(PartnerName))){
                main.lud.put(PartnerName, new Person( PartnerName.split(" ")[0],PartnerName.split(" ")[1]));//do Hashmapy Partnera
            }
            main.lud.get(PartnerName).Spouse = this; // Jako partnera tej osoby ustawiamy tą osobę
            this.Spouse = main.lud.get(PartnerName); // Ustawiam siebie jako partnera partnera xD
        }
    }

    public Person(String name, String surname, String FatherName, String PartnerName){
        this.Name = name;
        this.Surname = surname;
        this.sex = Sex(this.Name);
        if(!Objects.equals(FatherName, "-")){
            if(!(main.lud.containsKey(FatherName))){
                main.lud.put(FatherName, new Person(FatherName.split(" ")[0],FatherName.split(" ")[1]));//do Hashmapy dodajemy Ojca
            }
            this.Father = main.lud.get(FatherName);//dodaje sobie ojca
        }
        if(!Objects.equals(PartnerName, "-")){
            if(!(main.lud.containsKey(PartnerName))){
                main.lud.put(PartnerName, new Person( PartnerName.split(" ")[0],PartnerName.split(" ")[1]));//do Hashmapy Partnera
            }
            main.lud.get(PartnerName).Spouse = this; // Jako partnera tej osoby ustawiamy tą osobę
            this.Spouse = main.lud.get(PartnerName); // Ustawiam siebie jako partnera partnera xD
        }
    }


    private String Sex(String name){
        if(name.charAt(name.length()-1) == 'a')return "female";
        else return "male";
    }

}
