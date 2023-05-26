import java.io.File;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class main {

    public static HashMap<String, Person> lud = new HashMap<String, Person>();
    public static HashMap<String, Person> funkcje = new HashMap<String, Person>();


    public static void read_data(){
        //osoba;ojciec;partner
        try{
            Scanner scanner = new Scanner(new File("GeoDrzewo/persons.txt"));
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] splitted = data.split(";");
                if(lud.containsKey(splitted[0])){
                    lud.get(splitted[0]).updatePerson(splitted[1],splitted[2]);
                }

                else lud.put(splitted[0],new Person(splitted[0].split(" ")[0],splitted[0].split(" ")[1],splitted[1],splitted[2]));
            }
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String Relation(String NameAndSurname1, String NameAndSurname2){
        read_data();
        Person person = lud.get(NameAndSurname1);
        if(person.is_wife(NameAndSurname2)) return "zona";
        else if(person.is_Husband(NameAndSurname2)) return "maz";
        else if(person.is_Father(NameAndSurname2)) return "ojciec";
        else if(person.is_Mother(NameAndSurname2)) return "matka";
        else if(person.is_Son(NameAndSurname2)) return "syn";
        else if(person.is_daughter(NameAndSurname2)) return "corka";
        else if(person.is_brother(NameAndSurname2)) return "brat";
        else if(person.is_sister(NameAndSurname2)) return "siostra";
        else if(person.is_GrandFather(NameAndSurname2)) return "dziadek";
        else if(person.is_GrandMother(NameAndSurname2)) return "babcia";
        else if(person.is_GrandSon(NameAndSurname2)) return "wnuk";
        else if(person.is_GrandDaughter(NameAndSurname2)) return "wnuczka";
        else if(person.is_FatherInLaw(NameAndSurname2)) return "tesc";
        else if(person.is_MotherInLaw(NameAndSurname2)) return "tesciowa";
        else if(person.is_SisterInLaw(NameAndSurname2)) return "bratowa";
        else if(person.is_Nephew_fromBrother(NameAndSurname2)) return "bratanek";
        else if(person.is_Niece_fromBrother(NameAndSurname2)) return "bratanica";
        else if(person.is_Nephew_fromSister(NameAndSurname2)) return "siostrzeniec";
        else if(person.is_Niece_fromSister(NameAndSurname2)) return "siostrzenica";
        else if(person.is_DaughterInLaw(NameAndSurname2)) return "synowa";
        else if(person.is_SonInLaw(NameAndSurname2)) return "ziec";
        else {
            return "cos ci nie wyszlo";
        }
    }

    @Test
    public void zona1() {
        String result = Relation("Mariola Markowska","Henryk Markowski");
        assertEquals("zona", result);
    }

    @Test
    public void maz1() {
        String result = Relation("Krzysztof Nosarzewski","Agata Nosarzewska");
        assertEquals("maz", result);
    }

    @Test
    public void corka2() {
        String result = Relation("Oliwia Sobel","Emilia Sobel");
        assertEquals("corka", result);
    }

    @Test
    public void syn1() {
        String result = Relation("Wojciech Sobel","Rafal Sobel");
        assertEquals("syn", result);
    }

    @Test
    public void matka1() {
        String result = Relation("Emilia Markowska","Ryszard Markowski");
        assertEquals("matka", result);
    }

    @Test
    public void corka3() {
        String result = Relation("Halina Markowska","Czeslaw Markowski");
        assertEquals("corka", result);
    }

    @Test
    public void test8() {
        String result = Relation("Halina Markowska","Ryszard Markowski");
        assertEquals("siostra", result);
    }

    @Test
    public void test9() {
        String result = Relation("Pawel Markowski","Joanna Markowska");
        assertEquals("brat", result);
    }

    @Test
    public void wnuk1() {
        String result = Relation("Pawel Markowski","Bogdan Waza");
        assertEquals("wnuk", result);
    }

    @Test
    public void wnuk2() {
        String result = Relation("Pawel Markowski","Ryszarda Waza");
        assertEquals("wnuk", result);
    }

    @Test
    public void wnuk3() {
        String result = Relation("Pawel Markowski","Czeslaw Markowski");
        assertEquals("wnuk", result);
    }

    @Test
    public void wnuk4() {
        String result = Relation("Pawel Markowski","Emilia Markowska");
        assertEquals("wnuk", result);
    }

    @Test
    public void wnuczka1() {
        String result = Relation("Joanna Markowska","Bogdan Waza");
        assertEquals("wnuczka", result);
    }

    @Test
    public void wnuczka2() {
        String result = Relation("Joanna Markowska","Ryszarda Waza");
        assertEquals("wnuczka", result);
    }

    @Test
    public void wnuczka3() {
        String result = Relation("Joanna Markowska","Czeslaw Markowski");
        assertEquals("wnuczka", result);
    }
    @Test
    public void wnuczka4() {
        String result = Relation("Joanna Markowska","Emilia Markowska");
        assertEquals("wnuczka", result);
    }
    @Test
    public void test18() {
        String result = Relation("Krzysztof Nosarzewski","Oliwia Sobel");
        assertEquals("dziadek", result);
    }
    @Test
    public void test19() {
        String result = Relation("Agata Nosarzewska","Zuzanna Sobel");
        assertEquals("babcia", result);
    }
    @Test
    public void test20() {
        String result = Relation("Mariola Markowska","Czeslaw Markowski");
        assertEquals("synowa", result);
    }
    @Test
    public void test21() {
        String result = Relation("Henryk Markowski","Ryszarda Waza");
        assertEquals("ziec", result);
    }
    @Test
    public void test22() {
        String result = Relation("Krzysztof Nosarzewski","Bogdan Waza");
        assertEquals("ziec", result);
    }
    @Test
    public void test23() {
        String result = Relation("Czeslaw Markowski","Mariola Markowska");
        assertEquals("tesc", result);
    }
    @Test
    public void test24() {
        String result = Relation("Ryszarda Waza","Henryk Markowski");
        assertEquals("tesciowa", result);
    }
    @Test
    public void test25() {
        String result = Relation("Bogdan Waza","Krzysztof Nosarzewski");
        assertEquals("tesc", result);
    }
    @Test
    public void test26() {
        String result = Relation("Tomasz Markowski","Halina Markowska");
        assertEquals("bratanek", result);
    }
    @Test
    public void test27() {
        String result = Relation("Tomasz Markowski","Ryszard Markowski");
        assertEquals("bratanek", result);
    }
    @Test
    public void test28() {
        String result = Relation("Joanna Markowska","Halina Markowska");
        assertEquals("bratanica", result);
    }
    @Test
    public void test29() {
        String result = Relation("Joanna Markowska","Ryszard Markowski");
        assertEquals("bratanica", result);
    }
    @Test
    public void test30() {
        String result = Relation("Joanna Markowska","Agata Nosarzewska");
        assertEquals("siostrzenica", result);
    }
    @Test
    public void test31() {
        String result = Relation("Tomasz Markowski","Agata Nosarzewska");
        assertEquals("siostrzeniec", result);
    }
    @Test
    public void test32() {
        String result = Relation("Oliwia Sobel","Magda Sobel");
        assertEquals("siostrzenica", result);
    }
    @Test
    public void test33() {
        String result = Relation("Zuzanna Sobel","Emilia Sobel");
        assertEquals("siostrzenica", result);
    }
    @Test
    public void test34() {
        String result = Relation("Wojciech Sobel","Emilia Sobel");
        assertEquals("siostrzeniec", result);
    }
    @Test
    public void test35() {
        String result = Relation("Wojciech Sobel","Mateusz Nosarzewski");
        assertEquals("siostrzeniec", result);
    }
    @Test
    public void test36() {
        String result = Relation("Jozefa Szal","Emilia Markowska");
        assertEquals("tesciowa", result);
    }
    @Test
    public void test37() {
        String result = Relation("Emilia Markowska","Jozefa Szal");
        assertEquals("synowa", result);
    }
    @Test
    public void test38() {
        String result = Relation("Mariola Markowska","Halina Markowska");
        assertEquals("bratowa", result);
    }
    @Test
    public void test39() {
        String result = Relation("Mariola Markowska","Ryszard Markowski");
        assertEquals("bratowa", result);
    }
    @Test
    public void test40() {
        String result = Relation("Pawel Markowski","Ryszard Markowski");
        assertEquals("bratanek", result);
    }
    @Test
    public void test41() {
        String result = Relation("Bronislawa Markowska","Halina Markowska");
        assertEquals("babcia", result);
    }
    @Test
    public void test42() {
        String result = Relation("Feliks Szal","Ryszard Markowski");
        assertEquals("dziadek", result);
    }
    @Test
    public void test43() {
        String result = Relation("Roman Waza","Agata Nosarzewska");
        assertEquals("dziadek", result);
    }







}
