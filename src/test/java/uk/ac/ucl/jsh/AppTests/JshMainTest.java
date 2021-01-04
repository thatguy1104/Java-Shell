package uk.ac.ucl.jsh.AppTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;


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

    @Test
    public void mainTest_two_args_without_c() {
        String[] args = {"-c", "two"};
        Jsh.main(args);
    }

    @Test(expected = RuntimeException.class)
    public void mainTest_cmd_exception() {
        String input = "ls noExitingFile\nls noExitingFile\nls noExitingFile\nls noExitingFile\nls noExitingFile\nls noExitingFile\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setIn(in);
        String[] args = {};
        Jsh.main(args);
    }

    @Test(expected = RuntimeException.class)
    public void mainTest_empty_cmd() {
        String input = "\n\n\n\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setIn(in);
        String[] args = {};
        Jsh.main(args);
    }

    @Test(expected = RuntimeException.class)
    public void mainTest_empty_cmd_space() {
        String input = "\n \n \n \n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        System.setIn(in);
        String[] args = {};
        Jsh.main(args);
    }

}
