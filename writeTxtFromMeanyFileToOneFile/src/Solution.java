import java.io.*;
import java.util.*;

/*
Iterating through a file tree
1. The main method is passed two arguments as inputs.
The first argument is path, which is the path to the directory; the second is resultFileAbsolutePath, which is the name (full path) of an existing file that will store the result.
2. For each file in the path directory and in all of its subdirectories, do the following:
For each file whose size in bytes is NOT greater than 50, do the following:
2.1. Sort them by file name in ascending order. Don't include the path when sorting.
2.2. Rename resultFileAbsolutePath to "allFilesContent.txt" (use the FileUtils.renameFile method and FileUtils.isExist if necessary).
2.3. Sequentially write the contents of each file from step 2.1 to allFilesContent.txt. After the body of each file, write "\n".
All files have the TXT extension.
Use "/" as the path separator.
*/
public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(args[0]);
        System.out.println(args[0] + " " + args[1]);
        File resultFileAbsolutePath = new File(args[1]);
        File newFilePathDestination = new File(resultFileAbsolutePath.getParent() + "\\allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath,newFilePathDestination);
        Queue<File> queue = new LinkedList<>();
        List<File> list = new ArrayList<>();
        //Add all file to list
        queue.add(file);
        while(!queue.isEmpty())
        {
            file = queue.poll();
            if (file.isFile() && file.length() <= 50) list.add(new File(file.getAbsolutePath()));
            if(file.isDirectory()) {
                for (File filePath : file.listFiles()) {
                    queue.add(filePath);
            }   }


        }

        ///Sorted the list from ascending order.
        Collections.sort(list, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
             //   char[] str1 = o1.getName().toLowerCase().replaceAll(".txt","").toCharArray();
             //   char[] str2 = o2.getName().toLowerCase().replaceAll(".txt","").toCharArray();
                  char[] str1 = o1.getName().toLowerCase().toCharArray();
                   char[] str2 = o2.getName().toLowerCase().toCharArray();
                int length = str1.length;
                int result = 0;
                if(str1.length < str2.length)
                    length = str2.length;
                    for(int i = 0; i<length; i++){
                        if(str1.length <= i) {
                            result = -1;
                            break;
                        }
                        if(str2.length <= i) {
                            result = 1;
                            break;
                        }
                        if((int)str1[i] == (int)str2[i]) continue;
                        result = (int) str1[i] - (int)str2[i];
                        break;
                    }
                return result;
            }
        });
        String newLine = (char)13 + "" + (char)10;
        FileOutputStream fileOUT = null;
        FileInputStream fileIN = null;
        try {
            fileOUT = new FileOutputStream(newFilePathDestination);
            for(File listFileForOF : list){
                fileIN = new FileInputStream(listFileForOF);
                while (fileIN.available()>0) {
                    byte[] buffer = new byte[1024];
                    int count = fileIN.read(buffer);
                    fileOUT.write(buffer,0,count);
                }
                fileIN.close();
                fileOUT.write(newLine.getBytes());
            }
            fileOUT.close();
        } catch (IOException err){
            err.printStackTrace();
            try {
                fileOUT.close();
                fileIN.close();
            }catch (IOException e){}
        }
    }
}