import javafx.util.Pair;
import model.FileWord;

import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static List<FileWord> wordsList = new ArrayList();
    private static TreeMap<String, FileWord> data = new TreeMap<>( );

    public static void main(String[] args) {
        try {
            for (String s : getFiles()) {
                readFile(s);
            }

//            List<FileWord> xxx = wordsList.stream().filter(x -> args[0].contains(x.getWord())).collect(Collectors.toList());
////            xxx.stream().forEach(x -> System.out.println(x.getWord()));
//
//            Map<FileWord, Long> result = wordsList.stream().filter(x -> args[0].contains(x.getWord())).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
////            System.out.println(result);
//
//            Map<String, Long> result2 = wordsList.stream().filter(x -> args[0].contains(x.getWord())).collect(Collectors.groupingBy(FileWord::getFilename, Collectors.counting()));
////            result2.entrySet().forEach(System.out::println);
//
//            List<Map<String, Long>> result3 = Arrays.stream(args[0].split("[ ]")).map(x -> {
////                System.out.println(x);
//                return wordsList.stream().filter(y -> x.equals(y.getWord())).collect(Collectors.groupingBy(FileWord::getFilename, Collectors.counting()));
//            }).collect(Collectors.toList());
////            result3.forEach(System.out::println);
//
//            Map<Map<String, Long>, List<Map<String, Long>>> result4 = Arrays.stream(args[0].split("[ ]")).map(x -> {
//                return wordsList.stream().filter(y -> x.equals(y.getWord())).collect(Collectors.groupingBy(FileWord::getFilename, Collectors.counting()));
//            }).collect(Collectors.groupingBy(Function.identity()));
//
//            result4.forEach((x, y) -> {
////                x.values().forEach(System.out::println);
////                System.out.println(x);
////                System.out.println(y);
//            });
//
//            System.out.println("result5");
//            List<FileWord> result5 = Arrays.stream(args[0].split("[ ]")).map(x -> {
//                return wordsList.stream().filter(y -> x.equals(y.getWord())).collect(Collectors.toList());
//            }).flatMap(y -> y.stream()).collect(Collectors.toList());
//            System.out.println("result5");


            System.out.println("result6");
            List<FileWord> result6 = Arrays.stream(args[0].split("[ ]")).map(x -> {
                return data.values().stream().filter(y -> x.toLowerCase().equals(y.getWord())).collect(Collectors.toList());
            }).flatMap(y -> y.stream()).collect(Collectors.toList());

            result6.forEach(x -> {
                System.out.println(x);
                x.getFilenameList().stream().forEach(System.out::println);
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
