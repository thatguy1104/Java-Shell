package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TailTest extends JshTest {

    public TailTest() throws IOException {
    }

    @Test
    public void test_1() {
        String[] args = {"tail -n 3 " + JshTest.testDirectory + "text2.txt", "AAA\nBBB\nAAA"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_2() {
        String[] args = {"tail -n 1 " + JshTest.testDirectory + "text1.txt", "ofeijnwio"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_3() {
        String[] args = {"tail -n 3 " + JshTest.testDirectory + "text3.txt", "g\nh\ni"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    public void runAllTests() {
        test_1();
        test_2();
        test_3();
    }
}
