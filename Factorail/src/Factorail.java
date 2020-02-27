import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
Factorial
Write a method that calculates the factorial, i.e. the product of all numbers from 1 up to and including the entered number.

1. Enter a number less than or equal to 150 from the console.
2. Implement the factorial method.
*/
public class Factorail
{
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(reader.readLine());
        reader.close();
        System.out.println(factorial(input));
    }

    public static String factorial(int n) {
        if(n < 0)
            return "0";
        String s = "1";
        for(int i = 1; i <= n; i++)
            s = product(s, String.valueOf(i));
        return s;
    }


    public static String product(String s1, String s2) { // if I need more perform on this method, I have to pass two arguments "arrayOfChar" as the argument of method
        for(int i = 0; i<s1.length(); i++) { // method works with only to positiv value
            if(!((int)s1.charAt(i)  >= 48 && (int)s1.charAt(i) < 58))
                return "first value is not a poitive :( M";
        }
        for(int i = 0; i<s2.length(); i++) { // method works only to positiv value
            if(!((int)s2.charAt(i)  >= 48 && (int)s2.charAt(i) < 58))
                return "second value is not a poitive :( M";
        }
        //51090942171709440000
        //          815915283247 8 97 734345611269596115894272000000000
        //                   247 8 97 734345611269596115894272000000000
        //                          8   0      48
        //    2 32
        // moje //33452526613163 7 07 108170062053440751665152000000000
        //        33452526613163 8 07 108170062053440751665152000000000
        char[] tab = new char[(s1.length()+s2.length())];
        for(int i = 0 ; i < tab.length; i++) // set all char int array equals 0;
            tab[i] = '0';

        String result = ""; // return value 1
        String pomValue = "";
        for(int i = 0; i<s2.length();i++)
        {
            for(int j = 0; j<s1.length(); j++)
            {
                pomValue = String.valueOf(((int)s2.charAt(s2.length()-(i+1)) - 48) * ((int)s1.charAt(s1.length()-(j+1)) - 48));
                if(pomValue.length() == 1)
                {
                    byte shortValue = (byte)(((int)tab[j+i]-48) + ((int)pomValue.charAt(0)-48));
                    if(shortValue < 10)
                        tab[j+i] = (char)(shortValue + 48);
                    else {
                        tab[j+i] = (char) ((shortValue - ((shortValue/10)*10))+48);
                        tab[j+1+i] = (char) ((int)tab[j+1+i] + (shortValue/10));
                    }
                }
                else
                {
                    byte shortValue = (byte)(((int)tab[j+i]-48) + ((int)pomValue.charAt(1)-48));
                    if(shortValue < 10)
                        tab[j+i] = (char)(shortValue + 48);
                    else {
                        tab[j+i] = (char) ((shortValue - ((shortValue/10)*10))+48);
                        tab[j+1+i] = (char) ((int)tab[j+1+i] + (shortValue/10));
                    }
                    tab[j+1+i] = (char)((int)pomValue.charAt(0) + ((int)tab[j+1+i] - 48));
                }
            }
        }
        boolean flag = true;
        for(int i = tab.length-1; i >= 0; i--)
        {
            if (flag)
            {
                if ((int)tab[i] > 48 && (int)tab[i] < 58) {
                    flag = false;
                    result += tab[i];
                }
                else continue;
            } else { result += tab[i]; }
        }
        if(result.isEmpty())
            result = "0";
        return result;
    }
}





