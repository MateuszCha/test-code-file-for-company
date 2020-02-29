package ziper.command;

import ziper.ConsoleHelper;
import ziper.ZipFileManager;

import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class ZipCommand implements Command {

    public ZipFileManager getZipFileManager() throws Exception{
        ConsoleHelper.writeMessage("Enter the full path to the archive file:");
        Path zipPath = Paths.get(ConsoleHelper.readString());
        System.out.println(zipPath.toString());
        return new ZipFileManager(zipPath);
    }
}