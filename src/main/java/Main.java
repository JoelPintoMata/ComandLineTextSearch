import index.Indexer;
import ranker.Ranker;
import ranker.RankTFxIDFImpl;
import sourceReader.SourceReader;
import sourceReader.TextFileSourceReaderImpl;

import java.util.Scanner;

/**
 * Commandline text search main class
 */
public class Main {

    /**
     * Commandline text search main method
     * @param args the initial arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No directory given to indexer.");
        }

        Ranker rankTFxIDF = new RankTFxIDFImpl();

        Indexer indexer = new Indexer();
        indexer.setRank(rankTFxIDF);

        SourceReader sourceReader = new TextFileSourceReaderImpl();
        sourceReader.setIndexer(indexer);
        sourceReader.setSourceLocation(args[0]);
        sourceReader.read();

        Scanner keyboard = new Scanner(System.in);
        while (true) {
            System.out.print("search> ");
            String line = keyboard.nextLine();
            if(line.equals(":quit"))
                return;

            indexer.search(line).stream().forEach(System.out::println);
        }
    }
}
