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
    public void test_1() {
        String[][] cases = {{"cut -b 1 text1.txt", "a\no"}, {"cut -b 1,2 text1.txt", "ab\nof"}, {"cut -b 1- text1.txt", "bcdefghi\nfeijnwio"}, {"cut -b 1,3-4 text1.txt", "acd\noei"}};
        for (String[] aCase : cases) {
            Jsh.eval(aCase[0], out);
            String result = getEvalResult(aCase[1]);
            assertEquals(aCase[1], result);
        }
    }

    @Test
    public void test_2() throws IOException {
        String[][] cases = {{"echo abc | cut -b 1", "a"}};
        Jsh.eval(cases[0][0], out);
        String result = getEvalResult(cases[0][1]);
        assertEquals(cases[0][1], result);
    }

    @Test
    public void test_3() throws IOException {
        String[][] cases = {{"echo abc | cut -b -1,2-", "abc"}};
        Jsh.eval(cases[0][0], out);
        String result = getEvalResult(cases[0][1]);
        System.out.println(result);
        assertEquals(cases[0][1], result);
    }

    public void runAllTests() throws IOException {
        test_1();
        test_2();
        test_3();
    }
}
