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
    public void test_1() throws IOException {
        String[][] cases = {{"grep \"d\" text1.txt", "abcdefghi"}, {"grep \"d\" text2.txt", "d\nd"}, {"grep \"aa\" text3.txt", "aaa\naaa"}};
        for (String[] aCase : cases) {
            Jsh.eval(aCase[0], out);
            String result = getEvalResult(aCase[1]);
            assertEquals(aCase[1], result);
        }
    }

    public void runAllTests() throws IOException {
        test_1();
    }
}
