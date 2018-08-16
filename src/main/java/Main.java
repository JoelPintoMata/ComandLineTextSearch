import model.FileWord;
import model.Pair;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static TreeMap<String, FileWord> data = new TreeMap<>( );

    public static void main(String[] args) {
        try {
            for (String s : getFiles()) {
                readFile(s);
            }

            String[] queryWordsArray = args[0].split("[ ]");
            System.out.println("result7");
            List<FileWord> fileWordList = Arrays.stream(args[0].split("[ ]")).map(x ->
                    data.values().stream().filter(y -> x.toLowerCase().equals(y.getWord())).collect(Collectors.toList())
            ).flatMap(Collection::stream).collect(Collectors.toList());

            List<Pair> wordFilenamePairList = fileWordList.stream().map(fileWord ->
                    fileWord.getFilenameList().stream().map(filename -> new Pair(fileWord.getWord(), filename)).collect(Collectors.toList())
            ).flatMap(Collection::stream).collect(Collectors.toList());
//
            Map<String, Long> wordsFoundPerFilenameMap = wordFilenamePairList
                    .stream().collect(Collectors.groupingBy(Pair::getFilename, Collectors.counting()));

            wordsFoundPerFilenameMap.entrySet().stream().forEach(x -> System.out.println(x.getKey() + ": " + x.getValue() * 100 / queryWordsArray.length));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> getFiles() {
        File f = new File(System.getProperty("user.dir"));

        FilenameFilter textFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".txt");
            }
        };

        File[] files = f.listFiles(textFilter);
        List<String> result = new ArrayList<>();
        for (File file : files) {
            if (file.isFile()) {
                result.add(file.getName());
            }
        }
        return result;
    }

    private static void readFile(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();

            while (line != null) {
                Arrays.stream(line.split("[,. ]")).forEach(wrd -> {
                    // convert to lower case.
                    wrd = wrd.toLowerCase();
                    if(wrd.length() == 0)
                        return;

                    FileWord fileWord = new FileWord(filename, wrd);
//                    wordsList.add(word);

                    var oldVar = data.get(wrd);
                    if(oldVar == null) {
                        data.put(wrd, fileWord);
                    } else {
                        oldVar.addFilename(filename);
                        oldVar.incCount();
                    }
                });
                line = br.readLine();
            }
        }
    }
}
