package ua.com.bpgdev.javabegins.fileanalyzer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class FileAnalyzerTest {
    private String path = "test.txt";

    private String content = "ccc ddd! bbb aaa0! bbb aaa ccc bbb? bbb.\n" +
            "sss aaa?\n" +
            "xxx yyy! bbb zzz .";
    private String searchWord = "bbb";


    @Before
    public void before() throws IOException {
        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(content);
        }
    }

    @Test
    public void wordsCount() {
        assertEquals(5, FileAnalyzer.wordsCount(content, searchWord));
    }

    @Test
    public void findSentences() {
        // How should I test such functionality?
    }

    @After
    public void after() throws IOException {
        File testFile = new File("test.txt");
        if (testFile.exists()) {
            if (!testFile.delete()) {
                System.out.println("All temporary files are deleted.");
            }
        }
    }
}