package uk.ac.ucl.jsh;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Scanner;

public class JshTest {
    public JshTest() {
    }

    @Test
    public void testJsh() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        Jsh.eval("echo foo", out);
        Scanner scn = new Scanner(in);
        assertEquals(scn.next(),"foo");
    }

    @Test
    public void test_cut() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        String[] cases = {"cut -b 1 text1.txt", "cut -b 1,2 text1.txt", "cut -b 1- text1.txt", "cut -b 1,3-4 text1.txt"};
        String[] expected_out = {"a", "ab", "abcdef", "acd"};
        for (int i = 0; i < cases.length; i++) {
            Jsh.eval(cases[i], out);
            Scanner scn = new Scanner(in);
            assertEquals(scn.next(), expected_out[i]);
        }
    }

    @Test
    public void test_find() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        String[] cases = { "find *.txt", "find run.txt", "find text1.txt", "find text2.txt"};
        String[] expected_out = {"text1.txt", "run.txt", "text1.txt", "text2.txt"};
        for (int i = 0; i < cases.length; i++) {
            Jsh.eval(cases[i], out);
            Scanner scn = new Scanner(in);
            assertEquals(scn.next(), expected_out[i]);
        }
    }

}
