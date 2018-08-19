package ranker;

import model.Pair;

import java.util.Comparator;
import java.util.Map;

public interface Ranker {

    void setQueryTermsArray(String[] queryTermsArray);

    void setNumberOfSources(int numberOfSources);

    void setTF(Map<Pair<String, String>, Integer> tf);

    /**
     * Returns the ranking algorithm comparator
     * @return the ranking algorithm comparator
     */
    Comparator<? super Pair> getComparator();
}
