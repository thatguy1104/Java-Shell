package uk.ac.ucl.jsh.AppTests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.ac.ucl.jsh.Applications.Sort;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SortTest extends JshTest {

    public SortTest() {
    }

    @Test
    public void testSortSimple() {
        String filepath = JshTest.testDirectory + File.separator;
        String[] args = {"sort " + filepath +  "text1.txt", "abcdefghi" + System.getProperty("line.separator") +
                                                            "ofeijnwio"};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void testSortR() {
        String filepath = JshTest.testDirectory + File.separator;
        String[] args = {"sort -r " + filepath + "text1.txt", "ofeijnwio" + System.getProperty("line.separator") +
                                                              "abcdefghi"};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void testSortRStdin() {
        String filepath = JshTest.testDirectory + File.separator;
        String[] args = {"sort -r <" + filepath +  "text3.txt", "AAA" + System.getProperty("line.separator") +
                                                                "AAA" + System.getProperty("line.separator") +
                                                                "BBB" + System.getProperty("line.separator") +
                                                                "CCC" + System.getProperty("line.separator") +
                                                                "a" + System.getProperty("line.separator") +
                                                                "b" + System.getProperty("line.separator") +
                                                                "c" + System.getProperty("line.separator") +
                                                                "ccc" + System.getProperty("line.separator") +
                                                                "d" + System.getProperty("line.separator") +
                                                                "e" + System.getProperty("line.separator") +
                                                                "f" + System.getProperty("line.separator") +
                                                                "g" + System.getProperty("line.separator") +
                                                                "h" + System.getProperty("line.separator") +
                                                                "i"};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void testSortEmpty() {
        String filepath = JshTest.testDirectory + File.separator + JshTest.testSubDirectory + File.separator;
        String[] args = {"sort " + filepath +  "text3.txt", ""};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void testCatPipeSort() {
        String[] args = {"cat " + JshTest.testDirectory + File.separator + "text2.txt | sort", "AAA" + System.getProperty("line.separator") +
                                                                                               "AAA" + System.getProperty("line.separator") +
                                                                                               "BBB"};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testSortWrongNrArgs() throws IOException{
        Sort sort = new Sort();
        ArrayList<String> args = new ArrayList<>();
        args.add("sort");args.add("a");args.add("b");args.add("c");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("sort: wrong number of arguments");
        sort.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), outs);
    }

    @Test
    public void testSortWrongArgs() throws IOException{
        Sort sort = new Sort();
        ArrayList<String> args = new ArrayList<>();
        args.add("sort");args.add("a");args.add("b");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("sort: wrong argument a");
        sort.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), outs);
    }

    @Test
    public void testSortCannotOpen() throws IOException{
        Sort sort = new Sort();
        ArrayList<String> args = new ArrayList<>();
        args.add("sort");args.add("target");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("sort: cannot open target");
        sort.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), outs);
    }

    @Test
    public void testSortRCannotOpen() throws IOException{
        Sort sort = new Sort();
        ArrayList<String> args = new ArrayList<>();
        args.add("sort");args.add("-r");args.add("nonExistingTarget");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("sort: nonExistingTarget does not exist");
        sort.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), outs);
    }
}
