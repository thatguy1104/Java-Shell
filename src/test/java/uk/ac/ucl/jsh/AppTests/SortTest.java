package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SortTest extends JshTest {

    public SortTest() throws IOException {
    }

    @Test
    public void test_1() throws IOException {
        String[][] cases = {{"sort -r text1.txt", "ofeijnwio\nabcdefghi"}, {"sort -r text3.txt", "lol\nloL\nbbb\nbbb\nbab\naaa\naaa\nLoL\nBBB\nAAA"}};
        for (String[] aCase : cases) {
            Jsh.eval(aCase[0], this.out);
            String full_string = getEvalResult(aCase[1]);
            assertEquals(full_string, aCase[1]);
        }
    }

    public void runAllTests() throws IOException {
        test_1();
    }
}
