package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.JshTest;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FindTest extends JshTest {

    public FindTest() throws IOException {
    }

    @Test
    public void test_1() throws IOException {
        String[][] cases = {{"find *.txt", "text1.txt"}, {"find run.txt", "run.txt"}, {"find text1.txt", "text1.txt"}, {"find text2.txt", "text2.txt"}};
        for (String[] aCase : cases) {
            String full_string = eval_result(aCase[0], aCase[1]);
            assertEquals(full_string, aCase[1]);
        }
    }

    public void runAllTests() throws IOException {
        test_1();
    }
}
