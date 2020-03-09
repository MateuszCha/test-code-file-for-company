package V1;

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
       Number numberAfterConversion = null;
        long value;
        switch (expectedNumberSystem.getNumberSystemIntValue()){
            case 2:
                 value = convertNumberToDigital(number);
                numberAfterConversion = convertNumberToBinary(value);
                break;
            case 8:
                value = convertNumberToDigital(number);
                numberAfterConversion = convertNumberToOctal(value);
                break;
            case 10:
                value = convertNumberToDigital(number);
                numberAfterConversion = new Number(NumberSystemType._10,String.valueOf(value));
                break;
            case 16:
                value = convertNumberToDigital(number);
                numberAfterConversion = convertNumberToHex(value);
                break;
            default: throw new NumberFormatException();
        }
        return numberAfterConversion;
    }
    private static long convertNumberToDigital(Number number)
    {
        long result = 0;
        String strPom = number.getDigit().toUpperCase();
        if(strPom.charAt(0) == '-')
            strPom = strPom.substring(1);
        switch (number.getNumberSystem().getNumberSystemIntValue())
        {
            case 2:
                long binaryMultiplication = 1;
                for(int i = strPom.length()-1; i >=0; i--){
                    if(strPom.charAt(i) != '1' && strPom.charAt(i) != '0' ) throw new NumberFormatException();
                    if(strPom.charAt(i) == '1'){
                        result += binaryMultiplication;
                    }
                    binaryMultiplication *= 2;
                }
                break;
            case 8:
                long octalMultiplication = 1;
                for(int i = strPom.length()-1; i >=0; i--){
                    if((int)strPom.charAt(i) < 48 && (int)strPom.charAt(i) >= 56) throw new NumberFormatException();
                    result += ((int)strPom.charAt(i)-48) * octalMultiplication ;
                    octalMultiplication *= 8;
                }
                break;
            case 10:
                for(int i = strPom.length()-1; i >=0; i--)
                    if((int)strPom.charAt(i) < 48 && (int)strPom.charAt(i) > 57) throw new NumberFormatException();
                result = Integer.parseInt(number.getDigit());
                break;
            case  16:
                long hexMultiplication = 1;
                for(int i = strPom.length()-1; i >=0; i--) {
                    if ((int) strPom.charAt(i) >= 48 && (int) strPom.charAt(i) <= 57) {
                        result += ((int) strPom.charAt(i) - 48) * hexMultiplication;
                    } else {
                        if (((int) strPom.charAt(i) >= 65 && (int) strPom.charAt(i) <= 70))
                            result += ((int) strPom.charAt(i) - 55) * hexMultiplication;
                        else throw new NumberFormatException();
                    }
                    hexMultiplication *= 16;
                }
        }
        return result;
    }
    private static Number convertNumberToBinary(long numberDigital){
        StringBuilder str = new StringBuilder();
        while(numberDigital > 0){
            if(numberDigital % 2 == 0) str.append('0');
            else str.append('1');
            numberDigital /= 2;
        }
        return new Number(NumberSystemType._2,str.reverse().toString());
    }
    private static Number convertNumberToOctal(long numberDigital){
        StringBuilder str = new StringBuilder();
        while(numberDigital > 0){
            if(numberDigital < 8){
                str.append(numberDigital);
                break;
            }
            str.append(numberDigital%8);
            numberDigital /= 8;
        }
        return new Number(NumberSystemType._8,str.reverse().toString());
    }
    private static Number convertNumberToHex(long numberDigital){
        StringBuilder str = new StringBuilder();
        while(numberDigital > 0){
            if(numberDigital < 16){
                if(numberDigital >= 10) str.append((char)(numberDigital+55));
                else str.append(numberDigital);
                break;
            }
            short valuePom = (short) (numberDigital % 16);
            if(valuePom >= 10) str.append((char)(valuePom+55));
            else str.append(valuePom);
            numberDigital /= 16;
        }
        return new Number(NumberSystemType._16,str.reverse().toString().toLowerCase());
    }
}