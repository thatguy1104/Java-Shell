package uk.ac.ucl.jsh.AppTests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.ac.ucl.jsh.Applications.Head;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class HeadTest extends JshTest {

    public HeadTest() throws IOException {
    }

    @Test
    public void test_head_simple() throws IOException {
        String[] args = {"head " + testDirectory + File.separator + "text1.txt", readFile(testDirectory + File.separator + "text1.txt")};
        Jsh.eval(args[0], this.out);
        String result = getEvalResult(args[1]);
        assertEquals(args[1], result);
    }

    @Test
    public void test_head_7() {
        String[] args = {"head -n 7 " + testDirectory + File.separator + "text3.txt", "AAA\nBBB\nAAA\nCCC\nccc\na\nb"};
        Jsh.eval(args[0], this.out);
        String result = getEvalResult(args[1]);
        assertEquals(args[1], result);
    }

    @Test
    public void test_head_0() {
        String[] args = {"head -n 0 " + testDirectory + File.separator + "text1.txt", ""};
        Jsh.eval(args[0], this.out);
        String result = getEvalResult(args[1]);
        assertEquals(args[1], result);
    }

    @Test
    public void test_head_subdir() {
        String[] args = {"head " + subDirString + File.separator + "text1.txt", "1\n2\n3\n4\n5"};
        Jsh.eval(args[0], this.out);
        String result = getEvalResult(args[1]);
        assertEquals(args[1], result);
    }

    @Test
    public void test_head_subdir_2() {
        String[] args = {"head -n 2 " + subDirString + File.separator + "text2.txt", "A\na"};
        Jsh.eval(args[0], this.out);
        String result = getEvalResult(args[1]);
        assertEquals(args[1], result);
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
        head.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }

    @Test
    public void test_head_three_args() throws IOException{
        Head head = new Head();
        ArrayList<String> args = new ArrayList<>();
        args.add("head");args.add("a");args.add("b");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("head: wrong arguments");
        head.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }

    @Test
    public void test_head_five_args() throws IOException{
        Head head = new Head();
        ArrayList<String> args = new ArrayList<>();
        args.add("head");args.add("a");args.add("b");args.add("c");args.add("d");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("head: wrong arguments");
        head.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }

    //@Test TODO
    public void test_head_nonexistentFile() throws IOException{
        Head head = new Head();
        ArrayList<String> args = new ArrayList<>();
        args.add("head");args.add("nonexistentFile");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("head: nonexistentFile does not exist");
        head.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }

    @Test
    public void test_head_missing_head_count() throws IOException{
        Head head = new Head();
        ArrayList<String> args = new ArrayList<>();
        args.add("head");args.add("-n");args.add("a");args.add("b");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("head: wrong argument a");
        head.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }

}
