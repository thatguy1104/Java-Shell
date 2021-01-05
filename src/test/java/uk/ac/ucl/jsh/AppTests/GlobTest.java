package uk.ac.ucl.jsh.AppTests;
import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;
import uk.ac.ucl.jsh.Parser.Globbing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class GlobTest extends JshTest {

    private Globbing g = new Globbing();

    public GlobTest() throws IOException {
    }

    @Test
    public void test_glob_file_type() {
        String files = "*.txt";
        String[] expected_file_names = {JshTest.testDirectory + File.separator + "text1.txt",
                                        JshTest.testDirectory + File.separator + "text2.txt",
                                        JshTest.testDirectory + File.separator + "text3.txt"};
        List<File> result = g.determineType(files, JshTest.testDirectory);

        List<File> expected = new ArrayList<>();
        for (String file : expected_file_names) {
            expected.add(new File(file));
        }

        Collections.sort(result);
        Collections.sort(expected);

        assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    public void test_glob_directory() {
        String files = JshTest.testDirectory + File.separator + "*";
        String[] expected_file_names = {JshTest.testDirectory + File.separator + "text1.txt",
                                        JshTest.testDirectory + File.separator + "text2.txt",
                                        JshTest.testDirectory + File.separator + "text3.txt",
                                        JshTest.testDirectory + File.separator + JshTest.testSubDirectory,
                                        JshTest.testDirectory + File.separator + JshTest.dotDirectory};
        List<File> result = g.determineType(files, JshTest.testDirectory);

        List<File> expected = new ArrayList<>();
        for (String file : expected_file_names) {
            expected.add(new File(file));
        }

        Collections.sort(result);
        Collections.sort(expected);

        assertArrayEquals(expected.toArray(), result.toArray());
    }
}
