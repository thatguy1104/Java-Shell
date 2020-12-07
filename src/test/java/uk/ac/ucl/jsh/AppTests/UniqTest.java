package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.JshTest;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class UniqTest extends JshTest {

    public UniqTest() throws IOException {
    }

    @Test
    public void test_1() throws IOException {
        String[][] cases = {{"uniq text3.txt", "bab\nbbb\nBBB\naaa\nAAA\nLoL\nlol\nloL"}, {"uniq -i text3.txt", "bab\nAAA\nbbb\nloL"}};
        for (String[] aCase : cases) {
            String full_string = eval_result(aCase[0], aCase[1]);
            assertEquals(full_string, aCase[1]);
        }
    }

    public void runAllTests() throws IOException {
        test_1();
    }
}
