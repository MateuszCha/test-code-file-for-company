package Json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/* 
Deserializing a JSON object
In the convertFromJsonToNormal method, the first parameter is the name of a file that contains one JSON object.
The second parameter is the name of the class of the object serialized in the file.
The convertFromJsonToNormal method should extract the object from the file, convert it from JSON, and return it.

Use the readValue method with (File, Class) or (Reader, Class) parameters.

*/
public class deserializationLoadFromFile {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        T value = mapper.readValue(new File(fileName) ,clazz);
        return value;
    }

    public static void main(String[] args) {

    }
}