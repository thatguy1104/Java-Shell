package uk.ac.ucl.jsh.AppTests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.ac.ucl.jsh.Applications.Tail;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TailTest extends JshTest {

    public TailTest() throws IOException {
    }

    @Test
    public void test_tail_n_0() {
        String[] args = {"tail -n 0 " + JshTest.testDirectory + File.separator + "text2.txt", ""};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_tail_n_1() {
        String[] args = {"tail -n 1 " + JshTest.testDirectory + File.separator + "text1.txt", "ofeijnwio"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_tail_n_3_caps() {
        String[] args = {"tail -n 3 " + JshTest.testDirectory + File.separator + "text2.txt", "AAA\nBBB\nAAA"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_tail_n_3() {
        String[] args = {"tail -n 3 " + JshTest.testDirectory + File.separator + "text3.txt", "g\nh\ni"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_cat_tail() {
        String[] args = {"cat " + JshTest.testDirectory + File.separator + "text2.txt | tail", "AAA\nBBB\nAAA"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    //@Test TODO DEBUG SAME AS OTHERS
    public void test_tail_no_args() throws IOException{
        Tail tail = new Tail();
        ArrayList<String> args = new ArrayList<>();
        args.add("tail");
        exceptionRule.expect(RuntimeException.class);
        tail.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }

    @Test
    public void test_tail_four_args_without_n() throws IOException{
        Tail tail = new Tail();
        ArrayList<String> args = new ArrayList<>();
        args.add("tail");args.add("a");args.add("b");args.add("c");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("tail: wrong argument a");
        tail.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }

    @Test
    public void test_tail_three_args() throws IOException{
        Tail tail = new Tail();
        ArrayList<String> args = new ArrayList<>();
        args.add("tail");args.add("a");args.add("b");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("tail: wrong arguments");
        tail.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }

    @Test
    public void test_tail_five_args() throws IOException{
        Tail tail = new Tail();
        ArrayList<String> args = new ArrayList<>();
        args.add("tail");args.add("a");args.add("b");args.add("c");args.add("d");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("tail: wrong arguments");
        tail.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }

    @Test
    public void test_tail_nonexistentFile() throws IOException{
        Tail tail = new Tail();
        ArrayList<String> args = new ArrayList<>();
        args.add("tail");args.add("nonexistentFile");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("tail: nonexistentFile does not exist");
        tail.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }

    @Test
    public void test_tail_missing_tail_count() throws IOException{
        Tail tail = new Tail();
        ArrayList<String> args = new ArrayList<>();
        args.add("tail");args.add("-n");args.add("a");args.add("b");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("tail: wrong argument a");
        tail.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }
}
