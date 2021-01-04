package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class EchoTest extends JshTest {

    public EchoTest() throws IOException {
    }

    @Test
    public void test_simple_1() {
        String[] arg = {"echo hello world", "hello world"};
        Jsh.eval(arg[0], this.out);
        String result = pwdSupplementary(arg[1]);
        assertEquals(arg[1], result);
    }

    @Test
    public void test_simple_2() {
        String[] arg = {"echo foo", "foo"};
        Jsh.eval(arg[0], this.out);
        String result = pwdSupplementary(arg[1]);
        assertEquals(arg[1], result);
    }

    @Test
    public void test_input_sub() {
        String[] arg = {"`echo echo` foo", "foo"};
        Jsh.eval(arg[0], this.out);
        String result = pwdSupplementary(arg[1]);
        assertEquals(arg[1], result);
    }

    @Test
    public void test_empty_sub() {
        String[] arg = {"echo ``a", "a"};
        Jsh.eval(arg[0], this.out);
        String result = pwdSupplementary(arg[1]);
        assertEquals(arg[1], result);
    }

    @Test
    public void test_complex_input_sub() {
        String[] arg = {"echo \"a `echo \"b\"`\"", "a b"};
        Jsh.eval(arg[0], this.out);
        String result = pwdSupplementary(arg[1]);
        assertEquals(arg[1], result);
    }

    @Test
    public void test_double_quotes() {
        String[] arg = {"echo \"a b\"", "a b"};
        Jsh.eval(arg[0], this.out);
        String result = pwdSupplementary(arg[1]);
        assertEquals(arg[1], result);
    }

    @Test
    public void test_glob_file_extension() {
        String testCase = "cd testDir; echo *.txt; cd ..";
        Jsh.eval(testCase, out);
        String evalCase = "text1.txt text2.txt text3.txt";
        String[] expectedArray = evalCase.split(" ");
        Set<String> expectedSet = new HashSet<>(Arrays.asList(expectedArray));
        String result = pwdSupplementary(evalCase);
        String[] resultArray = result.split(" ");
        Set<String> resultSet = new HashSet<>(Arrays.asList(resultArray));
        assertEquals(expectedSet, resultSet);
    }

    @Test
    public void test_glob_directory() {
        String testCase = "echo testDir/*.txt";
        Jsh.eval(testCase, out);
        String evalCase = testDirectory + File.separator + "text1.txt " +
                          testDirectory + File.separator + "text2.txt " +
                          testDirectory + File.separator + "text3.txt";
        String[] expectedArray = evalCase.split(" ");
        Set<String> expectedSet = new HashSet<>(Arrays.asList(expectedArray));
        String result = pwdSupplementary(evalCase);
        String[] resultArray = result.split(" ");
        Set<String> resultSet = new HashSet<>(Arrays.asList(resultArray));
        assertEquals(expectedSet, resultSet);
    }

    @Test
    public void test_echo_pipe() {
        String testCase = "cat testDir/text1.txt | echo";
        Jsh.eval(testCase, out);
        String evalCase = "abcdefghi\nofeijnwio";
        String[] expectedArray = evalCase.split("\n");
        Set<String> expectedSet = new HashSet<>(Arrays.asList(expectedArray));
        String result = getEvalResult(evalCase);
        String[] resultArray = result.split("\n");
        Set<String> resultSet = new HashSet<>(Arrays.asList(resultArray));
        assertEquals(expectedSet, resultSet);
    }
}
