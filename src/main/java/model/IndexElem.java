package model;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a inverted index element
 * "the word x exists in this list of files"
 */
public class IndexElem {

    private final Set<String> filenameSet = new HashSet<>();
    private final String word;

    public IndexElem(String filename, String word) {
        this.filenameSet.add(filename);
        this.word = word;
    }

    public Set<String> getFilenameList() {
        return this.filenameSet;
    }

    public String getWord() {
        return word;
    }

    public void addFilename(String filename) {
        this.filenameSet.add(filename);
    }
}
