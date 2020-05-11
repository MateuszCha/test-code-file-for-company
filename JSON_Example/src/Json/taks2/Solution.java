package Json.taks2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/* 
Serializing a zoo
Make it so that a type is added to each element of the animals list when you serialize a Zoo object (dog for the Dog class, cat for the Cat class).
To check, you can use the Solution class's main method.

Before:
{"animals":[{"name":"doggy","barkVolume":0.0},{"name":"catty","lives":0}]}

After:
{"animals":[{"type":"dog","name":"doggy","barkVolume":0.0},{"type":"cat","name":"catty","lives":0}]}



*/
public class Solution {
    public static void main(String[] args) throws JsonProcessingException {
        Zoo.Dog dog = new Zoo.Dog("doggy");
        Zoo.Cat cat = new Zoo.Cat("catty");
        Zoo zoo = new Zoo();
        zoo.animals.add(dog);
        zoo.animals.add(cat);

        String result = new ObjectMapper().writeValueAsString(zoo);

        System.out.println(result);
    }
}