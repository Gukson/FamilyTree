public class Relation {

    public void createRelations(){

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
        Person father =  main.lud.get(NameAndSurname);
        if (this.Father == null || this.Father.Spouse == null) return false;

        return ((this.Father.equals(father)) || (this.Father.Spouse.equals(father))) &&this.sex.equals("male");
    }

    public boolean is_daughter(String NameAndSurname){//Czy jest córką osoby podanej w nawiasach?
        Person father =  main.lud.get(NameAndSurname);
        if (this.Father == null || this.Father.Spouse == null) return false;

        return ((this.Father.equals(father)) || (this.Father.Spouse.equals(father))) &&this.sex.equals("female");
    }

    public boolean is_brother(String NameAndSurname){//Czy jest bratem osoby podanej w nawiasach?
        Person osoba = main.lud.get(NameAndSurname);
        if(osoba.Father == null) return false;
        return osoba.Father.equals(this.Father) && this.sex.equals("male");
    }

    public boolean is_sister(String NameAndSurname){//Czy jest siostrą osoby podanej w nawiasach?
        Person osoba = main.lud.get(NameAndSurname);
        if(osoba.Father == null) return false;
        return osoba.Father.equals(this.Father) && this.sex.equals("female");
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
        Person person = main.lud.get(NameAndSurname);
        return (person.is_GrandFather(this.Name + " " + this.Surname) ||person.is_GrandMother(this.Name + " " + this.Surname)) && this.sex.equals("male");
    }

    public boolean is_GrandDaughter(String NameAndSurname){//Czy jest wnuczką osoby podanej w nawiasach?
        Person person = main.lud.get(NameAndSurname);
        return (person.is_GrandFather(this.Name + " " + this.Surname) ||person.is_GrandMother(this.Name + " " + this.Surname)) && this.sex.equals("female");
    }

    public boolean is_FatherInLaw(String NameAndSurname){//Czy jest teściem osoby podanej w nawiasach?
        Person person = main.lud.get(NameAndSurname);
        if(person.Spouse == null || person.Spouse.Father ==null) return false;
        return person.Spouse.Father.equals(this);
    }

    public boolean is_MotherInLaw(String NameAndSurname){//Czy jest teściową osoby podanej w nawiasach?
        Person person = main.lud.get(NameAndSurname);
        if(person.Spouse == null || person.Spouse.Father ==null || person.Spouse.Father.Spouse == null) return false;
        return person.Spouse.Father.Spouse.equals(this);
    }

    public boolean is_SisterInLaw(String NameAndSurname){//Czy jest bratową osoby podanej w nawiasach?
        if(this.Spouse == null || this.sex.equals("male")) return false;
        return this.Spouse.is_brother(NameAndSurname);
    }

    public boolean is_Nephew_fromBrother(String NameAndSurname){//Czy jest bratankiem osoby podanej w nawiasach?
        if(this.Father == null) return false;
        return this.Father.is_brother(NameAndSurname) && this.sex.equals("male");

    }

    public boolean is_Niece_fromBrother(String NameAndSurname){//Czy jest bratanicą osoby podanej w nawiasach?
        if(this.Father == null) return false;
        return this.Father.is_brother(NameAndSurname) && this.sex.equals("female");
    }

    public boolean is_Nephew_fromSister(String NameAndSurname){//Czy jest Siostrzeńcem osoby podanej w nawiasach?
        if(this.Father == null || this.Father.Spouse == null) return false;
        return this.Father.Spouse.is_sister(NameAndSurname) && this.sex.equals("male");
    }

    public boolean is_Niece_fromSister(String NameAndSurname){//Czy jest siostrzenicą osoby podanej w nawiasach?
        if(this.Father == null || this.Father.Spouse == null) return false;
        return this.Father.Spouse.is_sister(NameAndSurname) && this.sex.equals("female");
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
