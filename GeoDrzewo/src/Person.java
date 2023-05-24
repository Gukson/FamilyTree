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

    public boolean is_wife(String NameAndSurname){//Czy jest żoną osoby podanej w nawiasach?

        return this.Spouse!=null && (this.Spouse.Name + " " + this.Spouse.Surname).equals(NameAndSurname) && this.sex.equals("female");
    }

    public boolean is_Husband(String NameAndSurname){//Czy jest mężem osoby podanej w nawiasach?
        return this.Spouse!=null && (this.Spouse.Name + " " + this.Spouse.Surname).equals(NameAndSurname) && this.sex.equals("male");
    }

    public boolean is_Father(String NameAndSurname){//Czy jest ojcem osoby podanej w nawiasach?
        return main.lud.get(NameAndSurname).Father != null && (main.lud.get(NameAndSurname).Father).equals(this);
    }

    public boolean is_Mother(String NameAndSurname){//Czy jest matką osoby podanej w nawiasach?
        return main.lud.get(NameAndSurname).Father!=null && main.lud.get(NameAndSurname).Father.Spouse!=null && (main.lud.get(NameAndSurname).Father.Spouse).equals(this) && this.sex.equals("female");
    }

    public boolean is_Son(String NameAndSurname){//Czy jest synem osoby podanej w nawiasach?
        return ((this.Father !=null && this.Father.equals(main.lud.get(NameAndSurname))) || (this.Father !=null && this.Father.Spouse!=null) && this.Father.Spouse.equals(main.lud.get(NameAndSurname)))&&this.sex.equals("male");
    }

    public boolean is_daughter(String NameAndSurname){//Czy jest córką osoby podanej w nawiasach?
        return (this.Father !=null &&  (this.Father.equals(main.lud.get(NameAndSurname)) || (this.Father.Spouse!=null && this.Father.Spouse.equals(main.lud.get(NameAndSurname)))))&&this.sex.equals("female");
    }

    public boolean is_brother(String NameAndSurname){//Czy jest bratem osoby podanej w nawiasach?
        return main.lud.get(NameAndSurname).Father != null && (main.lud.get(NameAndSurname).Father.equals(this.Father) && this.sex.equals("male"));
    }

    public boolean is_sister(String NameAndSurname){//Czy jest córką osoby podanej w nawiasach?
        return main.lud.get(NameAndSurname).Father != null && (main.lud.get(NameAndSurname).Father.equals(this.Father) && this.sex.equals("female"));
    }

    public boolean is_GrandFather(String NameAndSurname){//Czy jest dziadkiem osoby podanej w nawiasach?
        Person person = main.lud.get(NameAndSurname);
        Person father = person.Father;
        if (father == null){
            return false;
        }
        Person mother = father.Spouse;

        return (father.Father != null && (father.Father).equals(this))||
                ((mother != null && mother.Father != null && (mother.Father).equals(this)));
    }

    public boolean is_GrandMother(String NameAndSurname){//Czy jest babcią osoby podanej w nawiasach?
        Person person = main.lud.get(NameAndSurname);
        Person father = person.Father;
        if(father == null){
            return false;
        }
        return (father.Father != null && (father.Father.Spouse).equals(this))||
                ((father.Spouse != null && father.Spouse.Father != null && (father.Spouse.Father.Spouse).equals(this)));
    }

    public boolean is_GrandSon(String NameAndSurname){//Czy jest wnukiem osoby podanej w nawiasach?


        return (this.Father != null && this.Father.Father!=null && this.sex.equals("male") && this.Father.Father.equals(main.lud.get(NameAndSurname))) ||
                (this.Father != null && this.Father.Father!=null && this.Father.Father.Spouse!=null && this.sex.equals("male") && this.Father.Father.Spouse.equals(main.lud.get(NameAndSurname))) ||
                        (this.Father != null && this.Father.Spouse!=null && this.Father.Spouse.Father!=null  && this.sex.equals("male") &&
                this.Father.Spouse.Father.equals(main.lud.get(NameAndSurname)) )||
                        (this.Father != null && this.Father.Spouse!=null && this.Father.Spouse.Father!=null && this.Father.Spouse.Father.Spouse!=null  && this.sex.equals("male") &&
                this.Father.Spouse.Father.Spouse.equals(main.lud.get(NameAndSurname)));
    }

    public boolean is_GrandDaughter(String NameAndSurname){//Czy jest wnuczką osoby podanej w nawiasach?

        return (this.Father != null && this.Father.Father!=null && this.sex.equals("female") && this.Father.Father.equals(main.lud.get(NameAndSurname))) ||
                (this.Father != null && this.Father.Father!=null && this.Father.Father.Spouse!=null && this.sex.equals("female") && this.Father.Father.Spouse.equals(main.lud.get(NameAndSurname))) ||
                (this.Father != null && this.Father.Spouse!=null && this.Father.Spouse.Father!=null  && this.sex.equals("female") &&
                        this.Father.Spouse.Father.equals(main.lud.get(NameAndSurname)) )||
                (this.Father != null && this.Father.Spouse!=null && this.Father.Spouse.Father!=null && this.Father.Spouse.Father.Spouse!=null  && this.sex.equals("female") &&
                        this.Father.Spouse.Father.Spouse.equals(main.lud.get(NameAndSurname)));
    }

    public boolean is_FatherInLaw(String NameAndSurname){//Czy jest teściem osoby podanej w nawiasach?
        return  main.lud.get(NameAndSurname).Spouse != null && main.lud.get(NameAndSurname).Spouse.Father !=null &&
                main.lud.get(NameAndSurname).Spouse.Father.equals(this);
    }

    public boolean is_MotherInLaw(String NameAndSurname){//Czy jest teściową osoby podanej w nawiasach?
        return main.lud.get(NameAndSurname).Spouse != null && main.lud.get(NameAndSurname).Spouse.Father != null && main.lud.get(NameAndSurname).Spouse.Father.Spouse!=null &&
                main.lud.get(NameAndSurname).Spouse.Father.Spouse.equals(this);
    }

    public boolean is_SisterInLaw(String NameAndSurname){//Czy jest bratową osoby podanej w nawiasach?
        return  this.Father!=null && main.lud.get(NameAndSurname).sex.equals("female") && main.lud.get(NameAndSurname).Spouse != null && main.lud.get(NameAndSurname).Spouse.Father!=null && main.lud.get(NameAndSurname).Spouse.Father.Father!=null&&
                this.Father.equals(main.lud.get(NameAndSurname).Spouse.Father.Father);

    }

    public boolean is_Nephew_fromBrother(String NameAndSurname){//Czy jest bratankiem osoby podanej w nawiasach?
        //czy ojciec osoby w nawiasach jest 
        return this.Father!=null && main.lud.get(NameAndSurname).sex.equals("male") && main.lud.get(NameAndSurname).Father!=null && main.lud.get(NameAndSurname).Father.Father !=null &&
                this.Father.equals(main.lud.get(NameAndSurname).Father.Father);
    }

    public boolean is_Niece_fromBrother(String NameAndSurname){//Czy jest bratanicą osoby podanej w nawiasach?
        return this.Father!=null && main.lud.get(NameAndSurname).sex.equals("female") && main.lud.get(NameAndSurname).Father!=null && main.lud.get(NameAndSurname).Father.Father !=null &&
                this.Father.equals(main.lud.get(NameAndSurname).Father.Father);
    }

    public boolean is_Nephew_fromSister(String NameAndSurname){//Czy jest Siostrzeńcem osoby podanej w nawiasach?
        return  this.Father != null && main.lud.get(NameAndSurname).Father != null && main.lud.get(NameAndSurname).Father.Spouse !=null && main.lud.get(NameAndSurname).Father.Spouse.Father!=null && main.lud.get(NameAndSurname).sex.equals("male") &&
                this.Father.equals(main.lud.get(NameAndSurname).Father.Spouse.Father);
    }

    public boolean is_Niece_fromSister(String NameAndSurname){//Czy jest siostrzenicą osoby podanej w nawiasach?
        return  this.Father != null && main.lud.get(NameAndSurname).Father != null && main.lud.get(NameAndSurname).Father.Spouse !=null && main.lud.get(NameAndSurname).Father.Spouse.Father!=null && main.lud.get(NameAndSurname).sex.equals("female") &&
                this.Father.equals(main.lud.get(NameAndSurname).Father.Spouse.Father);
    }


    public boolean is_DaughterInLaw(String NameAndSurname){//Czy jest Synową osoby podanej w nawiasach?
        Person partner = this.Spouse;
        return partner != null && partner.is_Son(NameAndSurname) && this.sex.equals("female");
    }

    public boolean is_SonInLaw(String NameAndSurname){//Czy jest Zięciem osoby podanej w nawiasach?
        Person partner = this.Spouse;
        return partner != null && partner.is_daughter(NameAndSurname) && this.sex.equals("male");
    }


}
