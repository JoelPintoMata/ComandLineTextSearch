import index.Index;
import rank.Rank;
import rank.RankTFxIDFImpl;
import sourceReader.SourceReader;
import sourceReader.TextFileSourceReaderImpl;

import java.util.List;
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
            throw new IllegalArgumentException("No directory given to index.");
        }

        Rank rankTFxIDF = new RankTFxIDFImpl();

        Index index = new Index();
        index.setRank(rankTFxIDF);

        SourceReader sourceReader = new TextFileSourceReaderImpl();
        sourceReader.setIndex(index);
        sourceReader.setSourceLocation(args[0]);
        sourceReader.read();

        Scanner keyboard = new Scanner(System.in);
        while (true) {
            System.out.print("search> ");
            String line = keyboard.nextLine();

            if(line.equals(":quit"))
                return;

            List<String> result = index.search(line);
            if (result.size() > 0)
                result.stream().forEach(System.out::println);
            else
                System.out.println("no matches found");
        }
    }
}
