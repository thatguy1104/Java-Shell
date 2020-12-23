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
    public void test_1() throws IOException {
        String aCase = "pwd";
        String curr_dir = System.getProperty("user.dir");
        Jsh.eval(aCase, this.out);
        String full_string = full_line(curr_dir);
        assertEquals(full_string, curr_dir);
    }

    public void runAllTests() throws IOException {
        test_1();
    }
}
