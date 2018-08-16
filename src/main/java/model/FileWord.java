package model;

import java.util.HashSet;
import java.util.Set;

public class FileWord {

    private final Set<String> filenameSet;
    private final String word;
    private int count;  // number of occurrences

    public FileWord(String filename, String word) {
        this.filenameSet = new HashSet<>();
        this.filenameSet.add(filename);
        this.word = word;
        this.count = 1;
    }

    public Set<String> getFilenameList() {
        return this.filenameSet;
    }

    public String getWord() {
        return word;
    }

    @Override
    public String toString() {
        return "Filename: " + filenameSet.stream().map(x -> " x ") + "word: " + word;
    }

    public void addFilename(String filename) {
        this.filenameSet.add(filename);
    }

    public void incCount() {
        this.count++;
    }
}
