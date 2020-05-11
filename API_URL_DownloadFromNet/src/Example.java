import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/* 
ile downloader
Implement a downloadFile(String urlString, Path downloadDirectory) method whose inputs are a URL to a file to be downloaded and the destination folder for the download.
All URLs have the following form:
https://yastatic.net/morda-logo/i/citylogos/yandex19-logo-ru.png
http://toogle.com/files/rules.txt
https://pacemook.com/photos/image1.jpg

The method must create a URL object and download the file's contents to the local disk.
Download it to a temporary directory first so that if the download fails, there won't be partially downloaded files in your directory.
Then move the file to the user's directory. Take the file name from the URL.
Only use classes and methods from the java.nio package.



*/
public class Example {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile(SetterPOM.PATH_TO_SOURCE, Paths.get(SetterPOM.PATH_TO_DIRECTORY));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        URL url = new URL(urlString);

        String fileName = "";
        if(urlString.contains("\\"))
            fileName = urlString.substring(urlString.lastIndexOf("\\"), urlString.lastIndexOf("."));
        if(urlString.contains("/"))
            fileName = urlString.substring(urlString.lastIndexOf("/")+1,urlString.lastIndexOf("."));
        String fileSuffix = urlString.substring(urlString.lastIndexOf("."));

        Path pathTmp = Files.createTempFile(downloadDirectory,fileName,fileSuffix);
        System.out.println(pathTmp.toString());

        Files.copy(url.openStream(),pathTmp, StandardCopyOption.REPLACE_EXISTING);
        return Files.move(pathTmp, downloadDirectory.resolve(fileName+fileSuffix),StandardCopyOption.REPLACE_EXISTING);
    }
}
