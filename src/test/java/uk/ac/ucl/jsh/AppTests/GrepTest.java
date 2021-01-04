package uk.ac.ucl.jsh.AppTests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.ac.ucl.jsh.Applications.Cut;
import uk.ac.ucl.jsh.Applications.Grep;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class GrepTest extends JshTest {

    public GrepTest() throws IOException {
    }

    @Test
    public void test_1() {
        String[] args = {"grep d " + JshTest.testDirectory + File.separator + "text1.txt", "abcdefghi"};
        Jsh.eval(args[0], out);
        String result = getEvalResult(args[1]);
        assertEquals(args[1], result);
    }

    @Test
    public void test_2() {
        String[] args = {"grep BB " + JshTest.testDirectory + File.separator + "text2.txt", "BBB"};
        Jsh.eval(args[0], out);
        String result = getEvalResult(args[1]);
        assertEquals(args[1], result);
    }

    @Test
    public void test_3() {
        String[] args = {"grep a " + JshTest.testDirectory + File.separator + "text3.txt", ""};
        Jsh.eval(args[0], out);
        String result = getEvalResult(args[1]);
        assertEquals(args[1], result);
    }

    @Test
    public void test_4() {
        String[] args = {"grep '...' " + JshTest.testDirectory + File.separator + "text2.txt", "AAA\nBBB\nAAA"};
        Jsh.eval(args[0], out);
        String result = getEvalResult(args[1]);
        assertEquals(args[1], result);
    }

    @Test
    public void test_cat_grep() {
        String[] args = {"cat " + JshTest.testDirectory + File.separator + "text2.txt | grep A", "AAA\nAAA"};
        Jsh.eval(args[0], this.out);
        String result = getEvalResult(args[1]);
        assertEquals(args[1], result);
    }

    @Test
    public void test_grep_mult_files() {
        String filepath = JshTest.testDirectory + File.separator;
        String[] args = {"grep noExistingPattern " + filepath + "text2.txt " + filepath + "text1.txt", ""};
        Jsh.eval(args[0], this.out);
        String result = getEvalResult(args[1]);
        assertEquals(args[1], result);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void test_grep_fileArg() throws IOException {
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
}
