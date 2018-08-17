package model;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a inverted index element
 * "the word x exists in this list of files"
 */
public class IndexElem {

    private final Set<String> resourceSet = new HashSet<>();
    private final String word;

    public IndexElem(String filename, String word) {
        this.resourceSet.add(filename);
        this.word = word;
    }

    /**
     * Gets the list of resource names where a word is found
     * Note: for immutability purposes this method returns a clone
     * @return a list of resource names where a word is found
     */
    public Set<String> getResourceList() {
        return (HashSet) ((HashSet) resourceSet).clone();
    }

    public String getWord() {
        return word;
    }

    public void addFilename(String filename) {
        this.resourceSet.add(filename);
    }
}
