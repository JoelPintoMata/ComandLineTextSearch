package index;

import model.IndexElem;
import model.Pair;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Concrete read implementation class
 */
public class Indexer {

    private static Map<String, IndexElem> index = new TreeMap<>();

    /**
     * Indexes new data
     * @param term the term to read
     * @param source the term source
     */
    public void index(String term, String source) {
//        convert to lower case.
        term = term.toLowerCase();

        if(term.length() == 0)
            return;

        IndexElem indexElem = new IndexElem(source, term);

        var oldVar = index.get(term);
        if(oldVar == null) {
            index.put(term, indexElem);
        } else {
            oldVar.addFilename(source);
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

        List<IndexElem> termsFoundInFilesList = index.values().stream().filter(y -> queryTermsMap.containsKey(y.getWord())).collect(Collectors.toList());

        List<Pair> termsPerFilePairList = termsFoundInFilesList.stream().map(fileWord ->
                fileWord.getResourceList().stream().map(filename -> new Pair(fileWord.getWord(), filename)).collect(Collectors.toList())
        ).flatMap(Collection::stream).collect(Collectors.toList());
//
        Map<String, Long> termsFoundPerFileMap = termsPerFilePairList
                .stream().collect(Collectors.groupingBy(Pair::getValue, Collectors.counting()));

        return termsFoundPerFileMap.entrySet().stream().map(x -> new Pair(x.getKey(), Long.toString(calculatesPercentage(x.getValue(), queryTermsArray))))
                .sorted(Comparator.comparing(x -> x.getValue()))
                .map(pair -> pair.getKey() + ": " + pair.getValue() + "%")
                .collect(Collectors.toList());
    }

    private long calculatesPercentage(Long value, String[] queryTermsArray) {
        return value * 100 / queryTermsArray.length;
    }
}
