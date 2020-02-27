package FileOperation;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FindDateInFile {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        InputStream file = new FileInputStream(reader.readLine());
        Map<Integer,String> map = new HashMap<Integer,String>();
        reader.close();

        int id = Integer.parseInt(args[0]);
        byte[] buffer = new byte[file.available()];
        file.read(buffer);
        /*
        String s = "";
        int index = 0;
        boolean flag = true;
        for(int i = 0; i<buffer.length; i++)
        {
            if(flag) {
                if((int)buffer[i] == 32) {
                    index = Integer.parseInt(s);
                    flag = false;
                }
            }
            if((int)buffer[i] == 13 || (int)buffer[i] == 10) {
                map.put(index, s);
                s = "";
                flag = true;
                continue;
            }
            s += buffer[i];
        }
        System.out.println(map.get(id));
        */
        String s = "";
        String search = "";
        boolean flag = false;
        boolean flagPom = true;
        for(int i = 0; i<buffer.length; i++)
        {
            if((int)buffer[i] == 32 && flagPom)
            {
                if(Integer.parseInt(s) == id)
                    flag = true;
                flagPom = false;
                continue;
            }
            if((int)buffer[i] == 10 || (int)buffer[i] == 13) {
                flag = false;
                flagPom = true;
                s = "";
                continue;
            }
            if(flag){
                search += (char)((int)buffer[i]); // char buffer[i];
            }
            s += (char)((int)buffer[i]);
        }
        System.out.println(id + " " + search);
        file.close();
    }
}