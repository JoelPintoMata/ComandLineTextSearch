package rank;

import model.Pair;

import java.util.Comparator;
import java.util.Map;

/**
 * Ranking algorithms interface
 */
public interface Rank {

    void setQueryTermsArray(String[] queryTermsArray);

    void setNumberOfSources(int numberOfSources);

    void setTF(Map<Pair<String, String>, Integer> tf);

    /**
     * Returns the ranking algorithm comparator
     * @return the ranking algorithm comparator
     */
    Comparator<? super Pair> getComparator();

    /**
     * Sets the term frequency for a given source
     * @param term the term
     * @param source the source
     */
    void addTF(String term, String source);
}
