package uk.ac.ucl.jsh.AppTests;
import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class UnsafeUniqTest extends JshTest {

    public UnsafeUniqTest() {
    }

    @Test
    public void testUnsafeUniqSimple() {
        String filepath = JshTest.testDirectory + File.separator;
        String[] args = {"_uniq " + filepath + "text3.txt", "AAA" + System.getProperty("line.separator") +
                                                            "BBB" + System.getProperty("line.separator") +
                                                            "AAA" + System.getProperty("line.separator") +
                                                            "CCC" + System.getProperty("line.separator") +
                                                            "ccc" + System.getProperty("line.separator") +
                                                            "a" + System.getProperty("line.separator") +
                                                            "b" + System.getProperty("line.separator") +
                                                            "c" + System.getProperty("line.separator") +
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
    public void testUnsafeUniqEmpty() {
        String filepath = JshTest.testDirectory + File.separator + JshTest.testSubDirectory + File.separator;
        String[] args = {"_uniq " + filepath + "text3.txt", ""};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void testUnsafeUniqI() {
        String filepath = JshTest.testDirectory + File.separator + JshTest.testSubDirectory + File.separator;
        String[] args = {"_uniq -i " + filepath + "text2.txt", "A" + System.getProperty("line.separator") +
                                                               "B" + System.getProperty("line.separator") +
                                                               "C"};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void testUnsafeTailPipeUniqI() {
        String filepath = JshTest.testDirectory + File.separator + JshTest.testSubDirectory + File.separator;
        String[] args = {"tail -n 4 " + filepath + "text2.txt | _uniq -i", "B" + System.getProperty("line.separator") +
                                                                           "C"};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }
}
