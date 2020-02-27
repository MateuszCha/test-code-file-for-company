package FileOperation;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/*
Building a file
Let's build a file from various pieces.
Read file names from the console.
Each file has a name: <someName>.partN.

For example, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.

The file names are supplied in random order. The word "end" is used to stop reading in file names.
In the folder where all the files are located, create a file without the "part" suffix, i.e. without ".<partN>".

For example, Lion.avi.

Use a buffer to copy all the bytes from the partial files to the created file.
Copy the first in the proper order, first the first part, then the second, ..., finally - the last part.
Close the streams.

 */



public class MergeOfFile {
    public static void main(String[] args) throws IOException {
        BufferedReader reder = new BufferedReader(new InputStreamReader(System.in));
        OutputStream outFile = null; //new FileOutputStream("nazwa");
        InputStream inFile;
        ArrayList<String> list = new ArrayList<String>(20);

        while(true)
        {
            String s = reder.readLine();
            if(s.equals("end"))
                break;
            list.add(s);
        }

        list.trimToSize();
        list.sort(null);
        int value = list.get(0).length();
        for(int i = value-1; i >= 0 ; i--) {
            if((int)list.get(0).charAt(i) == 46) {
                //System.out.println(list.get(0).substring(0,i));
                outFile = new FileOutputStream(list.get(0).substring(0,i));
                break;
            }   }

        for(String fileName : list)
        {
            inFile = new FileInputStream(fileName);
            byte[] buffer = new byte[inFile.available()];
            int cont = inFile.read(buffer);
            outFile.write(buffer,0,cont);
            inFile.close();
        }
        outFile.close();
    }
}