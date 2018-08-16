package model;


public class Pair {

    private final String word;
    private final String filename;

    public Pair(String word, String filename) {
        this.word = word;
        this.filename = filename;
    }

    public String getFilename() {
        return this.filename;
    }
}
