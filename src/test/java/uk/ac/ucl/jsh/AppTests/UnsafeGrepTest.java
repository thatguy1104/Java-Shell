package uk.ac.ucl.jsh.AppTests;
import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;

public class UnsafeGrepTest extends JshTest {

    public UnsafeGrepTest() {
    }

    @Test
    public void test_unsafe_grep_simple1() {
        String[] args = {"_grep d " + JshTest.testDirectory + File.separator + "text1.txt", "abcdefghi"};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void test_unsafe_grep_simple2() {
        String[] args = {"_grep BB " + JshTest.testDirectory + File.separator + "text2.txt", "BBB"};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void test_unsafe_grep_nothing() {
        String[] args = {"_grep aa " + JshTest.testDirectory + File.separator + "text3.txt", ""};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void test_unsafe_grep_anything() {
        String[] args = {"_grep '...' " + JshTest.testDirectory + File.separator + "text2.txt", "AAA" + System.getProperty("line.separator") +
                                                                                                "BBB" + System.getProperty("line.separator") +
                                                                                                "AAA"};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }
}
