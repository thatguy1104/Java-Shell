package uk.ac.ucl.jsh.AppTests;
import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class UnsafeGrepTest extends JshTest {

    public UnsafeGrepTest() throws IOException {
    }

    @Test
    public void test_1() {
        String[] args = {"_grep d " + JshTest.testDirectory + File.separator + "text1.txt", "abcdefghi"};
        Jsh.eval(args[0], out);
        String result = getEvalResult(args[1]);
        assertEquals(args[1], result);
    }

    @Test
    public void test_2() {
        String[] args = {"_grep BB " + JshTest.testDirectory + File.separator + "text2.txt", "BBB"};
        Jsh.eval(args[0], out);
        String result = getEvalResult(args[1]);
        assertEquals(args[1], result);
    }

    @Test
    public void test_3() {
        String[] args = {"_grep a " + JshTest.testDirectory + File.separator + "text3.txt", ""};
        Jsh.eval(args[0], out);
        String result = getEvalResult(args[1]);
        assertEquals(args[1], result);
    }

    @Test
    public void test_4() {
        String[] args = {"_grep '...' " + JshTest.testDirectory + File.separator + "text2.txt", "AAA\nBBB\nAAA"};
        Jsh.eval(args[0], out);
        String result = getEvalResult(args[1]);
        assertEquals(args[1], result);
    }

//    @Test
//    public void test_5() {
//        String[] args = {"_grep AAA < cat testDir/text1.txt", "AAA"};
//        Jsh.eval(args[0], out);
//        String result = getEvalResult(args[1]);
//        assertEquals(args[1], result);
//    }
}
