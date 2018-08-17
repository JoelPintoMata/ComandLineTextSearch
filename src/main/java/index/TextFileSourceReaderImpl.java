package index;

import utils.FileUtils;

import java.io.*;
import java.util.*;

/**
 * Concrete implementation for a text file type data source reader
 */
public class TextFileSourceReaderImpl implements SourceReader {

    private final String TYPE = ".txt";

    private Index index;
    private String location;

    @Override
    public void index() {
        List<String> filenames = FileUtils.getFiles(location, TYPE);
        for (String filename : filenames) {
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line = br.readLine();

                while (line != null) {
                    Arrays.stream(line.split("[,. ]")).forEach(term -> this.index.index(term, filename));
                    line = br.readLine();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(filenames.size() + " files read in directory " + location);
    }

    @Override
    public void setIndex(Index index) {
        this.index = index;
    }

    @Override
    public void setSourceLocation(String location) {
        this.location = location;
    }
}
