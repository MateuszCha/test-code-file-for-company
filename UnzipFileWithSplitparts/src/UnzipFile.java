import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
Unzipping a file
The main method accepts a list of arguments.
The first argument, resultFileName, is the name of the resulting file. The remaining arguments are the names of files for fileNamePart.
Each fileNamePart file is a part of a zip file. You need to unzip the entire file by assembling it from its parts.
Write the unzipped file to resultFileName.
Files inside the archive can be large, e.g. 50MB.
Files inside the archive can have any name.

Example input. The archive has one file named abc.mp3:
C:/result.mp3
C:/pathToTest/test.zip.003
C:/pathToTest/test.zip.001
C:/pathToTest/test.zip.004
C:/pathToTest/test.zip.002



*/

public class UnzipFile {
    public static void main(String[] args) throws IOException {
        byte[] buffer = new byte[1024];
        ZipInputStream zipIN = null;
        OutputStream outputStream = null;
        List<String> list = new ArrayList<>();
        List<FileInputStream> listFileIn = new ArrayList<>();
        for(int i = 1; i<args.length; i++){
            list.add(args[i]);
        }
        Collections.sort(list);
        for(int i = 0; i<list.size(); i++){
            listFileIn.add(new FileInputStream(list.get(i)));
        }
        SequenceInputStream sequenceInputStream = new SequenceInputStream(Collections.enumeration(listFileIn));
        try
        {
            outputStream = new FileOutputStream(args[0]);
            zipIN = new ZipInputStream(sequenceInputStream);
            ZipEntry entry = zipIN.getNextEntry(); // zawse trzeba pobracentry z zipu
            int count;
            while((count = zipIN.read(buffer)) > 0) {
                outputStream.write(buffer,0,count);
            }
        } finally {
            zipIN.close();
            outputStream.flush();
            outputStream.close();
        }
    }
}
