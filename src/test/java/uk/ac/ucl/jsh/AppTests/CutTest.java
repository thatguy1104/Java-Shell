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
    public void test_cut_simple() {
        String[] arg = {"cut -b 1 " + JshTest.testDirectory + "/text1.txt", "a\no"};
        Jsh.eval(arg[0], out);
        String result = getEvalResult(arg[1]);
        assertEquals(arg[1], result);
    }

    @Test
    public void test_cut_simple_2() {
        String[] arg = {"cut -b 1,2 " + JshTest.testDirectory +  "/text1.txt", "ab\nof"};
        Jsh.eval(arg[0], out);
        String result = getEvalResult(arg[1]);
        assertEquals(arg[1], result);
    }

    @Test
    public void test_cut_simple_3() {
        String[] arg = {"cut -b 1- " + JshTest.testDirectory +  "/text1.txt", "abcdefghi\nofeijnwio"};
        Jsh.eval(arg[0], out);
        String result = getEvalResult(arg[1]);
        assertEquals(arg[1], result);
    }

    @Test
    public void test_cut_simple_4() {
        String[] arg = {"cut -b 1,3-4 " + JshTest.testDirectory +  "/text1.txt", "acd\noei"};
        Jsh.eval(arg[0], out);
        String result = getEvalResult(arg[1]);
        assertEquals(arg[1], result);
    }

    @Test
    public void test_cut_echo_pipe() {
        String[] arg = {"echo abc | cut -b 1", "a"};
        Jsh.eval(arg[0], out);
        String result = getEvalResult(arg[1]);
        assertEquals(arg[1], result);
    }

    @Test
    public void test__echo_pipe_2() {
        String[] arg = {"echo abc | cut -b -1,2-", "abc"};
        Jsh.eval(arg[0], out);
        String result = getEvalResult(arg[1]);
        System.out.println(result);
        assertEquals(arg[1], result);
    }

    public void runAllTests() {
        test_cut_simple();
        test_cut_simple_2();
        test_cut_simple_3();
        test_cut_simple_4();
        test_cut_echo_pipe();
        test__echo_pipe_2();
    }
}
