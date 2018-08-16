package utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {

    /**
     * Retrieves a list of text file names from the project root
     * @return a list of text file names from the project root
     */
    public static List<String> getFiles() {
        File f = new File(System.getProperty("user.dir"));

        FilenameFilter textFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".txt");
            }
        };

        return Arrays.stream(f.listFiles(textFilter)).map(file -> file.getName()).collect(Collectors.toList());
    }
}
