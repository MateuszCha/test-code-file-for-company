package exampl1;
/* 
Calling a static method
Modify the GenericStatic class's static method to make it generic.
An example method call is provided in the main method.

E (element), T (type), K (key), and V (value).
*/
public class Solution {
    public static void main(String[] args) {
        Number number = StaticGeneric.<Number>someStaticMethod(new Integer(3));
    }
}