package uk.ac.ucl.jsh.AppTests;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.*;


public class JshMainTest extends JshTest {
    public JshMainTest() {
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testMainEchoArg() {
        String[] args = {"echo", "hello world"};
        Jsh.main(args);
    }

    @Test
    public void testMainEchoNothing() {
        String[] args = {"echo"};
        Jsh.main(args);
    }

    @Test
    public void testMainTwoArgsWithoutC() {
        String[] args = {"-c", "two"};
        Jsh.main(args);
    }

    @Test(expected = RuntimeException.class)
    public void testMainCmdException() {
        String input = "ls noExitingFile\nls noExitingFile\nls noExitingFile\nls noExitingFile\nls noExitingFile\nls noExitingFile\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setIn(in);
        String[] args = {};
        Jsh.main(args);
    }

    @Test(expected = RuntimeException.class)
    public void testMainEmptyCmd() {
        String input = "\n\n\n\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setIn(in);
        String[] args = {};
        Jsh.main(args);
    }

    @Test(expected = RuntimeException.class)
    public void testMainEmptyCmdSpace() {
        String input = "\n \n \n \n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setIn(in);
        String[] args = {};
        Jsh.main(args);
    }

}
