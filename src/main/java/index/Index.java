package index;

import model.IndexElem;
import model.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Index {

    private static TreeMap<String, IndexElem> index = new TreeMap<>();

    /**
     * Indexes new data
     * @param term the term to index
     * @param source the term source
     */
    public static void index(String term, String source) {
        // convert to lower case.
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
     * Performs a search against the index
     * @param query the query term(s)
     * @return a list of ordered results describing both the files and the match percentage
     */
    public List<String> search(String query) {
        String[] queryTermsArray = query.split("[ ]");
        Map<String, String> queryTermsMap = new HashMap<>();
        Arrays.stream(queryTermsArray).forEach(queryTerm -> {
            queryTerm = queryTerm.toLowerCase();
            queryTermsMap.put(queryTerm, queryTerm);
        });

        List<IndexElem> fileWordList = this.index.values().stream().filter(y -> queryTermsMap.containsKey(y.getWord())).collect(Collectors.toList());

        List<Pair> wordFilenamePairList = fileWordList.stream().map(fileWord ->
                fileWord.getFilenameList().stream().map(filename -> new Pair(fileWord.getWord(), filename)).collect(Collectors.toList())
        ).flatMap(Collection::stream).collect(Collectors.toList());
//
        Map<String, Long> wordsFoundPerFilenameMap = wordFilenamePairList
                .stream().collect(Collectors.groupingBy(Pair::getValue, Collectors.counting()));

        return wordsFoundPerFilenameMap.entrySet().stream().map(x -> new Pair(x.getKey(), Long.toString(calculateRank(x.getValue(), queryTermsArray))))
                .sorted(Comparator.comparing(x -> x.getValue()))
                .map(pair -> pair.getKey() + ": " + pair.getValue() + "%")
                .collect(Collectors.toList());
    }

    /**
     *
     * @param value
     * @param queryTermsArray
     * @return
     */
    private long calculateRank(Long value, String[] queryTermsArray) {
        return value * 100 / queryTermsArray.length;
    }
}