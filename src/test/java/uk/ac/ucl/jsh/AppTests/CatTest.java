package uk.ac.ucl.jsh.AppTests;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.ac.ucl.jsh.Applications.Cat;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CatTest extends JshTest {

    public CatTest() throws IOException {
    }

    OutputStream outs = new ByteArrayOutputStream();

    @Test
    public void test_cat_simple() throws IOException {
        String file_name = JshTest.testDirectory + File.separator + "text1.txt";
        Jsh.eval("cat " + file_name, outs);
        //String result = getEvalResult(readFile(file_name));
        String expected = readFile(file_name);
        assertEquals(expected, outs.toString().trim());
    }

    @Test
    public void test_cat_redirection() throws IOException {
        String fileName = JshTest.testDirectory + File.separator + "text2.txt";
        Jsh.eval("cat < " + fileName, outs);
        //String result = getEvalResult(readFile(fileName));
        String expected = readFile(fileName);
        assertEquals(expected, outs.toString().trim());
    }

    @Test
    public void test_cat_input_sub() {
        String[] cases = {"echo `cat testDir/text1.txt`", "abcdefghiofeijnwio"};
        Jsh.eval(cases[0], outs);
        //String result = pwdSupplementary(cases[1]);
        assertEquals(cases[1], outs.toString().trim());
    }

    @Test
    public void test_cat_multi_file() {
        String[] cases = {"cat testDir/text1.txt testDir/text2.txt", "abcdefghi" + System.getProperty("line.separator") +
                                                                     "ofeijnwio" + System.getProperty("line.separator") +
                                                                     "AAA" + System.getProperty("line.separator") +
                                                                     "BBB" + System.getProperty("line.separator") +
                                                                     "AAA"};
        Jsh.eval(cases[0], outs);
        //String result = getEvalResult(cases[1]);
        assertEquals(cases[1], outs.toString().trim());
    }

    @Test
    public void test_cat_io() {
        String[] cases = {"cat < " + JshTest.testDirectory + File.separator + "text1.txt", "abcdefghi" + System.getProperty("line.separator") + "ofeijnwio"};
        Jsh.eval(cases[0], outs);
        //String result = getEvalResult(cases[1]);
        assertEquals(cases[1], outs.toString().trim());
    }

    @Test
    public void test_cat_double_quote() {
        String[] cases = {"cat < " + JshTest.testDirectory + File.separator + "text1.txt", "abcdefghi" + System.getProperty("line.separator")+ "ofeijnwio"};
        Jsh.eval(cases[0], outs);
        //String result = getEvalResult(cases[1]);
        assertEquals(cases[1], outs.toString().trim());
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
