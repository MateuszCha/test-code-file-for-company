package example3;

import java.util.HashMap;
import java.util.LinkedHashMap;

/* 
Simple generics
Parameterize the Solution class so that it can work with all classes that inherit HashMap.
The getMap method must return an object of the map field's type.

*/
public class Solution<T extends HashMap>{
    private T map;

    public Solution(T map) {
        this.map = map;
    }

    public T getMap() {
        return map;
    }

    public static void main(String[] args) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("string", 4);
        Solution solution = new Solution(hashMap);
        HashMap mapFromSolution = solution.getMap();
        System.out.println(mapFromSolution.getClass());


        LinkedHashMap<Solution, Solution> hashMap2 = new LinkedHashMap<>();
        hashMap2.put(solution, solution);
        Solution solution2 = new Solution(hashMap2);
        LinkedHashMap mapFromSolution2 = (LinkedHashMap)solution2.getMap();   // Need to cast  :(
        System.out.println(mapFromSolution2.getClass());
    }
}