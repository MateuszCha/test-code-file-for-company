package ziper.command;

import ziper.ConsoleHelper;
import ziper.ZipFileManager;
import ziper.exception.PathNotFoundException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ZipCreateCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        try {
            ConsoleHelper.writeMessage("Creating an archive.");

            ZipFileManager zipFileManager = getZipFileManager();

            ConsoleHelper.writeMessage("Enter the full name of the file or directory to be archived:");
            Path sourcePath = Paths.get(ConsoleHelper.readString());
            System.out.println(sourcePath.toString());
            zipFileManager.createZip(sourcePath);

            ConsoleHelper.writeMessage("Archive created.");

        } catch (PathNotFoundException e) {
            ConsoleHelper.writeMessage("You didn't correctly enter a file name or directory.");
        }
    }
}
