package uk.ac.ucl.jsh.AppTests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.ac.ucl.jsh.Applications.Sort;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SortTest extends JshTest {

    public SortTest() throws IOException {
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void test_sort() {
        String filepath = JshTest.testDirectory + File.separator;
        String[] args = {"sort " + filepath +  "text1.txt", "abcdefghi\nofeijnwio"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_sort_r() {
        String filepath = JshTest.testDirectory + File.separator;
        String[] args = {"sort -r " + filepath + "text1.txt", "ofeijnwio\nabcdefghi"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_sort_r_stdin() {
        String filepath = JshTest.testDirectory + File.separator;
        String[] args = {"sort -r <" + filepath +  "text3.txt", "AAA\nAAA\nBBB\nCCC\na\nb\nc\nccc\nd\ne"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_sort_empty() {
        String filepath = JshTest.testDirectory + File.separator + JshTest.testSubDirectory + File.separator;
        String[] args = {"sort " + filepath +  "text3.txt", ""};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    //@Test //TODO ERROR
    public void test_substitution_sort_find_r() {
        //String filepath = JshTest.testDirectory;
        String filepath2 = JshTest.testDirectory + File.separator + JshTest.testSubDirectory;
        String[] args = {"cat `find " + filepath2 + " -name '*.txt'` | sort -r", "1\n2\n3\n4\n5\nA\nB\nC\na\nb\nc"};
        //String[] args = {"cat `find " + filepath + " -name '*.txt'` | sort -r", "1\n2\n3\n4\n5\nA\nAAA\nAAA\nAAA\nAAA\nB\nBBB\nBBB\nC\nCCC\na\na\nabcdefghi\nb\nb\nc\nc\nccc\nd\ne\nf\ng\nh\ni\nofeijnwio"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_cat_grep() {
        String[] args = {"cat " + JshTest.testDirectory + File.separator + "text2.txt | sort", "AAA\nAAA\nBBB"};
        Jsh.eval(args[0], this.out);
        String result = getEvalResult(args[1]);
        assertEquals(args[1], result);
    }

    @Test
    public void test_sort_wrong_nr_args() throws IOException{
        Sort sort = new Sort();
        ArrayList<String> args = new ArrayList<>();
        args.add("sort");args.add("a");args.add("b");args.add("c");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("sort: wrong number of arguments");
        sort.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }

    @Test
    public void test_sort_wrong_args() throws IOException{
        Sort sort = new Sort();
        ArrayList<String> args = new ArrayList<>();
        args.add("sort");args.add("a");args.add("b");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("sort: wrong argument a");
        sort.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }

    @Test
    public void test_sort_cannot_open() throws IOException{
        Sort sort = new Sort();
        ArrayList<String> args = new ArrayList<>();
        args.add("sort");args.add("target");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("sort: cannot open target");
        sort.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }

    @Test
    public void test_sort_r_cannot_open() throws IOException{
        Sort sort = new Sort();
        ArrayList<String> args = new ArrayList<>();
        args.add("sort");args.add("-r");args.add("nonExistingTarget");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("sort: nonExistingTarget does not exist");
        sort.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }

    //@Test
    public void test_sort_missing_arg() throws IOException{
        Sort sort = new Sort();
        ArrayList<String> args = new ArrayList<>();
        args.add("sort");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("sort: missing arguments");
        sort.mainExec(args, System.getProperty("user.dir"), null, out);
    }



}
