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
     * @param location the files location
     */
    public static List<String> getFiles(String location, String type) {
        File f = new File(location);

        FilenameFilter textFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(type);
            }
        };

        return Arrays.stream(f.listFiles(textFilter)).map(file -> file.getName()).collect(Collectors.toList());
    }
}
