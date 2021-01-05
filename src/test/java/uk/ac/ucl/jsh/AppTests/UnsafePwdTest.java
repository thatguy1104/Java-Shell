package uk.ac.ucl.jsh.AppTests;
import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import static org.junit.Assert.assertEquals;

public class UnsafePwdTest extends JshTest {

    public UnsafePwdTest() {
    }


    @Test
    public void test_unsafe_pwd() {
        String aCase = "_pwd";
        String expected = System.getProperty("user.dir");
        Jsh.eval(aCase, outs);
        assertEquals(expected, outs.toString().trim());
    }
}
