package example2;

import java.util.List;

/*
Introducing generics
Parameterize the SomeClass and Solution classes as follows:
1. SomeClass must work with types that inherit Number;
2. Solution must work with types that inherit List, which in turn have SomeClass as a type argument.
E (element), T (type), K (key), and V (value).
*/
public class Solution<T extends List<Solution.SomeClass>> {
    public static class SomeClass <T extends Number>{
    }

    public static void main(String[] args) {

    }
}
