package uk.ac.ucl.jsh;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
        assertEquals(scn.next(), "foo");
    }

    @Test
    public void testCat() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        Jsh.eval("cat text1.txt", out);

        Scanner scn = new Scanner(in);

        String line;
        while(scn.hasNextLine()) {
            line = scn.next();
            if (!line.equals("")) {
                System.out.println("OUT: " + line);
            }
        }
    }

    @Test
    public void testCd() throws Exception {
        // TODO
    }


    //@Test
    // public void testCut() throws Exception {
    //     PipedInputStream in = new PipedInputStream();
    //     PipedOutputStream out;
    //     out = new PipedOutputStream(in);
    //     String[] cases = {"cut -b 1 text1.txt", "cut -b 1,2 text1.txt", "cut -b 1- text1.txt", "cut -b 1,3-4 text1.txt"};
    //     String[] expected_out = {"a", "ab", "abcdef", "acd"};
    //     for (int i = 0; i < cases.length; i++) {
    //         Jsh.eval(cases[i], out);
    //         Scanner scn = new Scanner(in);
    //         assertEquals(scn.next(), expected_out[i]);
    //     }
    // }

    // @Test
    // public void testFind() throws Exception {
    //     PipedInputStream in = new PipedInputStream();
    //     PipedOutputStream out;
    //     out = new PipedOutputStream(in);
    //     String[] cases = { "find *.txt", "find run.txt", "find text1.txt", "find text2.txt"};
    //     String[][] expected_out = {{"text1.txt"}, {"run.txt"}, {"text1.txt"}, {"text2.txt"}};
    //     for (int i = 0; i < cases.length; i++) {
    //         Jsh.eval(cases[i], out);
    //         Scanner scn = new Scanner(in);
    //         while(scn.hasNext()) {
    //             for (int j = 0; j < expected_out[i].length; j++) {
    //                 assertEquals(scn.next(), expected_out[j]);
    //             } 
    //         }
    //     }
    //}

    @Test
    public void testGrep() throws Exception {
        // TODO
    }

    @Test
    public void testHead() throws Exception {
        // TODO
    }

    @Test
    public void testLs() throws Exception {
        // TODO
    }

    @Test
    public void testPwd() throws Exception {
        // TODO
    }

    @Test
    public void testSort() throws Exception {
        // TODO
    }

    @Test
    public void testTail() throws Exception {
        // TODO
    }

    @Test
    public void testUniq() throws Exception {
        // TODO
    }

    @Test
    public void testUnsafe() throws Exception {
        // TODO
    }

}
