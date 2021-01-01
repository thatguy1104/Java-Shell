package uk.ac.ucl.jsh.AppTests;
import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CatTest extends JshTest {

    public CatTest() throws IOException {
    }

    @Test
    public void test_cat_simple() throws IOException {
        String file_name = JshTest.testDirectory + "/text1.txt";
        Jsh.eval("cat " + file_name, out);
        String result = getEvalResult(readFile(file_name));
        String expected = readFile(file_name);
        assertEquals(expected, result);
    }

    @Test
    public void test_cat_redirection() throws IOException {
        String fileName = JshTest.testDirectory + "/text2.txt";
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

    public void runAllTests() throws IOException {
        test_cat_simple();
        test_cat_redirection();
        test_cat_input_sub();
        test_cat_multi_file();
    }
}
