package uk.ac.ucl.jsh.AppTests;
import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class UnsafeHeadTest extends JshTest {

    public UnsafeHeadTest() throws IOException {
    }

    @Test
    public void test_head_simple() throws IOException {
        String[] args = {"_head " + testDirectory + File.separator + "text1.txt", readFile(testDirectory + File.separator + "text1.txt")};
        Jsh.eval(args[0], this.out);
        String result = getEvalResult(args[1]);
        assertEquals(args[1], result);
    }

    @Test
    public void test_head_7() {
        String[] args = {"_head -n 7 " + testDirectory + File.separator + "text3.txt", "AAA\nBBB\nAAA\nCCC\nccc\na\nb"};
        Jsh.eval(args[0], this.out);
        String result = getEvalResult(args[1]);
        assertEquals(args[1], result);
    }

    @Test
    public void test_head_0() {
        String[] args = {"_head -n 0 " + testDirectory + File.separator + "text1.txt", ""};
        Jsh.eval(args[0], this.out);
        String result = getEvalResult(args[1]);
        assertEquals(args[1], result);
    }

    @Test
    public void test_head_subdir() {
        String[] args = {"_head " + subDirString + File.separator + "text1.txt", "1\n2\n3\n4\n5"};
        Jsh.eval(args[0], this.out);
        String result = getEvalResult(args[1]);
        assertEquals(args[1], result);
    }

    @Test
    public void test_head_subdir_2() {
        String[] args = {"_head -n 2 " + subDirString + File.separator + "text2.txt", "A\na"};
        Jsh.eval(args[0], this.out);
        String result = getEvalResult(args[1]);
        assertEquals(args[1], result);
    }
}
