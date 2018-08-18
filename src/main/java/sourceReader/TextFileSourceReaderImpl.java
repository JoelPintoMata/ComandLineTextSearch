package sourceReader;

import index.Indexer;
import utils.FileUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Concrete implementation for a text file type data source reader
 */
public class TextFileSourceReaderImpl implements SourceReader {

    private final String sourceType = SourceTypeEnum.TEXT.getValue();

    private Indexer indexer;
    private String location;

    @Override
    public void read() {
        List<String> filenames = FileUtils.getFiles(location, sourceType);
        for (String filename : filenames) {
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line = br.readLine();

                while (line != null) {
                    Arrays.stream(line.split("[,. ]")).forEach(term -> this.indexer.index(term, filename));
                    line = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(filenames.size() + " files read in directory " + location);
    }

    @Override
    public void setIndexer(Indexer indexer) {
        this.indexer = indexer;
    }

    @Override
    public void setSourceLocation(String location) {
        this.location = location;
    }
}
