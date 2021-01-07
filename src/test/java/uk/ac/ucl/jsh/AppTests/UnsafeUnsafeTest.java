package uk.ac.ucl.jsh.AppTests;
import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import static org.junit.Assert.assertEquals;

public class UnsafeUnsafeTest extends JshTest {

    public UnsafeUnsafeTest() {
    }

    @Test
    public void testUnsafeNothing() {
        String expected = "_cat: missing arguments";
        Jsh.eval("_cat", outs);
        String result = outs.toString().replaceAll(System.getProperty("line.separator"), "");
        assertEquals(expected, result);
    }

    @Test
    public void testUnsafeError() {
        String expected = "_cat: file does not exist";
        Jsh.eval("_cat yes.txt", outs);
        String result = outs.toString().replaceAll(System.getProperty("line.separator"), "");
        assertEquals(expected, result);
    }
}