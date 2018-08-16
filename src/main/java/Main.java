import index.TextFileSourceReaderImpl;
import index.SourceReader;
import index.Index;

import java.util.Scanner;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No directory given to index.");
        }

        Index index = new Index();

        SourceReader sourceReader = new TextFileSourceReaderImpl();
        sourceReader.setIndex(index);
        sourceReader.setSourceLocation(args[0]);
        sourceReader.index();

        Scanner keyboard = new Scanner(System.in);
        while (true) {
            System.out.print("search> ");
            String line = keyboard.nextLine();
            if(line.equals(":quit"))
                return;

            index.search(line).stream().forEach(System.out::println);
        }
    }
}
