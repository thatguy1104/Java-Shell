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
        String[][] cases = {{"head text1.txt", readFile("text1.txt")}, {"head -n 3 text3.txt", "bab\nbbb\nBBB"}, {"head -n 0 text1.txt", ""}};
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
