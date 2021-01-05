package uk.ac.ucl.jsh.AppTests;
import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class UnsafeTailTest extends JshTest {

    public UnsafeTailTest() throws IOException {
    }

    @Test
    public void test_unsafe_tail_n_0() {
        String[] args = {"_tail -n 0 " + JshTest.testDirectory + File.separator + "text2.txt", ""};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_unsafe_tail_n_1() {
        String[] args = {"_tail -n 1 " + JshTest.testDirectory + File.separator + "text1.txt", "ofeijnwio"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_unsafe_tail_n_3_caps() {
        String[] args = {"_tail -n 3 " + JshTest.testDirectory + File.separator + "text2.txt", "AAA\nBBB\nAAA"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_unsafe_tail_n_3() {
        String[] args = {"_tail -n 3 " + JshTest.testDirectory + File.separator + "text3.txt", "g\nh\ni"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }
}
