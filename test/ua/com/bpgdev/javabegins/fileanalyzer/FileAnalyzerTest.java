package ua.com.bpgdev.javabegins.fileanalyzer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.Assert.*;

public class FileAnalyzerTest {
    private String path = "test.txt";

    private String content = "ccc ddd! bbb aaa0! bbb aaa ccc bbb? bbb.\n" +
            "sss aaa?\n" +
            "xxx yyy! bbb zzz .";
    private String searchWord = "bbb";


    @Before
    public void before() throws IOException {
        OutputStream outputStream = new FileOutputStream(path);

        byte[] bytes = content.getBytes();
        for (byte aByte : bytes) {
            outputStream.write(aByte);
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
        // Why is the file not deleted by this code?
        File testFile = new File("test.txt");
        if (testFile.exists()) {
            if (!testFile.delete()) {
                testFile.createNewFile();
                testFile.delete();

            }
        }
    }
}