package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message){
        System.out.println(message);
    }
    public static String readString() {
        String s = null;
        while(s == null)
        {
            try {
                s = reader.readLine();
            } catch (IOException err) {
                System.out.println("An error occurred while trying to enter text. Try again.");
            }
        }
        return s;
    }
    public static int readInt(){
        int number = 0;
        try {
            number = Integer.parseInt(readString());
        }catch(NumberFormatException err){
            System.out.println("An error while trying to enter a number. Try again.");
            number = Integer.parseInt(readString());

        }
        return number;
    }
}
