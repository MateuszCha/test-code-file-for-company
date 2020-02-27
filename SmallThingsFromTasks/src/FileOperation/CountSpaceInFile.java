package FileOperation;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
/*
Spaces
The first parameter of the main method is a file name.
Display the ratio of the number of spaces to the number of all characters. For example, 10.45.
1. Count all the characters (n1).
2. Count the spaces (n2).
3. Display n2/n1*100, rounding to 2 decimal places.
4. Close the streams.
*/
public class CountSpaceInFile {
    public static void main(String[] args) throws IOException {
        InputStream inFile = new FileInputStream(args[0]);

        int allCharacter = 0;
        int spaceBar = 0;
        while(inFile.available() > 0 )
        {
            int value = inFile.read();
            // if((value >= 65 && value <= 90 ) || (value >= 97 && value <= 122))
            allCharacter++;
            if(value == 32)
                spaceBar++;
        }
        inFile.close();
        System.out.printf("%.2f",(double)spaceBar/allCharacter*100);
    }
}