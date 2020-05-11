package example3;

import java.util.*;


/* 
Collections & Generics
Implement helper methods in the Solution class that create an appropriate collection and add the passed objects to it.
The newArrayList and newHashSet methods have the T type parameter.
The newHashMap method has K (key) and V (value) type parameters. The newHashMap method must accept lists that contain descendants of K and V.
The returned collections must be of the same type as the objects passed to the method.

Hint: In the newHashMap method, you need to check to make sure that the lists of keys and values are the same size. If they aren't, then throw an IllegalArgumentException.




*/
public class Solution2 {

    public static void main(String[] args) {
    }

    public static <T> ArrayList<T> newArrayList(T... elements) {
        //write your code here
        return new ArrayList<T>(Arrays.asList(elements));
    }

    public static <T> HashSet<T> newHashSet(T... elements) {
        //write your code here
        return new HashSet<T>(Arrays.asList(elements));
    }

    public static <K,V> HashMap<K,V> newHashMap(List<? extends K> keys, List<? extends V> values) {
        HashMap<K,V> hashMap = new HashMap<K,V>();
        if(keys.size()!= values.size())
            throw new IllegalArgumentException();
        for(int i = 0; i < keys.size(); i++){
            hashMap.put(keys.get(i),values.get(i));
        }
        return hashMap;
    }
}
