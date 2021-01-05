package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import static org.junit.Assert.assertEquals;

public class PwdTest extends JshTest {

    public PwdTest() {
    }

    @Test
    public void test_pwd() {
        String aCase = "pwd";
        String expected = System.getProperty("user.dir");
        Jsh.eval(aCase, outs);
        assertEquals(expected, outs.toString().trim());
    }
}
