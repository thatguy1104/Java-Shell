package uk.ac.ucl.jsh.AppTests;
import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class UnsafeUniqTest extends JshTest {

    public UnsafeUniqTest() throws IOException {
    }

    @Test
    public void test_uniq() {
        String filepath = JshTest.testDirectory + File.separator;
        String[] args = {"_uniq " + filepath + "text3.txt", "AAA\nBBB\nAAA\nCCC\nccc\na\nb\nc\nd\ne\nf\ng\nh\ni"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_uniq_empty() {
        String filepath = JshTest.testDirectory + File.separator + JshTest.testSubDirectory + File.separator;
        String[] args = {"_uniq " + filepath + "text3.txt", ""};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_uniq_i() {
        String filepath = JshTest.testDirectory + File.separator + JshTest.testSubDirectory + File.separator;
        String[] args = {"_uniq -i " + filepath + "text2.txt", "A\nB\nC"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

//    @Test
//    public void test_uniq_stdin() {
//        String filepath = JshTest.testDirectory + File.separator + JshTest.testSubDirectory + File.separator;
//        String[] args = {"_uniq < " + filepath + "text2.txt", "A\na\nB\nb\nC\nc"};
//        Jsh.eval(args[0], this.out);
//        String full_string = getEvalResult(args[1]);
//        assertEquals(full_string, args[1]);
//    }

//    @Test
//    public void test_uniq_stdin_i() {
//        String filepath = JshTest.testDirectory + File.separator + JshTest.testSubDirectory + File.separator;
//        String[] args = {"_uniq -i < " + filepath + "text2.txt", "A\nB\nC"};
//        Jsh.eval(args[0], this.out);
//        String full_string = getEvalResult(args[1]);
//        assertEquals(full_string, args[1]);
//    }

    @Test
    public void test_tail_pipe_uniq_i() {
        String filepath = JshTest.testDirectory + File.separator + JshTest.testSubDirectory + File.separator;
        String[] args = {"tail -n 4 " + filepath + "text2.txt | _uniq -i", "B\nC"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }
}