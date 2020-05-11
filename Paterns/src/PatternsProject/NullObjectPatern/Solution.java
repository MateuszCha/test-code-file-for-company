package PatternsProject.NullObjectPatern;

import java.nio.file.Files;
import java.nio.file.Paths;

/*
Null object pattern
Read about the null-object pattern on Wikipedia.
Use Files to correctly initialize the fileData field with a ConcreteFileData object in the Solution class's constructor.
If you have any problems reading the file in the pathToFile path, then initialize the field with a NullFileData object.

*/
public class Solution {
    private FileData fileData;

    public Solution(String pathToFile) {
        try{
            boolean isWriteable = Files.isWritable(Paths.get(pathToFile));
            boolean isDirectory = Files.isDirectory(Paths.get(pathToFile));
            boolean isHidden = Files.isHidden(Paths.get(pathToFile));
            boolean isExecutable = Files.isExecutable(Paths.get(pathToFile));
            fileData = new ConcreteFileData(isHidden,isExecutable,isDirectory,isWriteable);
        }catch (Exception err){
            fileData = new NullFileData(err);

        }
    }

    public FileData getFileData() {
        return fileData;
    }
}
