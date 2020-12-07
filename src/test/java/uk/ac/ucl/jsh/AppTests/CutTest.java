package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.JshTest;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CutTest extends JshTest {

    public CutTest() throws IOException {
    }

    @Test
    public void test_1() throws IOException {
        String[][] cases = {{"cut -b 1 text1.txt", "a\no"}, {"cut -b 1,2 text1.txt", "ab\nof"}, {"cut -b 1- text1.txt", "bcdefghi\nfeijnwio"}, {"cut -b 1,3-4 text1.txt", "ad\noi"}};
        for (String[] aCase : cases) {
            String full_string = eval_result(aCase[0], aCase[1]);
            assertEquals(full_string, aCase[1]);
        }
    }

    public void runAllTests() throws IOException {
        test_1();
    }
}
