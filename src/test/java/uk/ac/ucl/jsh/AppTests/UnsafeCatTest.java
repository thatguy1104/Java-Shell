package uk.ac.ucl.jsh.AppTests;
import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;

public class UnsafeCatTest extends JshTest {

    public UnsafeCatTest() throws IOException {
    }

    OutputStream outs = new ByteArrayOutputStream();

    @Test
    public void test_unsafe_cat_simple() throws IOException {
        String file_name = JshTest.testDirectory + File.separator + "text1.txt";
        Jsh.eval("_cat " + file_name, outs);
        //String result = getEvalResult(readFile(file_name));
        String expected = readFile(file_name);
        assertEquals(expected, outs.toString().trim());
    }

    @Test
    public void test_unsafe_cat_input_sub() {
        String[] cases = {"echo `_cat testDir/text1.txt`", "abcdefghiofeijnwio"};
        Jsh.eval(cases[0], outs);
        //String result = pwdSupplementary(cases[1]);
        assertEquals(cases[1], outs.toString().trim());
    }

    @Test
    public void test_unsafe_cat_multi_file() {
        String[] cases = {"_cat testDir/text1.txt testDir/text2.txt", "abcdefghi" + System.getProperty("line.separator") +
                                                                      "ofeijnwio" + System.getProperty("line.separator") +
                                                                      "AAA" + System.getProperty("line.separator") +
                                                                      "BBB" + System.getProperty("line.separator") +
                                                                      "AAA"};
        Jsh.eval(cases[0], outs);
        //String result = getEvalResult(cases[1]);
        assertEquals(cases[1], outs.toString().trim());
    }
}
