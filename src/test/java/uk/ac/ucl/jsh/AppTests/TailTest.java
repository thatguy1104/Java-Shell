package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TailTest extends JshTest {

    public TailTest() throws IOException {
    }

    @Test
    public void test_1() throws IOException {
        String[][] cases = {{"tail -n 3 text2.txt", "o\nw\nj"}, {"tail -n 3 text3.txt", "LoL\nlol\nloL"}, {"tail -n 0 text1.txt", ""}};
        for (String[] aCase : cases) {
            Jsh.eval(aCase[0], this.out);
            String full_string = full_line(aCase[1]);
            assertEquals(full_string, aCase[1]);
        }
    }

    public void runAllTests() throws IOException {
        test_1();
    }
}
