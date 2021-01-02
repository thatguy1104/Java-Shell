package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.Applications.Find;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class FindTest extends JshTest {

    public FindTest() throws IOException {
    }

    @Test
    public void test_find() {
        String[] cases = {"cd testDir; find -name text1.txt; cd ..", "." + File.separator + "text1.txt\n" +
                                                                     "." + File.separator + testSubDirectory + File.separator + "text1.txt"};
        Jsh.eval(cases[0], out);
        String result = getEvalResult(cases[1]);
        assertEquals(cases[1], result);
    }

    @Test
    public void test_find_dir() {
        String[] cases = {"find testDir -name text1.txt", testDirectory + File.separator + testSubDirectory + File.separator + "text1.txt\n" +
                                                          testDirectory + File.separator + "text1.txt"};
        Jsh.eval(cases[0], out);
        String result = getEvalResult(cases[1]);
        assertEquals(cases[1], result);
    }

    @Test
    public void test_find_glob() {
        String[] cases = {"cd testDir; find -name *.txt; cd ..", "." + File.separator + testSubDirectory + File.separator + "text3.txt\n" +
                                                                 "." + File.separator + "text2.txt\n" +
                                                                 "." + File.separator + "text3.txt\n" +
                                                                 "." + File.separator + "text1.txt\n" +
                                                                 "." + File.separator + testSubDirectory + File.separator + "text2.txt\n" +
                                                                 "." + File.separator + testSubDirectory + File.separator + "text1.txt"};
        Jsh.eval(cases[0], out);
        String result = getEvalResult(cases[1]);
        assertEquals(cases[1], result);
    }

    @Test
    public void test_find_dir_glob() {
        String[] cases = {"find testDir/testSubDir -name *.txt", testDirectory + File.separator + testSubDirectory + File.separator + "text2.txt\n" +
                                                                 testDirectory + File.separator + testSubDirectory + File.separator + "text3.txt\n" +
                                                                 testDirectory + File.separator + testSubDirectory + File.separator + "text1.txt"};
        Jsh.eval(cases[0], out);
        String result = getEvalResult(cases[1]);
        assertEquals(cases[1], result);
    }

    @Test
    public void test_find_subDir() {
        String[] cases = {"cd testDir/testSubDir; find -name text1.txt; cd ..; cd ..", "." + File.separator + "text1.txt"};
        Jsh.eval(cases[0], out);
        String result = getEvalResult(cases[1]);
        assertEquals(cases[1], result);
    }

    public void runAllTests() {
        test_find();
        test_find_dir();
        test_find_glob();
        test_find_dir_glob();
        test_find_subDir();
    }
}
