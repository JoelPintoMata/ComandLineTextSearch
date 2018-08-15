package model;

public class FileWord {

    private final String filename;
    private final String word;

    public FileWord(String filename, String word) {
        this.filename = filename;
        this.word = word;
    }

    public String getFilename() {
        return filename;
    }

    public String getWord() {
        return word;
    }

    @Override
    public String toString() {
        return "Filename: " + filename + "word: " + word;
    }
}
