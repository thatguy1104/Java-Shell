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
    public void test_1() {
        String[][] cases = {{"echo hello world", "hello world"}, {"echo foo", "foo"}};
        for (String[] aCase : cases) {
            Jsh.eval(aCase[0], this.out);
            String result = pwdSupplementary(aCase[1]);
            assertEquals(aCase[1], result);
        }
    }

    public void runAllTests() {
        test_1();
    }
}
