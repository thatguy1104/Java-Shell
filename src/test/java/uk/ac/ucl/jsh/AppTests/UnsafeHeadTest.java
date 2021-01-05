package uk.ac.ucl.jsh.AppTests;
import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class UnsafeHeadTest extends JshTest {

    public UnsafeHeadTest() {
    }

    @Test
    public void test_unsafe_head_simple() throws IOException {
        String[] args = {"_head " + testDirectory + File.separator + "text1.txt", readFile(testDirectory + File.separator + "text1.txt")};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void test_unsafe_head_7() {
        String[] args = {"_head -n 7 " + testDirectory + File.separator + "text3.txt", "AAA" + System.getProperty("line.separator") +
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
    public void test_unsafe_head_0() {
        String[] args = {"_head -n 0 " + testDirectory + File.separator + "text1.txt", ""};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void test_unsafe_head_subdir() {
        String[] args = {"_head " + subDirString + File.separator + "text1.txt", "1" + System.getProperty("line.separator") +
                                                                                 "2" + System.getProperty("line.separator") +
                                                                                 "3" + System.getProperty("line.separator") +
                                                                                 "4" + System.getProperty("line.separator") +
                                                                                 "5"};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void test_unsafe_head_subdir_2() {
        String[] args = {"_head -n 2 " + subDirString + File.separator + "text2.txt", "A" + System.getProperty("line.separator") +
                                                                                      "a"};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }
}
