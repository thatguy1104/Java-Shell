package uk.ac.ucl.jsh.AppTests;
import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class UnsafeCatTest extends JshTest {

    public UnsafeCatTest() throws IOException {
    }

    @Test
    public void test_unsafe_cat_simple() throws IOException {
        String file_name = JshTest.testDirectory + File.separator + "text1.txt";
        Jsh.eval("_cat " + file_name, out);
        String result = getEvalResult(readFile(file_name));
        String expected = readFile(file_name);
        assertEquals(expected, result);
    }

    @Test
    public void test_unsafe_cat_input_sub() {
        String[] cases = {"echo `_cat testDir/text1.txt`", "abcdefghiofeijnwio"};
        Jsh.eval(cases[0], out);
        String result = pwdSupplementary(cases[1]);
        assertEquals(cases[1], result);
    }

    @Test
    public void test_unsafe_cat_multi_file() {
        String[] cases = {"_cat testDir/text1.txt testDir/text2.txt", "abcdefghi\nofeijnwio\nAAA\nBBB\nAAA"};
        Jsh.eval(cases[0], out);
        String result = getEvalResult(cases[1]);
        assertEquals(cases[1], result);
    }
}
