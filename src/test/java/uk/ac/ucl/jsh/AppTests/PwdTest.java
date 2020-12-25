package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PwdTest extends JshTest {

    public PwdTest() throws IOException {
    }

    @Test
    public void test_1() {
        String aCase = "pwd";
        String expected = System.getProperty("user.dir");
        Jsh.eval(aCase, this.out);
        String result = pwdSupplementary(expected);
        assertEquals(expected, result);
    }

    public void runAllTests() {
        test_1();
    }
}
