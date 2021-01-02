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
        String filepath = JshTest.testDirectory + File.separator;
        String[] args = {"sort " + filepath +  "text1.txt", "abcdefghi\nofeijnwio"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_sort_r() {
        String filepath = JshTest.testDirectory + File.separator;
        String[] args = {"sort -r " + filepath + "text1.txt", "ofeijnwio\nabcdefghi"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_sort_r_stdin() {
        String filepath = JshTest.testDirectory + File.separator;
        String[] args = {"sort -r <" + filepath +  "text3.txt", "AAA\nAAA\nBBB\nCCC\na\nb\nc\nccc\nd\ne"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_sort_empty() {
        String filepath = JshTest.testDirectory + File.separator + JshTest.testSubDirectory + File.separator;
        String[] args = {"sort " + filepath +  "text3.txt", ""};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_substitution_sort_find_r() {
        String filepath = JshTest.testDirectory;
        String[] args = {"cat `find " + filepath + " -name '*.txt'` | sort -r", "AAA\nAAA\nAAA\nAAA\nBBB\nBBB\nC\na\nabcdefghi\nb\nc\nccc\nd\ne\nf\ng\nh\ni\nofeijnwio"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }


    public void runAllTests() {
        test_sort();
        test_sort_r();
        test_sort_r_stdin();
        test_sort_empty();
        test_substitution_sort_find_r();
    }
}
