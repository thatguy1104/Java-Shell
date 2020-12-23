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
    public void test_1() throws IOException {
        String file_name = "text1.txt";
        Jsh.eval("cat " + file_name, out);
        String result = getActualResult(file_name);
        String expected = readFile(file_name);
        assertEquals(expected, result);
    }

    public void runAllTests() throws IOException {
        test_1();
    }
}
