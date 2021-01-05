package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;

public class PwdTest extends JshTest {

    public PwdTest() throws IOException {
    }

    OutputStream outs = new ByteArrayOutputStream();

    @Test
    public void test_pwd() {
        String aCase = "pwd";
        String expected = System.getProperty("user.dir");
        Jsh.eval(aCase, outs);
        //String result = pwdSupplementary(expected);
        assertEquals(expected, outs.toString().trim());
    }
}
