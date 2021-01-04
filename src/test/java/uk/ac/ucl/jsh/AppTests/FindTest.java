package uk.ac.ucl.jsh.AppTests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.ac.ucl.jsh.Applications.Find;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class FindTest extends JshTest {

    public FindTest() throws IOException {
    }

    @Test
    public void test_find() {
        String testCase = "cd testDir; find -name text1.txt; cd ..";
        Jsh.eval(testCase, out);
        String evalCase = "." + File.separator + "text1.txt\n" +
                          "." + File.separator + testSubDirectory + File.separator + "text1.txt";
        String[] expectedArray = evalCase.split("\n");
        Set<String> expectedSet = new HashSet<>(Arrays.asList(expectedArray));
        String result = getEvalResult(evalCase);
        String[] resultArray = result.split("\n");
        Set<String> resultSet = new HashSet<>(Arrays.asList(resultArray));
        assertEquals(expectedSet, resultSet);
    }

    @Test
    public void test_find_dir() {
        String testCase = "find testDir -name text1.txt";
        Jsh.eval(testCase, out);
        String evalCase = testDirectory + File.separator + testSubDirectory + File.separator + "text1.txt\n" +
                          testDirectory + File.separator + "text1.txt";
        String[] expectedArray = evalCase.split("\n");
        Set<String> expectedSet = new HashSet<>(Arrays.asList(expectedArray));
        String result = getEvalResult(evalCase);
        String[] resultArray = result.split("\n");
        Set<String> resultSet = new HashSet<>(Arrays.asList(resultArray));
        assertEquals(expectedSet, resultSet);
    }

    @Test
    public void test_find_glob() {
        String testCase = "cd testDir; find -name *.txt; cd ..";
        Jsh.eval(testCase, out);
        String evalCase = "." + File.separator + testSubDirectory + File.separator + "text3.txt\n" +
                          "." + File.separator + "text2.txt\n" +
                          "." + File.separator + "text3.txt\n" +
                          "." + File.separator + "text1.txt\n" +
                          "." + File.separator + testSubDirectory + File.separator + "text2.txt\n" +
                          "." + File.separator + testSubDirectory + File.separator + "text1.txt\n";
        String[] expectedArray = evalCase.split("\n");
        Set<String> expectedSet = new HashSet<>(Arrays.asList(expectedArray));
        String result = getEvalResult(evalCase);
        String[] resultArray = result.split("\n");
        Set<String> resultSet = new HashSet<>(Arrays.asList(resultArray));
        assertEquals(expectedSet, resultSet);
    }

    @Test
    public void test_find_dir_glob() {
        String testCase = "find testDir/testSubDir -name *.txt";
        Jsh.eval(testCase, out);
        String evalCase = testDirectory + File.separator + testSubDirectory + File.separator + "text2.txt\n" +
                          testDirectory + File.separator + testSubDirectory + File.separator + "text3.txt\n" +
                          testDirectory + File.separator + testSubDirectory + File.separator + "text1.txt";
        String[] expectedArray = evalCase.split("\n");
        Set<String> expectedSet = new HashSet<>(Arrays.asList(expectedArray));
        String result = getEvalResult(evalCase);
        String[] resultArray = result.split("\n");
        Set<String> resultSet = new HashSet<>(Arrays.asList(resultArray));
        assertEquals(expectedSet, resultSet);
    }

    @Test
    public void test_find_subDir() {
        String testCase = "cd testDir/testSubDir; find -name text1.txt; cd ..; cd ..";
        Jsh.eval(testCase, out);
        String evalCase = "." + File.separator + "text1.txt";
        String[] expectedArray = evalCase.split("\n");
        Set<String> expectedSet = new HashSet<>(Arrays.asList(expectedArray));
        String result = getEvalResult(evalCase);
        String[] resultArray = result.split("\n");
        Set<String> resultSet = new HashSet<>(Arrays.asList(resultArray));
        assertEquals(expectedSet, resultSet);
    }

    @Test
    public void test_find_glob_word() {
        String testCase = "find testDir -name *.word";
        Jsh.eval(testCase, out);
        String evalCase = "";
        String[] expectedArray = evalCase.split("\n");
        Set<String> expectedSet = new HashSet<>(Arrays.asList(expectedArray));
        String result = getEvalResult(evalCase);
        String[] resultArray = result.split("\n");
        Set<String> resultSet = new HashSet<>(Arrays.asList(resultArray));
        assertEquals(expectedSet, resultSet);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void test_find_argument_error() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("find: missing arguments");
        Find find = new Find();
        ArrayList<String> args = new ArrayList<>();
        args.add("find");
        find.mainExec(args, System.getProperty("user.dir"), null, out);
    }

    @Test
    public void test_find_directory_error() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("find: no such directory");
        Find find = new Find();
        ArrayList<String> args = new ArrayList<>();
        args.add("find"); args.add("hello"); args.add("-name"); args.add("text1.txt");
        find.mainExec(args, System.getProperty("user.dir"), null, out);
    }
}
