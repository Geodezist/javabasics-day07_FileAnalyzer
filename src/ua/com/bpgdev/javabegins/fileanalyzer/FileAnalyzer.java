package ua.com.bpgdev.javabegins.fileanalyzer;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FileAnalyzer {
    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            throw new IllegalArgumentException("Usage of the program: FileAnalyzer pathToTextFile searchWord");
        }
        String pathToTextFile = args[0];
        String searchWord = args[1];

        String text = readFromFile(pathToTextFile);

        int searchWordCount = wordsCount(text, searchWord);
        System.out.println("Word " + searchWord + " was found in text " + searchWordCount + " time(s).");

        ArrayList<String> sentences = findSentences(text, searchWord);
        for (String sentence : sentences) {
            System.out.println(sentence);
        }
    }

    private static String readFromFile(String pathToTextFile) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String stringLine;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToTextFile))) {
            while ((stringLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(stringLine).append(System.getProperty("line.separator"));
            }
        }
        return stringBuilder.toString();
    }

    public static int wordsCount(String text, String searchWord) {
        String regexpSplitString = System.getProperty("line.separator") + "| |\\.|\\?|!";
        return (int) Stream.of(text.split(regexpSplitString)).filter(word -> word.equals(searchWord)).count();
    }

    public static ArrayList<String> findSentences(String text, String searchWord) {

        ArrayList<String> stringArrayList = new ArrayList<>();

        String regexpSplitString = "(?<=\\.)|(?<=\\?)|(?<=!)";
        Pattern searchPattern = Pattern.compile(searchWord);

        Stream.of(text.split(regexpSplitString)).filter(sentence -> {
            Matcher matcher = searchPattern.matcher(sentence);
            return matcher.find();
        }).forEach(sentence ->
                stringArrayList.add(sentence.trim()));

        return stringArrayList;
    }
}
