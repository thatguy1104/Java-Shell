package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.Applications.Find;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;
import java.io.IOException;
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
                          "." + File.separator + testSubDirectory + File.separator + "text1.txt";
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
}
