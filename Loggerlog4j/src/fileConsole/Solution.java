package fileConsole;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/* 
Setting up a logger
You've been given a log4j.properties file that contains logger settings for developers.
The logging requirements for the production environment have changed.

Here is a list of the changes that need to be made:
1) A log's file size must not exceed 5 megabytes.
2) Log files must be stored on the D drive in the log directory, and must be called runApp.log.
3) The last 6 log files must be stored. If the sixth file is already full (i.e. is 5 MB), then you need to delete the first file and create a new one.
4) The log level for the console must be set to ERROR.
5) Set the minimum log level to WARN.

Edit the log4j.properties file as outlined in the task conditions.


Requirements:
1. A log's file size must not exceed 5 megabytes.
2. Log files must be stored on the D drive in the log directory, and must be called runApp.log.
3. The last 6 log files must be stored. If the sixth file is already full (i.e. is 5 MB), then you need to delete the first file and create a new one.
4. The log level for the console must be set to ERROR.
5. Set the minimum log level to WARN.

*/
public class Solution {
    public static void main(String args[]) throws IOException {
        String logProperties = "4.JavaCollections/src/" + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/log4j.properties";
        Path path = Paths.get(logProperties).toAbsolutePath();
        try (InputStream is = new FileInputStream(path.toFile())) {
            Properties properties = new Properties();
            properties.load(is);
        }
    }
}
