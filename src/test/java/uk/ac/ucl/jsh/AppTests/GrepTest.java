package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class GrepTest extends JshTest {

    public GrepTest() throws IOException {
    }

    @Test
    public void test_1() {
        String[] args = {"grep \"d\" " + JshTest.testDirectory + "text1.txt", "abcdefghi"};
        Jsh.eval(args[0], out);
        String result = getEvalResult(args[1]);
        assertEquals(args[1], result);
    }

    @Test
    public void test_2() {
        String[] args = {"grep BB " + JshTest.testDirectory + "text2.txt", "BBB"};
        Jsh.eval(args[0], out);
        String result = getEvalResult(args[1]);
        assertEquals(args[1], result);
    }

    @Test
    public void test_3() {
        String[] args = {"grep a" + JshTest.testDirectory + "text3.txt", ""};
        Jsh.eval(args[0], out);
        String result = getEvalResult(args[1]);
        assertEquals(args[1], result);
    }

    public void runAllTests() {
        test_1();
        test_2();
        test_3();
    }
}
