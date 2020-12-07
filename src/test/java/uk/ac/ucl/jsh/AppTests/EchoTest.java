package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
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
            String full_string = eval_result(aCase[0], aCase[1]);
            assertEquals(full_string, aCase[1]);
        }
    }

    public void runAllTests() throws IOException {
        test_1();
    }
}
