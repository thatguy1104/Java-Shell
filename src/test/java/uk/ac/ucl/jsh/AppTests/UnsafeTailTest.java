package uk.ac.ucl.jsh.AppTests;
import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class UnsafeTailTest extends JshTest {

    public UnsafeTailTest() {
    }

    @Test
    public void testUnsafeTailN0() {
        String[] args = {"_tail -n 0 " + JshTest.testDirectory + File.separator + "text2.txt", ""};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void testUnsafeTailN1() {
        String[] args = {"_tail -n 1 " + JshTest.testDirectory + File.separator + "text1.txt", "ofeijnwio"};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void testUnsafeTailN3Caps() {
        String[] args = {"_tail -n 3 " + JshTest.testDirectory + File.separator + "text2.txt", "AAA" + System.getProperty("line.separator") +
                                                                                               "BBB" + System.getProperty("line.separator") +
                                                                                               "AAA"};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void testUnsafeTailN3() {
        String[] args = {"_tail -n 3 " + JshTest.testDirectory + File.separator + "text3.txt", "g" + System.getProperty("line.separator") +
                                                                                               "h" + System.getProperty("line.separator") +
                                                                                               "i"};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }
}
