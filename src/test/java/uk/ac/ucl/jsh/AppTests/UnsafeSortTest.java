package uk.ac.ucl.jsh.AppTests;
import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class UnsafeSortTest extends JshTest {

    public UnsafeSortTest() throws IOException {
    }

    @Test
    public void test_unsafe_sort() {
        String filepath = JshTest.testDirectory + File.separator;
        String[] args = {"_sort " + filepath +  "text1.txt", "abcdefghi\nofeijnwio"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_unsafe_sort_r() {
        String filepath = JshTest.testDirectory + File.separator;
        String[] args = {"_sort -r " + filepath + "text1.txt", "ofeijnwio\nabcdefghi"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_unsafe_sort_empty() {
        String filepath = JshTest.testDirectory + File.separator + JshTest.testSubDirectory + File.separator;
        String[] args = {"_sort " + filepath +  "text3.txt", ""};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }
}
