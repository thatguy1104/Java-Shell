package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class UnsafeFindTest extends JshTest {

    public UnsafeFindTest() {
    }

    @Test
    public void test_unsafe_find() {
        String testCase = "cd testDir; _find -name text1.txt; cd ..";
        Jsh.eval(testCase, outs);
        String evalCase = "." + File.separator + "text1.txt\n" +
                "." + File.separator + testSubDirectory + File.separator + "text1.txt";
        String[] expectedArray = evalCase.split("\n");
        Set<String> expectedSet = new HashSet<>(Arrays.asList(expectedArray));
        String result = outs.toString().trim();
        String[] resultArray = result.split(System.getProperty("line.separator"));
        Set<String> resultSet = new HashSet<>(Arrays.asList(resultArray));
        assertEquals(expectedSet, resultSet);
    }

    @Test
    public void test_unsafe_find_dir() {
        String testCase = "_find testDir -name text1.txt";
        Jsh.eval(testCase, outs);
        String evalCase = testDirectory + File.separator + testSubDirectory + File.separator + "text1.txt\n" +
                testDirectory + File.separator + "text1.txt";
        String[] expectedArray = evalCase.split("\n");
        Set<String> expectedSet = new HashSet<>(Arrays.asList(expectedArray));
        String result = outs.toString().trim();
        String[] resultArray = result.split(System.getProperty("line.separator"));
        Set<String> resultSet = new HashSet<>(Arrays.asList(resultArray));
        assertEquals(expectedSet, resultSet);
    }

    @Test
    public void test_unsafe_find_glob() {
        String testCase = "cd testDir; _find -name *.txt; cd ..";
        Jsh.eval(testCase, outs);
        String evalCase = "." + File.separator + testSubDirectory + File.separator + "text3.txt\n" +
                "." + File.separator + "text2.txt\n" +
                "." + File.separator + "text3.txt\n" +
                "." + File.separator + "text1.txt\n" +
                "." + File.separator + testSubDirectory + File.separator + "text2.txt\n" +
                "." + File.separator + testSubDirectory + File.separator + "text1.txt";
        String[] expectedArray = evalCase.split("\n");
        Set<String> expectedSet = new HashSet<>(Arrays.asList(expectedArray));
        String result = outs.toString().trim();
        String[] resultArray = result.split(System.getProperty("line.separator"));
        Set<String> resultSet = new HashSet<>(Arrays.asList(resultArray));
        assertEquals(expectedSet, resultSet);
    }

    @Test
    public void test_unsafe_find_dir_glob() {
        String testCase = "_find testDir/testSubDir -name *.txt";
        Jsh.eval(testCase, outs);
        String evalCase = testDirectory + File.separator + testSubDirectory + File.separator + "text2.txt\n" +
                testDirectory + File.separator + testSubDirectory + File.separator + "text3.txt\n" +
                testDirectory + File.separator + testSubDirectory + File.separator + "text1.txt";
        String[] expectedArray = evalCase.split("\n");
        Set<String> expectedSet = new HashSet<>(Arrays.asList(expectedArray));
        String result = outs.toString().trim();
        String[] resultArray = result.split(System.getProperty("line.separator"));
        Set<String> resultSet = new HashSet<>(Arrays.asList(resultArray));
        assertEquals(expectedSet, resultSet);
    }

    @Test
    public void test_unsafe_find_subDir() {
        String testCase = "cd testDir/testSubDir; _find -name text1.txt; cd ..; cd ..";
        Jsh.eval(testCase, outs);
        String evalCase = "." + File.separator + "text1.txt";
        String[] expectedArray = evalCase.split("\n");
        Set<String> expectedSet = new HashSet<>(Arrays.asList(expectedArray));
        String result = outs.toString().trim();
        String[] resultArray = result.split(System.getProperty("line.separator"));
        Set<String> resultSet = new HashSet<>(Arrays.asList(resultArray));
        assertEquals(expectedSet, resultSet);
    }
}
