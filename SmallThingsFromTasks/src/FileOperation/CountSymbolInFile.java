package FileOperation;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CountSymbolInFile {
    public static void main(String[] args) throws IOException {
        InputStream file = new FileInputStream(args[0]);
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        while (file.available() > 0)
        {
            int value = file.read();
            if(map.containsKey((char)value))
                map.replace((char)value , map.get((char)value)+1);
            else
                map.put((char)value , 1);
        }
        file.close();
        Map<Character,Integer> mapSort = new TreeMap<Character,Integer>(map);

        for(Map.Entry<Character,Integer> pairs : mapSort.entrySet())
            System.out.println(pairs.getKey() + " " + pairs.getValue());

    }
}