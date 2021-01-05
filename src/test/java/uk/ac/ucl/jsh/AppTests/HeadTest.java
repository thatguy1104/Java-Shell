package uk.ac.ucl.jsh.AppTests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.ac.ucl.jsh.Applications.Head;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class HeadTest extends JshTest {

    public HeadTest() {
    }

    @Test
    public void test_head_simple() throws IOException {
        String[] args = {"head " + testDirectory + File.separator + "text1.txt", readFile(testDirectory + File.separator + "text1.txt")};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void test_head_n_7() {
        String[] args = {"head -n 7 " + testDirectory + File.separator + "text3.txt", "AAA" + System.getProperty("line.separator") +
                                                                                      "BBB" + System.getProperty("line.separator") +
                                                                                      "AAA" + System.getProperty("line.separator") +
                                                                                      "CCC" + System.getProperty("line.separator") +
                                                                                      "ccc" + System.getProperty("line.separator") +
                                                                                      "a" + System.getProperty("line.separator") +
                                                                                      "b"};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void test_head_n_0() {
        String[] args = {"head -n 0 " + testDirectory + File.separator + "text1.txt", ""};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void test_head_subdir() {
        String[] args = {"head " + subDirString + File.separator + "text1.txt", "1" + System.getProperty("line.separator") +
                                                                                "2" + System.getProperty("line.separator") +
                                                                                "3" + System.getProperty("line.separator") +
                                                                                "4" + System.getProperty("line.separator") +
                                                                                "5"};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void test_head_subdir_2() {
        String[] args = {"head -n 2 " + subDirString + File.separator + "text2.txt", "A" + System.getProperty("line.separator") +
                                                                                     "a"};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void test_cat_head() {
        String[] args = {"cat " + JshTest.testDirectory + File.separator + "text2.txt | head", "AAA" + System.getProperty("line.separator") +
                                                                                               "BBB" + System.getProperty("line.separator") +
                                                                                               "AAA"};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void test_head_four_args_without_n() throws IOException{
        Head head = new Head();
        ArrayList<String> args = new ArrayList<>();
        args.add("head");args.add("a");args.add("b");args.add("c");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("head: wrong argument a");
        head.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), outs);
    }

    @Test
    public void test_head_three_args() throws IOException{
        Head head = new Head();
        ArrayList<String> args = new ArrayList<>();
        args.add("head");args.add("a");args.add("b");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("head: wrong arguments");
        head.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), outs);
    }

    @Test
    public void test_head_five_args() throws IOException{
        Head head = new Head();
        ArrayList<String> args = new ArrayList<>();
        args.add("head");args.add("a");args.add("b");args.add("c");args.add("d");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("head: wrong arguments");
        head.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), outs);
    }

    @Test
    public void test_head_missing_head_count() throws IOException{
        Head head = new Head();
        ArrayList<String> args = new ArrayList<>();
        args.add("head");args.add("-n");args.add("a");args.add("b");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("head: wrong argument a");
        head.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), outs);
    }
}
