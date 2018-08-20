package sourceReader;

import index.Index;
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

    private Index index;
    private String location;

    @Override
    public void read() {
        List<String> filenameList = FileUtils.getFiles(location, sourceType);
        for (String filename : filenameList) {
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line = br.readLine();

                while (line != null) {
                    Arrays.stream(line.split("[,. ]")).forEach(term -> this.index.add(term, filename));
                    line = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(filenameList.size() + " files read in directory " + location);

//        set the number of sources read in the index for ranking purposes
        index.setNumberOfSources(filenameList.size());
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
