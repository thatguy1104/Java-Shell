package uk.ac.ucl.jsh.AppTests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.ac.ucl.jsh.Applications.Cut;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CutTest extends JshTest {

    public CutTest() throws IOException {
    }

    @Test
    public void test_cut_simple() {
        String[] arg = {"cut -b 1 " + JshTest.testDirectory + File.separator + "text1.txt", "a\no"};
        Jsh.eval(arg[0], out);
        String result = getEvalResult(arg[1]);
        assertEquals(arg[1], result);
    }

    @Test
    public void test_cut_simple_2() {
        String[] arg = {"cut -b 1,2 " + JshTest.testDirectory + File.separator + "text1.txt", "ab\nof"};
        Jsh.eval(arg[0], out);
        String result = getEvalResult(arg[1]);
        assertEquals(arg[1], result);
    }

    @Test
    public void test_cut_simple_3() {
        String[] arg = {"cut -b 1- " + JshTest.testDirectory + File.separator + "text1.txt", "abcdefghi\nofeijnwio"};
        Jsh.eval(arg[0], out);
        String result = getEvalResult(arg[1]);
        assertEquals(arg[1], result);
    }

    @Test
    public void test_cut_simple_4() {
        String[] arg = {"cut -b 1,3-4 " + JshTest.testDirectory + File.separator + "text1.txt", "acd\noei"};
        Jsh.eval(arg[0], out);
        String result = getEvalResult(arg[1]);
        assertEquals(arg[1], result);
    }

    @Test
    public void test_cut_echo_pipe() {
        String[] arg = {"echo abc | cut -b 1", "a"};
        Jsh.eval(arg[0], out);
        String result = getEvalResult(arg[1]);
        assertEquals(arg[1], result);
    }

    @Test
    public void test_unsafe_cut_echo_pipe_2() {
        String[] arg = {"echo abc | cut -b -1,2-", "abc"};
        Jsh.eval(arg[0], out);
        String result = getEvalResult(arg[1]);
        System.out.println(result);
        assertEquals(arg[1], result);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void test_cut_fileTest() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("cut: file does not exist");
        Cut cut = new Cut();
        ArrayList<String> args = new ArrayList<>();
        args.add("cut"); args.add("-b"); args.add("1"); args.add("nonexistentfilename.txt");
        cut.mainExec(args, System.getProperty("user.dir"), null, out);
    }

    @Test
    public void test_cut_wrong_args() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("cut: wrong arguments");
        Cut cut = new Cut();
        ArrayList<String> args = new ArrayList<>();
        args.add("cut"); args.add("1,2"); args.add("text1.txt");
        cut.mainExec(args, System.getProperty("user.dir"), null, out);
    }

    @Test
    public void test_cut_byte_index() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("cut: byte index specified does not exist");
        Cut cut = new Cut();
        ArrayList<String> args = new ArrayList<>();
        args.add("cut"); args.add("-b"); args.add("1, -1, 0, -0"); args.add(JshTest.testDirectory + File.separator + "text1.txt");
        cut.mainExec(args, System.getProperty("user.dir"), new FileInputStream(JshTest.testDirectory + File.separator + "text1.txt"), out);
    }

    @Test
    public void test_cut_zero_arg() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("cut: could not convert arguments");
        Cut cut = new Cut();
        ArrayList<String> args = new ArrayList<>();
        args.add("cut"); args.add("-b"); args.add("-0"); args.add(JshTest.testDirectory + File.separator + "text1.txt");
        cut.mainExec(args, System.getProperty("user.dir"), new FileInputStream(JshTest.testDirectory + File.separator + "text1.txt"), out);
    }

    @Test
    public void test_cut_missing_args() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("cut: missing arguments");
        Cut cut = new Cut();
        ArrayList<String> args = new ArrayList<>();
        args.add(JshTest.testDirectory + File.separator + "text1.txt");
        cut.mainExec(args, System.getProperty("user.dir"), new FileInputStream(JshTest.testDirectory + File.separator + "text1.txt"), out);
    }

    @Test
    public void test_cut_wrong_file3() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("cut: wrong argument " + JshTest.testDirectory + File.separator + "text1.txt");
        Cut cut = new Cut();
        ArrayList<String> args = new ArrayList<>();
        args.add("cut");  args.add(JshTest.testDirectory + File.separator + "text1.txt");
        cut.mainExec(args, System.getProperty("user.dir"), null, out);
    }
}
