package uk.ac.ucl.jsh.AppTests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.ac.ucl.jsh.Applications.Grep;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GrepTest extends JshTest {

    public GrepTest() throws IOException {
    }
    OutputStream outs = new ByteArrayOutputStream();

    @Test
    public void test_grep_simple_1() {
        String[] args = {"grep d " + JshTest.testDirectory + File.separator + "text1.txt", "abcdefghi"};
        Jsh.eval(args[0], outs);
        //String result = getEvalResult(args[1]);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void test_grep_simple_2() {
        String[] args = {"grep BB " + JshTest.testDirectory + File.separator + "text2.txt", "BBB"};
        Jsh.eval(args[0], outs);
        //String result = getEvalResult(args[1]);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void test_grep_nothing() {
        String[] args = {"grep aa " + JshTest.testDirectory + File.separator + "text3.txt", ""};
        Jsh.eval(args[0], outs);
        //String result = getEvalResult(args[1]);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void test_grep_anything() {
        String[] args = {"grep '...' " + JshTest.testDirectory + File.separator + "text2.txt", "AAA" + System.getProperty("line.separator") +
                                                                                               "BBB" + System.getProperty("line.separator") +
                                                                                               "AAA"};
        Jsh.eval(args[0], outs);
        //String result = getEvalResult(args[1]);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void test_grep_dot_no_match() {
        String[] args = {"grep '.....' " + JshTest.testDirectory + File.separator + "text2.txt", ""};
        Jsh.eval(args[0], outs);
        //String result = getEvalResult(args[1]);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void test_cat_grep() {
        String[] args = {"cat " + JshTest.testDirectory + File.separator + "text2.txt | grep A", "AAA" + System.getProperty("line.separator") +
                                                                                                 "AAA"};
        Jsh.eval(args[0], outs);
        //String result = getEvalResult(args[1]);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void test_grep_mult_files() {
        String filepath = JshTest.testDirectory + File.separator;
        String[] args = {"grep noExistingPattern " + filepath + "text2.txt " + filepath + "text1.txt", ""};
        Jsh.eval(args[0], outs);
        //String result = getEvalResult(args[1]);
        assertEquals(args[1], outs.toString().trim());
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void test_grep_file_arg() throws IOException {
        Grep grep = new Grep();
        ArrayList<String> args = new ArrayList<>();
        args.add("grep"); args.add("AAA"); args.add("filethatdoesnotexiste.txt");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("grep: wrong file argument");
        grep.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }

    @Test
    public void test_grep_wrong_nr_args() throws IOException {
        Grep grep = new Grep();
        ArrayList<String> args = new ArrayList<>();
        args.add("grep"); args.add("AAA");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("grep: wrong number of arguments");
        grep.mainExec(args, System.getProperty("user.dir"), null, out);
    }

    @Test
    public void test_grep_dir_but_not_file() throws IOException {
        Grep grep = new Grep();
        ArrayList<String> args = new ArrayList<>();
        args.add("grep"); args.add("A");args.add("tools");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("grep: wrong file argument");
        grep.mainExec(args, System.getProperty("user.dir"), null, out);
    }

}
