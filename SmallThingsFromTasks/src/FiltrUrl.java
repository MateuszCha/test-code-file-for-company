import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

/*
Request parser
Read a URL from the console. ok
Display a space-delimited list of all the parameters (The parameters follow the "?" and are separated by "&", e.g. "lvl=15"). ok
The URL contains at least 1 parameter. ok
The parameters must be displayed in the same order in which they are present in the URL. ok
If the obj parameter is present, pass its value to the relevant alert method.
alert(double value) - for numbers (fractional numbers have a decimal point)
alert(String value) - for strings
Note that the alert method must be called AFTER the list of all parameters is displayed.

Requirements:
1. The program should read only one line from the keyboard. ok
2. The Solution class must not have static fields. ok
3. The program must display data on the screen in accordance with the task conditions. ok
4. The program should call the alert method with the double parameter if the obj parameter can be correctly converted to a double.
5. The program should call the alert method with the String parameter if the obj parameter CANNOT be correctly converted to a double.
Example 1

Input:
http://codegym.cc/alpha/index.html?lvl=15&view&name=Amigo

Output:
lvl view name

Example 2

Input:
http://codegym.cc/alpha/index.html?obj=3.14&name=Amigo

Output:
obj name
double: 3.14
*/

public class FiltrUrl {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
        String s = "";
        try
        {
            s = reader.readLine();
            reader.close();
        }catch(IOException err)
        {
            System.out.println(err);
        }
        for(int i = 0; i<s.length();i++) //61 = //63 ? //  38 &
        {
            if((int)s.charAt(i) == 63) {
                s = s.substring(i + 1);
                break;
            }
        }
        boolean flag = true;
        String pom1 ="";
        String pom2 ="";
        for(int i = 0; i<s.length();i++)
        {
            if(flag)
            {
                if((int)s.charAt(i) == 61) {
                    flag = false;
                    continue;
                }
                if((int)s.charAt(i) == 38) {
                    map.put(pom1,null); // null or pom2
                    pom1 = "";
                    pom2 = "";
                    continue;
                }
                pom1 += s.charAt(i);
            }else{
                if((int)s.charAt(i) == 38)
                {
                    flag = true;
                    map.put(pom1,pom2);
                    pom1 = "";
                    pom2 = "";
                    continue;
                }
                pom2 += s.charAt(i);
            }
            if(i == s.length()-1) {
                map.put(pom1, pom2);
                pom1 = "";
                pom2 = "";
            }
        }
        for(Map.Entry<String,String> pairs : map.entrySet())
        {
            System.out.print(pairs.getKey() + " ");

        }
        System.out.println();
        for(Map.Entry<String,String> pairs : map.entrySet())
        {
            if(!pairs.getKey().equals("obj"))
                continue;
            try {
                alert(Double.parseDouble(pairs.getValue()));
            }catch (Exception err)
            {
                if(pairs.getValue() == null)
                    continue;
                alert(pairs.getValue());
            }
        }
    }
    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }
}
