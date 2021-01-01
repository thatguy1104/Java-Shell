package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class UniqTest extends JshTest {

    public UniqTest() throws IOException {
    }

    @Test
    public void test_uniq() {
        String[] args = {"uniq " + JshTest.testDirectory + "text3.txt", "AAA\nBBB\nAAA\nCCC\nccc\na\nb\nc"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_uniq_i() {
        String[] args = {"uniq -i " + JshTest.testDirectory + "text3.txt", "d\ne\nf\ng"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    public void runAllTests() {
        test_uniq();
        test_uniq_i();

    }
}
