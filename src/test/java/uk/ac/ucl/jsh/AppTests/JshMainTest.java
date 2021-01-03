package uk.ac.ucl.jsh.AppTests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.IOException;


public class JshMainTest extends JshTest {
    public JshMainTest() throws IOException {
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void mainTest() {
        String[] args = {"echo", "hello world"};
        Jsh.main(args);
    }

    @Test
    public void mainTest_2() {
        String[] args = {"echo"};
        Jsh.main(args);
    }
}
