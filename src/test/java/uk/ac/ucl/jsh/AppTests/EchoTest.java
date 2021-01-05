package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class EchoTest extends JshTest {

    public EchoTest() {
    }

    @Test
    public void test_simple_1() {
        String[] arg = {"echo hello world", "hello world"};
        Jsh.eval(arg[0], outs);
        assertEquals(arg[1], outs.toString().trim());
    }

    @Test
    public void test_simple_2() {
        String[] arg = {"echo foo", "foo"};
        Jsh.eval(arg[0], outs);
        assertEquals(arg[1], outs.toString().trim());
    }

    @Test
    public void test_input_sub() {
        String[] arg = {"`echo echo` foo", "foo"};
        Jsh.eval(arg[0], outs);
        assertEquals(arg[1], outs.toString().trim());
    }

    @Test
    public void test_empty_sub() {
        String[] arg = {"echo ``a", "a"};
        Jsh.eval(arg[0], outs);
        assertEquals(arg[1], outs.toString().trim());
    }

    @Test
    public void test_complex_input_sub() {
        String[] arg = {"echo \"a `echo \"b\"`\"", "a b"};
        Jsh.eval(arg[0], outs);
        assertEquals(arg[1], outs.toString().trim());
    }

    @Test
    public void test_double_quotes() {
        String[] arg = {"echo \"a b\"", "a b"};
        Jsh.eval(arg[0], outs);
        assertEquals(arg[1], outs.toString().trim());
    }

    @Test
    public void test_glob_file_extension() {
        String testCase = "cd testDir; echo *.txt; cd ..";
        Jsh.eval(testCase, outs);
        String evalCase = "text1.txt text2.txt text3.txt";
        String[] expectedArray = evalCase.split(" ");
        Set<String> expectedSet = new HashSet<>(Arrays.asList(expectedArray));
        String result = outs.toString().trim();
        String[] resultArray = result.split(" ");
        Set<String> resultSet = new HashSet<>(Arrays.asList(resultArray));
        assertEquals(expectedSet, resultSet);
    }

    @Test
    public void test_glob_directory() {
        String testCase = "echo testDir/*.txt";
        Jsh.eval(testCase, outs);
        String evalCase = testDirectory + File.separator + "text1.txt " +
                          testDirectory + File.separator + "text2.txt " +
                          testDirectory + File.separator + "text3.txt";
        String[] expectedArray = evalCase.split(" ");
        Set<String> expectedSet = new HashSet<>(Arrays.asList(expectedArray));
        String result = outs.toString().trim();
        String[] resultArray = result.split(" ");
        Set<String> resultSet = new HashSet<>(Arrays.asList(resultArray));
        assertEquals(expectedSet, resultSet);
    }

    @Test
    public void test_echo_pipe() {
        String testCase = "cat testDir/text1.txt | echo";
        Jsh.eval(testCase, outs);
        String evalCase = "abcdefghi" + System.getProperty("line.separator") + "ofeijnwio";
        String[] expectedArray = evalCase.split(System.getProperty("line.separator"));
        Set<String> expectedSet = new HashSet<>(Arrays.asList(expectedArray));
        String result = outs.toString().trim();
        String[] resultArray = result.split(System.getProperty("line.separator"));
        Set<String> resultSet = new HashSet<>(Arrays.asList(resultArray));
        assertEquals(expectedSet, resultSet);
    }

    @Test
    public void test_output_redirection() throws FileNotFoundException {
        String[] args = {"echo aaa > testDir/testSubDir/text3.txt", "aaa"};
        Jsh.eval(args[0], outs);
        String fileLocation = testDirectory + File.separator + testSubDirectory + File.separator + "text3.txt";
        String data = "";
        try {
            File redirectionFile = new File(fileLocation);
            Scanner myReader = new Scanner(redirectionFile);
            while (myReader.hasNextLine()) {
                data = data + myReader.nextLine() + System.getProperty("line.separator");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        data = data.replace(System.getProperty("line.separator"), "").replace(" ", "");
        assertEquals(args[1], data);
        PrintWriter writer = new PrintWriter(fileLocation);
        writer.print("");
        writer.close();
    }
}
