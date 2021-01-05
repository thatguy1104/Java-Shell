package uk.ac.ucl.jsh.AppTests;
import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class UnsafeUnsafeTest extends JshTest {

    public UnsafeUnsafeTest() throws IOException {
    }

    @Test
    public void test_unsafe_nothing() {
        String expected = "_cat: missing arguments";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Jsh.eval("_cat", outputStream);
        String result = outputStream.toString().replaceAll("\n", "").replaceAll("\r", "");
        assertEquals(expected, result);
    }

    @Test
    public void test_unsafe_error() {
        String expected = "_cat: file does not exist";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Jsh.eval("_cat yes.txt", outputStream);
        String result = outputStream.toString().replaceAll("\n", "").replaceAll("\r", "");
        assertEquals(expected, result);
    }
}