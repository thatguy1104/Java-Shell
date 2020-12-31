package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CutTest extends JshTest {

    public CutTest() throws IOException {
    }

    @Test
    public void test_simple() {
        String[][] cases = {{"cut -b 1 " + JshTest.testDirectory + "text1.txt", "a\no"},
                {"cut -b 1,2 " + JshTest.testDirectory +  "text1.txt", "ab\nof"},
                {"cut -b 1- " + JshTest.testDirectory +  "text1.txt", "abcdefghi\nofeijnwio"},
                {"cut -b 1,3-4 " + JshTest.testDirectory +  "text1.txt", "acd\noei"}};
        for (String[] aCase : cases) {
            Jsh.eval(aCase[0], out);
            String result = getEvalResult(aCase[1]);
            assertEquals(aCase[1], result);
        }
    }

    @Test
    public void test_cut_echo_pipe() {
        String[][] cases = {{"echo abc | cut -b 1", "a"}};
        Jsh.eval(cases[0][0], out);
        String result = getEvalResult(cases[0][1]);
        assertEquals(cases[0][1], result);
    }

    @Test
    public void test__echo_pipe_2() {
        String[][] cases = {{"echo abc | cut -b -1,2-", "abc"}};
        Jsh.eval(cases[0][0], out);
        String result = getEvalResult(cases[0][1]);
        System.out.println(result);
        assertEquals(cases[0][1], result);
    }

    public void runAllTests() {
        test_simple();
        test_cut_echo_pipe();
        test__echo_pipe_2();
    }
}
