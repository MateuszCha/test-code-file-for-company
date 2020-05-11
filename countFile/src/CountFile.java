import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/*
Write a program that will read detailed information about a folder and display it on the console.

First of all, read the folder path from the console.
If the entered path is not a directory, display "[full path] is not a folder" and exit the program.
Then calculate and display the following information:

Total folders: [the number of folders in the directory and subdirectories]
Total files: [the number of files in the directory and subdirectories]
Total size: [the total number of bytes stored in the directory]

Only use classes and methods from the java.nio package.

Don't display the square brackets ("[]").



*/
public class CountFile {
    static int countFile = 0;
    static int countDirectory = -1;
    static long countSize = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Path path = Paths.get(reader.readLine());


        reader.close();

        if(Files.isDirectory(path)){
            Files.walkFileTree(path,new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                   if (Files.isRegularFile(file)) {
                       countFile++;
                       countSize += attrs.size();
                   }

                    return super.visitFile(file, attrs);
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    countDirectory++;
                    return super.postVisitDirectory(dir, exc);
                }
            });
            System.out.println("Total folders: " + countDirectory);
            System.out.println("Total files: " + countFile);
            System.out.println("Total size: " + countSize);
        }
        else{
            System.out.format("%s is not a folder",path.toString());
        }




    }
}
