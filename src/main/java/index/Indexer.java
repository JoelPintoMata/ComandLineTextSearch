package index;

import model.IndexElem;
import model.Pair;
import ranker.Ranker;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Concrete read implementation class
 */
public class Indexer {

    private static Map<String, IndexElem> index = new TreeMap<>();
    private static Map<Pair<String, String>, Integer> tf = new HashMap<>();

    private int numberOfSources;
    private Ranker rank;

    /**
     * Adds new data to the index
     * @param term the term to read
     * @param source the term source
     */
    public void add(String term, String source) {
//        convert to lower case.
        term = term.toLowerCase();

        if(term.length() == 0)
            return;

        IndexElem indexElem = new IndexElem(source, term);

        var oldVar = index.get(term);
        if(oldVar == null) {
            index.put(term, indexElem);
        } else {
            oldVar.addSource(source);

        }
//        update the term tf
        setTF(term, source);
    }

    /**
     * Sets the term frequency for a given source
     * @param term the term
     * @param source the source
     */
    private void setTF(String term, String source) {
        Pair pair = new Pair(term, source);
        Integer i = tf.get(pair);
        if(i == null)
            tf.put(pair, 1);
        else {
            tf.put(pair, ++i);
        }
    }

    /**
     * Performs a search against the read
     * @param query the query term(s)
     * @return a list of ordered results describing both the files and the match percentage
     */
    public List<String> search(String query) {
//        get the query individual terms
        String[] queryTermsArray = query.split("[ ]");

//        create a map of the query individual terms for fast search
        Map<String, String> queryTermsMap = new HashMap<>();
        Arrays.stream(queryTermsArray).forEach(queryTerm -> {
            queryTerm = queryTerm.toLowerCase();
            queryTermsMap.put(queryTerm, queryTerm);
        });

        List<IndexElem> termsFoundInFilesList = queryTermsMap.keySet().stream().map(queryTerm ->
                index.get(queryTerm)
        ).filter(x -> x != null).collect(Collectors.toList());

        List<Pair> termsPerFilePairList = termsFoundInFilesList.stream().map(fileWord ->
                fileWord.getSourceSet().stream().map(filename -> new Pair(fileWord.getTerm(), filename)).collect(Collectors.toList())
        ).flatMap(Collection::stream).collect(Collectors.toList());
//
        Map<String, Long> termsFoundPerFileMap = termsPerFilePairList
                .stream().collect(Collectors.groupingBy(x ->
                        (String) x.getValue(), Collectors.counting()
                ));

        rank.setQueryTermsArray(queryTermsArray);
        rank.setNumberOfSources(numberOfSources);
        rank.setTF(tf);

        return termsFoundPerFileMap.entrySet().stream().map(x -> new Pair(x.getKey(), calculatesPercentage(x.getValue(), queryTermsArray)))
                .sorted(rank.getComparator())
                .map(pair -> pair.getKey() + ": " + pair.getValue() + "%")
                .collect(Collectors.toList());
    }

    private long calculatesPercentage(Long value, String[] queryTermsArray) {
        return value * 100 / queryTermsArray.length;
    }

    /**
     * Sets the number of sources in this corpus
     * @param numberOfSources number of sources in this corpus
     */
    public void setNumberOfSources(int numberOfSources) {
        this.numberOfSources = this.numberOfSources + numberOfSources;
    }

    /**
     * Sets this indexer ranker
     * @param rank this indexer ranker
     */
    public void setRank(Ranker rank) {
        this.rank = rank;
    }
}
