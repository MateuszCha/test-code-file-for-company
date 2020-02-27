package BigTaskHtml;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        if(f.isDirectory()) return true;
        String end = f.getName().toLowerCase();
        return (end.endsWith(".html") || end.endsWith(".htm"));
    }
    @Override
    public String getDescription() {
        return "HTML and HTM files";
    }
}