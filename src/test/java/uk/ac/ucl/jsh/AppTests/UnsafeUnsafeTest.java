package uk.ac.ucl.jsh.AppTests;
import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;

public class UnsafeUnsafeTest extends JshTest {

    public UnsafeUnsafeTest() throws IOException {
    }

    @Test
    public void test_unsafe_nothing() throws IOException {
        String file_name = JshTest.testDirectory + File.separator + "text1.txt";
        Jsh.eval("_cat", out);
        String result = pwdSupplementary(readFile(file_name));
        String expected = "_cat: missing arguments";
        assertEquals(expected, result);
    }

    @Test
    public void test_cat_redirection() throws IOException {
        String fileName = JshTest.testDirectory + File.separator + "text2.txt";
        Jsh.eval("cat < " + fileName, out);
        String result = getEvalResult(readFile(fileName));
        String expected = readFile(fileName);
        assertEquals(expected, result);
    }

    @Test
    public void test_cat_input_sub() {
        String[] cases = {"echo `cat testDir/text1.txt`", "abcdefghiofeijnwio"};
        Jsh.eval(cases[0], out);
        String result = pwdSupplementary(cases[1]);
        assertEquals(cases[1], result);
    }

    @Test
    public void test_cat_multi_file() {
        String[] cases = {"cat testDir/text1.txt testDir/text2.txt", "abcdefghi\nofeijnwio\nAAA\nBBB\nAAA"};
        Jsh.eval(cases[0], out);
        String result = getEvalResult(cases[1]);
        assertEquals(cases[1], result);
    }
}