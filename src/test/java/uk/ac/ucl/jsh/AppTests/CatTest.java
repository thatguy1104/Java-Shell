package uk.ac.ucl.jsh.AppTests;
import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class CatTest extends JshTest {

    public CatTest() throws IOException {
    }

    @Test
    public void test_file() throws IOException {
        String file_name = "text1.txt";
        Jsh.eval("cat " + file_name, out);
        String result = getActualResult(file_name);
        String expected = readFile(file_name);
        assertEquals(expected, result);
    }

    @Test
    public void test_pipe() throws IOException {
        // TODO
    }

    @Test
    public void test_pipe_grep() throws IOException {
        // TODO
    }

    @Test
    public void test_nested() throws IOException {
        // TODO
    }

    @Test
    public void test_pipe_cat() throws IOException {
        // TODO
    }

    @Test
    public void test_redir_cat() throws IOException {
        // TODO
    }

    @Test
    public void test_two_redir() throws IOException {
        // TODO
    }

    public void runAllTests() throws IOException {
        test_file();
//        test_pipe();
//        test_pipe_grep();
//        test_nested();
//        test_pipe_cat();
//        test_redir_cat();
//        test_two_redir();
    }
}
