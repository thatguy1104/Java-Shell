package uk.ac.ucl.jsh.AppTests;
import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class UnsafeCutTest extends JshTest {

    public UnsafeCutTest() {
    }

    @Test
    public void testUnsafeCutSimple() {
        String[] arg = {"_cut -b 1 " + JshTest.testDirectory + File.separator + "text1.txt", "a" + System.getProperty("line.separator") +
                                                                                             "o"};
        Jsh.eval(arg[0], outs);
        assertEquals(arg[1], outs.toString().trim());
    }

    @Test
    public void testUnsafeCutSimple2() {
        String[] arg = {"_cut -b 1,2 " + JshTest.testDirectory + File.separator + "text1.txt", "ab" + System.getProperty("line.separator") +
                                                                                               "of"};
        Jsh.eval(arg[0], outs);
        assertEquals(arg[1], outs.toString().trim());
    }

    @Test
    public void testUnsafeCutSimple3() {
        String[] arg = {"_cut -b 1- " + JshTest.testDirectory + File.separator + "text1.txt", "abcdefghi" + System.getProperty("line.separator") +
                                                                                              "ofeijnwio"};
        Jsh.eval(arg[0], outs);
        assertEquals(arg[1], outs.toString().trim());
    }

    @Test
    public void testUnsafeCutSimple4() {
        String[] arg = {"_cut -b 1,3-4 " + JshTest.testDirectory + File.separator + "text1.txt", "acd" + System.getProperty("line.separator") +
                                                                                                 "oei"};
        Jsh.eval(arg[0], outs);
        assertEquals(arg[1], outs.toString().trim());
    }
}
