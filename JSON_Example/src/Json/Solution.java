package Json;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

/* My first serialization into JSON
REQUIRED CONNECTED LIBRARIES: Jackson Core, Bind and Annotation 2.6.1
1) The program does not fulfill the main requirement for serialization into JSON. Find the bug and fix it. 2) Corr
ectly arrange the JSON annotations on the classes. All the data must be serialized.
// Files-> P structure-> libraries "+" all file jar

*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Cat cat = new Cat();
        cat.name = "Missy";
        cat.age = 5;
        cat.weight = 3;

        Dog dog = new Dog();
        dog.name = "Killer";
        dog.age = 8;
        dog.owner = "Bill Jefferson";

        ArrayList<Pet> pets = new ArrayList<Pet>();
        pets.add(cat);
        pets.add(dog);

        StringWriter writer = new StringWriter();
        convertToJSON(writer, pets);
        System.out.println(writer.toString());
        //[{"name":"Missy","age":5,"weight":3},{"name":"Killer","age":8,"owner":"Bill Jefferson"}]
    }

    public static void convertToJSON(StringWriter writer, Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, object);
    }

    @JsonAutoDetect
    public static class Pet {
        @JsonProperty
        String name;
        Pet(){

        }

    }
    @JsonAutoDetect
    public static class Cat extends Pet{
        @JsonProperty
        int age;
        @JsonProperty
        int weight;
        Cat(){

        }
    }
    @JsonAutoDetect
    public static class Dog extends Pet {
        @JsonProperty
        int age;
        @JsonProperty
        String owner;
        Dog(){

        }
    }
}