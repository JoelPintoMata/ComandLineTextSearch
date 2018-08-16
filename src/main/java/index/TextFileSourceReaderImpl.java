package index;

import utils.FileUtils;

import java.io.*;
import java.util.*;

public class TextFileSourceReaderImpl implements SourceReader {

    private Index index;

    @Override
    public void index() {
        for (String filename : FileUtils.getFiles()) {
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
    }

    @Override
    public void setIndex(Index index) {
        this.index = index;
    }
}
