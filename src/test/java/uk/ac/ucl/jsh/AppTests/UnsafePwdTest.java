package uk.ac.ucl.jsh.AppTests;
import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;

public class UnsafePwdTest extends JshTest {

    public UnsafePwdTest() throws IOException {
    }

    OutputStream outs = new ByteArrayOutputStream();

    @Test
    public void test_unsafe_pwd() {
        String aCase = "_pwd";
        String expected = System.getProperty("user.dir");
        Jsh.eval(aCase, outs);
        //String result = pwdSupplementary(expected);
        assertEquals(expected, outs.toString().trim());
    }
}
