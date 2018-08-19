package model;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents indexed element
 *  in detail: "the term x exists in these sources"
 */
public class IndexElem {

    private final Set<String> sourceSet = new HashSet<>();
    private final String term;

    public IndexElem(String source, String term) {
        this.sourceSet.add(source);
        this.term = term;
    }

    /**
     * Gets the list of sources where a term is found
     *
     * Note: for immutability purposes this method returns a clone
     * @return a list of sources where a term is found
     */
    public Set<String> getSourceSet() {
        return (HashSet) ((HashSet) sourceSet).clone();
    }

    public String getTerm() {
        return term;
    }

    public void addSource(String source) {
        this.sourceSet.add(source);
    }
}
