import model.IndexElem;
import model.Pair;
import index.TextFileSourceReaderImpl;
import index.SourceReader;
import index.Index;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        SourceReader sourceReader = new TextFileSourceReaderImpl();
        Index index = new Index(sourceReader);
        sourceReader.index();

        String[] queryWordsArray = args[0].split("[ ]");

        List<IndexElem> fileWordList = Arrays.stream(args[0].split("[ ]")).map(x ->
                index.get().values().stream().filter(y -> x.toLowerCase().equals(y.getWord())).collect(Collectors.toList())
        ).flatMap(Collection::stream).collect(Collectors.toList());

        List<Pair> wordFilenamePairList = fileWordList.stream().map(fileWord ->
                fileWord.getFilenameList().stream().map(filename -> new Pair(fileWord.getWord(), filename)).collect(Collectors.toList())
        ).flatMap(Collection::stream).collect(Collectors.toList());
//
        Map<String, Long> wordsFoundPerFilenameMap = wordFilenamePairList
                .stream().collect(Collectors.groupingBy(Pair::getValue, Collectors.counting()));

        wordsFoundPerFilenameMap.entrySet().stream().forEach(x -> System.out.println(x.getKey() + ": " + x.getValue() * 100 / queryWordsArray.length));
    }
}
