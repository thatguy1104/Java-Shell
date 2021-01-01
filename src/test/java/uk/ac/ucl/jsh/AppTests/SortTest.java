package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SortTest extends JshTest {

    public SortTest() throws IOException {
    }

    @Test
    public void test_sort() {
        String[] args = {"sort " + JshTest.testDirectory + File.separator +  "text1.txt", "abcdefghi\nofeijnwio"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_sort_r() {
        String[] args = {"sort -r " + JshTest.testDirectory + File.separator + "text1.txt", "ofeijnwio\nabcdefghi"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_pipe_uniq() {
        String[] args = {"sort -r " + JshTest.testDirectory + File.separator +  "text3.txt", "i\nh\ng\nf\ne\nd\nccc\nc\nb\na"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    public void runAllTests() {
        test_sort();
        test_sort_r();

    }
}
