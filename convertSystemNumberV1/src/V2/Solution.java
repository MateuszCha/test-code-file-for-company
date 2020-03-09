package V2;

import java.math.BigInteger;

/*
Numeral system converter

*/
public class Solution {
    public static void main(String[] args) {
        Number number = new Number(NumberSystemType._10, "6");
        Number result = convertNumberToOtherNumberSystem(number, NumberSystemType._2);
        System.out.println(result);    // 110 expected

        Number number1 = new Number(NumberSystemType._10, "6");
        Number result1 = convertNumberToOtherNumberSystem(number1, NumberSystemType._8);
        System.out.println(result1);    // 6 expected

        Number number2 = new Number(NumberSystemType._10, "6");
        Number result2 = convertNumberToOtherNumberSystem(number2, NumberSystemType._10);
        System.out.println(result2);    // 6 expected

        Number number3 = new Number(NumberSystemType._10, "6");
        Number result3 = convertNumberToOtherNumberSystem(number3, NumberSystemType._16);
        System.out.println(result3);    // 6 expected

        number = new Number(NumberSystemType._16, "6df");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._8);
        System.out.println(result);    // 3337 expected

        number = new Number(NumberSystemType._16, "6df");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._2);
        System.out.println(result);    // 11011011111 expected

        number = new Number(NumberSystemType._16, "6df");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._10);
        System.out.println(result);    // 3337 expected

        number = new Number(NumberSystemType._16, "6df");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._16);
        System.out.println(result);    // 3337 expected

        number = new Number(NumberSystemType._16, "abcdefabcdef");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._16);
        System.out.println(result);    // abcdefabcdef expected

        number = new Number(NumberSystemType._16, "abcdefabcdef");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._2);
        System.out.println(result);    // 101010111100110111101111101010111100110111101111 expected

        number = new Number(NumberSystemType._16, "abcdefabcdef");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._8);
        System.out.println(result);    // 5274675752746757 expected

        number = new Number(NumberSystemType._16, "abcdefabcdef");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._10);
        System.out.println(result);    // 188900977659375 expected
    }

    public static Number convertNumberToOtherNumberSystem(Number number, NumberSystem expectedNumberSystem) {
        String str = number.getDigit();
        boolean isPositive = true;
        if(str.charAt(0) == '-'){
            str = str.substring(1);
            isPositive = false;
        } // if size > 0
        str = str.toLowerCase();
        char tab[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        short radix = (short) expectedNumberSystem.getNumberSystemIntValue();
        BigInteger value = new BigInteger(str, number.getNumberSystem().getNumberSystemIntValue());
        if(isPositive)   str = "";
        else str = "-";
        str += value.toString(expectedNumberSystem.getNumberSystemIntValue());

        Number numberAfterConversion = new Number(expectedNumberSystem,str);
        return numberAfterConversion;
    }

}