package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TailTest extends JshTest {

    public TailTest() throws IOException {
    }

    @Test
    public void test_tail_n_0() {
        String[] args = {"tail -n 0 " + JshTest.testDirectory + File.separator + "text2.txt", ""};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test //TODO check if this is correct @Aashvin @Albert
    public void test_tail_n_1() {
        String[] args = {"tail -n 1 " + JshTest.testDirectory + File.separator + "text1.txt", "ofeijnwio"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_tail_n_3_caps() {
        String[] args = {"tail -n 3 " + JshTest.testDirectory + File.separator + "text2.txt", "AAA\nBBB\nAAA"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_tail_n_3() {
        String[] args = {"tail -n 3 " + JshTest.testDirectory + File.separator + "text3.txt", "g\nh\ni"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    @Test
    public void test_cat_tail() {
        String[] args = {"cat " + JshTest.testDirectory + File.separator + "text2.txt | tail", "AAA\nBBB\nAAA"};
        Jsh.eval(args[0], this.out);
        String full_string = getEvalResult(args[1]);
        assertEquals(full_string, args[1]);
    }

    public void runAllTests() {
        test_tail_n_0();
        test_tail_n_1();
        test_tail_n_3();
        test_tail_n_3_caps();
        test_cat_tail();
    }
}
