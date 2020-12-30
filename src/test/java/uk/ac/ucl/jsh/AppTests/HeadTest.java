package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class HeadTest extends JshTest {

    public HeadTest() throws IOException {
    }

    @Test
    public void test_1() throws IOException {
        String[] args = {"head " + JshTest.testDirectory + "text1.txt", readFile(JshTest.testDirectory + "text1.txt")};
        Jsh.eval(args[0], this.out);
        String result = getEvalResult(args[1]);
        assertEquals(args[1], result);
    }

    @Test
    public void test_2() {
        String[] args = {"head -n 7 " + JshTest.testDirectory + "text3.txt", "AAA\nBBB\nAAA\nCCC\nccc\na\nb"};
        Jsh.eval(args[0], this.out);
        String result = getEvalResult(args[1]);
        assertEquals(args[1], result);
    }

    @Test
    public void test_3() {
        String[] args = {"head -n 0 " + JshTest.testDirectory + "text1.txt", ""};
        Jsh.eval(args[0], this.out);
        String result = getEvalResult(args[1]);
        assertEquals(args[1], result);
    }

    public void runAllTests() throws IOException {
        test_1();
        test_2();
        test_3();
    }
}
