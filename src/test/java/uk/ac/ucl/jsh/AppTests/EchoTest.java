package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class EchoTest extends JshTest {

    public EchoTest() throws IOException {
    }

    @Test
    public void test_1() throws IOException {
        String[][] cases = {{"echo foo", "foo"}, {"echo hello world", "hello world"}, {"echo \"hello    world\"", "hello    world"}};
        for (String[] aCase : cases) {
            String expected = aCase[1];
            Jsh.eval(aCase[0], this.out);
            String result = getEvalResult(aCase[1]);
            assertEquals(expected, result);
        }
    }

    public void runAllTests() throws IOException {
        test_1();
    }
}
