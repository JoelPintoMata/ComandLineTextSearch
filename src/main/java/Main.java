import model.FileWord;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    private static List<FileWord> wordsList = new ArrayList();

    public static void main(String[] args) {
        try {
            for (String s : getFiles()) {
                readFile(s);
            }

            List<FileWord> xxx = wordsList.stream().filter(x -> args[0].contains(x.getWord())).collect(Collectors.toList());
//            xxx.stream().forEach(x -> System.out.println(x.getWord()));

            Map<FileWord, Long> result = wordsList.stream().filter(x -> args[0].contains(x.getWord())).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//            System.out.println(result);

            Map<String, Long> result2 = wordsList.stream().filter(x -> args[0].contains(x.getWord())).collect(Collectors.groupingBy(FileWord::getFilename, Collectors.counting()));
//            result2.entrySet().forEach(System.out::println);

            List<Map<String, Long>> result3 = Arrays.stream(args[0].split("[ ]")).map(x -> {
                System.out.println(x);
                return wordsList.stream().filter(y -> x.equals(y.getWord())).collect(Collectors.groupingBy(FileWord::getFilename, Collectors.counting()));
            }).collect(Collectors.toList());
//            result3.forEach(System.out::println);

            Map<Map<String, Long>, Long> result4 = Arrays.stream(args[0].split("[ ]")).map(x -> {
                return wordsList.stream().filter(y -> x.equals(y.getWord())).collect(Collectors.groupingBy(FileWord::getFilename, Collectors.counting()));
            }).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            result4.forEach((x, y) -> {
                x.values().forEach(System.out::println);
                System.out.println(x);
                System.out.println(y);
            });

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
                    if(wrd.length() == 0)
                        return;
                    FileWord word = new FileWord(filename, wrd);
                    wordsList.add(word);
                });
                line = br.readLine();
            }
        }
    }
}
