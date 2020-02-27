package FileOperation;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
Threads and bytes
Read file names from the console until the word "exit" is entered. ok
Pass the file name to the ReadThread thread. ok
The ReadThread thread should find the byte that occurs most frequently in the file, and add it to resultMap,
where the String parameter is the file name and the Integer parameter is the relevant byte.
Close the streams.

 */
public class ThreadsAndCountByte {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader reder = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<ReadThread> list = new ArrayList<ReadThread>(20);
        while (true){
            String s = reder.readLine();
            if(s == null || s.equals("exit"))
                break;
            list.add(new ReadThread(s));
        }
        list.trimToSize();
        for(ReadThread listForOf : list)
            listForOf.start();

        Thread.sleep(10);
        for (ReadThread listForOf : list) {
            ThreadsAndCountByte.resultMap.putAll(listForOf.getMap());
            //System.out.println(Solution.resultMap);
        }
    }



    public static class ReadThread extends Thread {
        private String fileName;
        private  Map<String,Integer> map;

        public ReadThread(String fileName) {
            this.fileName = fileName;
            this.map = new HashMap<String, Integer>();
        }
        @Override
        public  void run(){
            synchronized (this.map)
            {
                Map<Character,Integer> map = new HashMap<Character, Integer>();
                InputStream file;
                try {
                    file = new FileInputStream(this.fileName);
                    int value;
                    while(file.available() > 0)
                    {
                        value = file.read();
                        if(map.containsKey((char)value))
                            map.replace((char)value, map.get((char)value) +1);
                        else
                            map.put((char)value, 1);
                    }
                    file.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                findeValue(map);
            }
        }

        private synchronized void findeValue(Map<Character,Integer> map){
            int max = 0;
            for(Map.Entry<Character,Integer> pairs : map.entrySet()) {
                if(max < pairs.getValue())
                    max = pairs.getValue();
            }
            for(Map.Entry<Character,Integer> pairs : map.entrySet()) {
                if(pairs.getValue().equals(max))
                    this.map.put(this.fileName, (int)pairs.getKey());
            }
            //System.out.println(this.map);
        }

        public  Map<String,Integer> getMap() throws InterruptedException {
            return this.map;
        }
    }
}
