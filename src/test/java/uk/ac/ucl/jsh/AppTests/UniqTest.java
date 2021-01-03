package uk.ac.ucl.jsh.AppTests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.ac.ucl.jsh.Applications.Uniq;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshGrammarParser;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class UniqTest extends JshTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    public UniqTest() throws IOException {
    }

    @Test
    public void test_uniq() {
        String filepath = JshTest.testDirectory + File.separator;
        String[] args = {"uniq " + filepath + "text3.txt", "AAA\nBBB\nAAA\nCCC\nccc\na\nb\nc\nd\ne\nf\ng\nh\ni"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_uniq_empty() {
        String filepath = JshTest.testDirectory + File.separator + JshTest.testSubDirectory + File.separator;
        String[] args = {"uniq " + filepath + "text3.txt", ""};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_uniq_i() {
        String filepath = JshTest.testDirectory + File.separator + JshTest.testSubDirectory + File.separator;
        String[] args = {"uniq -i " + filepath + "text2.txt", "A\nB\nC"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

//    @Test
    public void test_uniq_stdin() {
        String filepath = JshTest.testDirectory + File.separator + JshTest.testSubDirectory + File.separator;
        String[] args = {"uniq < " + filepath + "text2.txt", "A\na\nB\nb\nC\nc"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

//    @Test
    public void test_uniq_stdin_i() {
        String filepath = JshTest.testDirectory + File.separator + JshTest.testSubDirectory + File.separator;
        String[] args = {"uniq -i < " + filepath + "text2.txt", "A\nB\nC"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_tail_pipe_uniq_i() {
        String filepath = JshTest.testDirectory + File.separator + JshTest.testSubDirectory + File.separator;
        String[] args = {"tail -n 4 " + filepath + "text2.txt | uniq -i", "B\nC"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_uniq_error() throws IOException{
        Uniq uniq = new Uniq();
        ArrayList<String> args = new ArrayList<>();
        args.add("uniq");args.add("a");args.add("b");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("uniq: wrong argument a");
        uniq.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }

    @Test
    public void test_uniq_wrong_args2() throws IOException{
        Uniq uniq = new Uniq();
        ArrayList<String> args = new ArrayList<>();
        args.add("uniq");args.add("a");args.add("b");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("uniq: wrong argument a");
        uniq.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }

    @Test
    public void test_uniq_empty_args() throws IOException{
        Uniq uniq = new Uniq();
        ArrayList<String> args = new ArrayList<>();
        args.add("uniq");args.add("nonExistentFile");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("uniq: nonExistentFile does not exist");
        uniq.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }

    @Test
    public void test_uniq_cannot_open() throws IOException{
        Uniq uniq = new Uniq();
        ArrayList<String> args = new ArrayList<>();
        args.add("uniq");args.add("target");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("uniq: cannot open target");
        uniq.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }

    @Test
    public void test_uniq_no_args() throws IOException{
        Uniq uniq = new Uniq();
        ArrayList<String> args = new ArrayList<>();
        args.add("uniq");
        exceptionRule.expect(RuntimeException.class);
        uniq.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }

    @Test
    public void test_uniq_too_many_args() throws IOException{
        Uniq uniq = new Uniq();
        ArrayList<String> args = new ArrayList<>();
        args.add("uniq");args.add("a");args.add("b");args.add("c");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("uniq: too many arguments");
        uniq.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }
}
