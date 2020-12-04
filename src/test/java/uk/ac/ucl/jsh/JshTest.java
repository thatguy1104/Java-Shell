package uk.ac.ucl.jsh;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class JshTest {

    private PipedInputStream in = new PipedInputStream();
    private PipedOutputStream out = new PipedOutputStream(in);
    private Scanner scn = new Scanner(in);

    public JshTest() throws IOException {
    }

    private String readFile(String file_name) throws IOException {
        List<String> contents = Files.readAllLines(Paths.get(file_name));
        return contents.stream().map(String::valueOf).collect(Collectors.joining("\n"));
    }

    private String last_string(String line) {
        String[] lines = line.split("\\n");
        return lines[lines.length - 1];
    }

    private String full_line(String file_contents) {
        if (file_contents.equals("")) {
            return "";
        }

        StringBuilder line = new StringBuilder(this.scn.next());
        if (file_contents.contains("\n")) {
            while (this.scn.hasNextLine()) {
                String temp = this.scn.next();
                line.append("\n").append(temp);
                if (temp.equals(last_string(file_contents))) break;
            }
            return line.toString();
        }
        return file_contents;
    }


    @Test
    public void testJsh() throws Exception {
        Jsh.eval("echo foo", this.out);
        assertEquals(this.scn.next(), "foo");
    }

    @Test
    public void testCat() throws Exception {
        String file_name = "text1.txt";
        Jsh.eval("cat " + file_name, this.out);
        String file_contents = readFile(file_name);
        String full_string = full_line(file_contents);
        assertEquals(full_string, file_contents);
    }

//    @Test
//    public void testCd() throws Exception {
//        // TODO
//        Jsh.eval("cd src", this.out);
//        String cd_directory = System.getProperty("user.dir");
//        System.out.println(cd_directory);
//        assertEquals(this.scn.next(), "foo");
//    }


    @Test
    public void testCut() throws Exception {
        int test_cases = 4;
        String[] cases = {"cut -b 1 text1.txt", "cut -b 1,2 text1.txt", "cut -b 1- text1.txt", "cut -b 1,3-4 text1.txt"};
        String[] expected_out = {"a\no", "ab\nof", "bcdefghi\nfeijnwio", "ad\noi"};

        for (int i = 0; i < test_cases; i++) {
            Jsh.eval(cases[i], this.out);
            String full_string = full_line(expected_out[i]);
            assertEquals(full_string, expected_out[i]);
        }
    }

    @Test
    public void testFind() throws Exception {
        int test_cases = 4;
        String[] cases = {"find *.txt", "find run.txt", "find text1.txt", "find text2.txt"};
        String[] expected_out = {"text1.txt", "run.txt", "text1.txt", "text2.txt"};
        for (int i = 0; i < test_cases; i++) {
            Jsh.eval(cases[i], this.out);
            String full_string = full_line(expected_out[i]);
            assertEquals(full_string, expected_out[i]);
        }
    }

    @Test
    public void testGrep() throws Exception {
        int test_cases = 3;
        String[] cases = {"grep \"d\" text1.txt", "grep \"d\" text2.txt", "grep \"aa\" text3.txt"};
        String[] expected_out = {"abcdefghi", "d\nd", "aaa\naaa"};

        for (int i = 0; i < test_cases; i++) {
            Jsh.eval(cases[i], this.out);
            String full_string = full_line(expected_out[i]);
            assertEquals(full_string, expected_out[i]);
        }
    }

    @Test
    public void testHead() throws Exception {
        int test_cases = 3;
        String[] cases = {"head text1.txt", "head -n 3 text3.txt", "head -n 0 text1.txt"};
        String[] expected_out = {readFile("text1.txt"), "bab\nbbb\nBBB", ""};

        for (int i = 0; i < test_cases; i++) {
            Jsh.eval(cases[i], this.out);
            String full_string = full_line(expected_out[i]);
            assertEquals(full_string, expected_out[i]);
        }
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
        int test_cases = 2;
        String[] cases = {"sort -r text1.txt", "sort -r text3.txt"};
        String[] expected_out = {"ofeijnwio\nabcdefghi", "lol\nloL\nbbb\nbbb\nbab\naaa\naaa\nLoL\nBBB\nAAA"};

        for (int i = 0; i < test_cases; i++) {
            Jsh.eval(cases[i], this.out);
            String full_string = full_line(expected_out[i]);
            assertEquals(full_string, expected_out[i]);
        }
    }

    @Test
    public void testTail() throws Exception {
        int test_cases = 3;
        String[] cases = {"tail -n 3 text2.txt", "tail -n 3 text3.txt", "tail -n 0 text1.txt"};
        String[] expected_out = {"o\nw\nj", "LoL\nlol\nloL", ""};

        for (int i = 0; i < test_cases; i++) {
            Jsh.eval(cases[i], this.out);
            String full_string = full_line(expected_out[i]);
            assertEquals(full_string, expected_out[i]);
        }
    }

    @Test
    public void testUniq() throws Exception {
        int test_cases = 2;
        String[] cases = {"uniq text3.txt", "uniq -i text3.txt"};
        String[] expected_out = {"bab\nbbb\nBBB\naaa\nAAA\nLoL\nlol\nloL", "bab\nAAA\nbbb\nloL"};

        for (int i = 0; i < test_cases; i++) {
            Jsh.eval(cases[i], this.out);
            String full_string = full_line(expected_out[i]);
            assertEquals(full_string, expected_out[i]);
        }
    }

    @Test
    public void testUnsafe() throws Exception {
        // TODO
    }

}
