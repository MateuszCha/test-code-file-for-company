import java.util.HashSet;
import java.util.Set;

/* 
Palindrome?
Declare and implement the private static Set<Integer> getRadix(String number), getRadix(String number) method, which needs to determine the number systems (from 2 to 36 inclusive) in which the representation of number (passed in the decimal representation) is a palindrome and add the indices of these systems to the return value.
If the passed number is invalid, return an empty HashSet.
Number systems whose base is greater than 10 use Latin letters as numerals. For example, the number 35 in the decimal system corresponds to the number "Z" in the system with a base of 36.

The main method is not tested.

P.S.: Catch a NumberFormatException in the getRadix method. Don't display the stack trace.

*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        // Expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        // Expected output: [6]
        System.out.println(getRadix("5321"));       // Expected output: []
        System.out.println(getRadix("1A"));         // Expected output: []
    }
    private static Set<Integer> getRadix(String number){
        Set<Integer> set = new HashSet<>();
        try {
            for (int i = 2; i <= 36; i++)
            {
                String str = Integer.toString(Integer.parseInt(number), i);
                boolean flag = true;
                for (int j = 0; j < str.length() / 2 + 1; j++)
                {
                    if (str.charAt(j) != str.charAt(str.length() - 1 - j)) {
                        flag = false;
                        break;
                    }
                }
                if(flag)
                    set.add(i);
            }
        }catch (NumberFormatException err){}
        return set;
    }
}