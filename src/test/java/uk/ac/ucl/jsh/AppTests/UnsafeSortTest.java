package uk.ac.ucl.jsh.AppTests;
import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class UnsafeSortTest extends JshTest {

    public UnsafeSortTest() {
    }

    @Test
    public void test_unsafe_sort() {
        String filepath = JshTest.testDirectory + File.separator;
        String[] args = {"_sort " + filepath +  "text1.txt", "abcdefghi" + System.getProperty("line.separator") +
                                                             "ofeijnwio"};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void test_unsafe_sort_r() {
        String filepath = JshTest.testDirectory + File.separator;
        String[] args = {"_sort -r " + filepath + "text1.txt", "ofeijnwio" + System.getProperty("line.separator") +
                                                               "abcdefghi"};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void test_unsafe_sort_empty() {
        String filepath = JshTest.testDirectory + File.separator + JshTest.testSubDirectory + File.separator;
        String[] args = {"_sort " + filepath +  "text3.txt", ""};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }
}
