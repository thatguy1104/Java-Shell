package uk.ac.ucl.jsh.AppTests;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.JUnit38ClassRunner;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import uk.ac.ucl.jsh.Applications.Cat;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit38ClassRunner.class)
public class CatTest extends JshTest {

    public CatTest() throws IOException {
    }

    @Test
    public void test_cat_simple() throws IOException {
        OutputStream out = new ByteArrayOutputStream();
        String file_name = JshTest.testDirectory + File.separator + "text1.txt";
        Jsh.eval("cat " + file_name, out);
        String result = getEvalResult(readFile(file_name));
        String expected = readFile(file_name);
        assertEquals(expected, result);
    }

    @Test
    public void test_cat_redirection() throws IOException {
        String fileName = JshTest.testDirectory + File.separator + "text2.txt";
        Jsh.eval("cat < " + fileName, out);
        String result = getEvalResult(readFile(fileName));
        String expected = readFile(fileName);
        assertEquals(expected, result);
    }

    @Test
    public void test_cat_input_sub() {
        String[] cases = {"echo `cat testDir/text1.txt`", "abcdefghiofeijnwio"};
        Jsh.eval(cases[0], out);
        String result = pwdSupplementary(cases[1]);
        assertEquals(cases[1], result);
    }

    @Test
    public void test_cat_multi_file() {
        String[] cases = {"cat testDir/text1.txt testDir/text2.txt", "abcdefghi\nofeijnwio\nAAA\nBBB\nAAA"};
        Jsh.eval(cases[0], out);
        String result = getEvalResult(cases[1]);
        assertEquals(cases[1], result);
    }

    @Test
    public void test_cat_io() {
        String[] cases = {"cat < " + JshTest.testDirectory + File.separator + "text1.txt", "abcdefghi\nofeijnwio"};
        Jsh.eval(cases[0], out);
        String result = getEvalResult(cases[1]);
        assertEquals(cases[1], result);
    }

    @Test
    public void test_cat_double_quote() {
        String[] cases = {"cat < " + JshTest.testDirectory + File.separator + "text1.txt", "abcdefghi\nofeijnwio"};
        Jsh.eval(cases[0], out);
        String result = getEvalResult(cases[1]);
        assertEquals(cases[1], result);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void test_cat_fileTest() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("cat: file does not exist");
        Cat cat = new Cat();
        ArrayList<String> args = new ArrayList<>();
        args.add("cat"); args.add("nonexistentfilename.txt");
        cat.mainExec(args, System.getProperty("user.dir"), null, out);
    }

    @Test
    public void test_cat_no_args() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("cat: missing arguments");
        Cat cat = new Cat();
        ArrayList<String> args = new ArrayList<>();
        args.add("cat");
        cat.mainExec(args, System.getProperty("user.dir"), null, out);
    }
}
