package uk.ac.ucl.jsh;

import org.junit.Test;
import uk.ac.ucl.jsh.Applications.Application;

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
        int limit = 50, i = 0;

        if (file_contents.equals("")) {
            return "";
        }

        StringBuilder line = new StringBuilder(this.scn.next());
        if (file_contents.contains("\n")) {
            while (this.scn.hasNextLine()) {
                String temp = this.scn.next();
                line.append("\n").append(temp);
                if (temp.equals(last_string(file_contents))) break;
                i++;
                if (i > limit) break;
            }
            return line.toString();
        }
        return file_contents;
    }

    private String eval_result(String case_, String expected) throws IOException {
        Jsh.eval(case_, this.out);
        return full_line(expected);
    }

    @Test
    public void testEcho() throws Exception {
        String[][] cases = {{"echo foo", "foo"}, {"echo hello world", "hello world"}, {"echo \"hello    world\"", "hello    world"}};
        for (String[] aCase : cases) {
            String full_string = eval_result(aCase[0], aCase[1]);
            assertEquals(full_string, aCase[1]);
        }
    }

    @Test
    public void testCat() throws Exception {
        String file_name = "text1.txt";
        Jsh.eval("cat " + file_name, this.out);
        String file_contents = readFile(file_name);
        String full_string = full_line(file_contents);
        assertEquals(full_string, file_contents);
    }

    @Test
    public void testCd() throws Exception {
        String aCase = "cd src";
        String curr_dir = System.getProperty("user.dir/src");

        System.out.println(curr_dir);

//        String full_string = eval_result(aCase, curr_dir);
//        assertEquals(full_string, curr_dir);

    }

    @Test
    public void testCut() throws Exception {
        String[][] cases = {{"cut -b 1 text1.txt", "a\no"}, {"cut -b 1,2 text1.txt", "ab\nof"}, {"cut -b 1- text1.txt", "bcdefghi\nfeijnwio"}, {"cut -b 1,3-4 text1.txt", "ad\noi"}};
        for (String[] aCase : cases) {
            String full_string = eval_result(aCase[0], aCase[1]);
            assertEquals(full_string, aCase[1]);
        }
    }

    @Test
    public void testFind() throws Exception {
        String[][] cases = {{"find *.txt", "text1.txt"}, {"find run.txt", "run.txt"}, {"find text1.txt", "text1.txt"}, {"find text2.txt", "text2.txt"}};
        for (String[] aCase : cases) {
            String full_string = eval_result(aCase[0], aCase[1]);
            assertEquals(full_string, aCase[1]);
        }
    }

    @Test
    public void testGrep() throws Exception {
        String[][] cases = {{"grep \"d\" text1.txt", "abcdefghi"}, {"grep \"d\" text2.txt", "d\nd"}, {"grep \"aa\" text3.txt", "aaa\naaa"}};
        for (String[] aCase : cases) {
            String full_string = eval_result(aCase[0], aCase[1]);
            assertEquals(full_string, aCase[1]);
        }
    }

    @Test
    public void testHead() throws Exception {
        String[][] cases = {{"head text1.txt", readFile("text1.txt")}, {"head -n 3 text3.txt", "bab\nbbb\nBBB"}, {"head -n 0 text1.txt", ""}};
        for (String[] aCase : cases) {
            String full_string = eval_result(aCase[0], aCase[1]);
            assertEquals(full_string, aCase[1]);
        }
    }

    @Test
    public void testLs() throws Exception {
        File f = new File(System.getProperty("user.dir"));
        String[] path_names = f.list();
        assert path_names != null;

        for (String file_name : path_names) {
            String full_string = eval_result("ls", file_name);
            assertEquals(full_string, file_name);
        }
    }

    @Test
    public void testPwd() throws Exception {
        String aCase = "pwd";
        String curr_dir = System.getProperty("user.dir");
        String full_string = eval_result(aCase, curr_dir);
        assertEquals(full_string, curr_dir);
    }

    @Test
    public void testSort() throws Exception {
        String[][] cases = {{"sort -r text1.txt", "ofeijnwio\nabcdefghi"}, {"sort -r text3.txt", "lol\nloL\nbbb\nbbb\nbab\naaa\naaa\nLoL\nBBB\nAAA"}};
        for (String[] aCase : cases) {
            String full_string = eval_result(aCase[0], aCase[1]);
            assertEquals(full_string, aCase[1]);
        }
    }

    @Test
    public void testTail() throws Exception {
        String[][] cases = {{"tail -n 3 text2.txt", "o\nw\nj"}, {"tail -n 3 text3.txt", "LoL\nlol\nloL"}, {"tail -n 0 text1.txt", ""}};
        for (String[] aCase : cases) {
            String full_string = eval_result(aCase[0], aCase[1]);
            assertEquals(full_string, aCase[1]);
        }
    }

    @Test
    public void testUniq() throws Exception {
        String[][] cases = {{"uniq text3.txt", "bab\nbbb\nBBB\naaa\nAAA\nLoL\nlol\nloL"}, {"uniq -i text3.txt", "bab\nAAA\nbbb\nloL"}};
        for (String[] aCase : cases) {
            String full_string = eval_result(aCase[0], aCase[1]);
            assertEquals(full_string, aCase[1]);
        }
    }

    @Test
    public void testUnsafe() throws Exception {
        // TODO
    }

    @Test
    public void testFactory() {
        String[] allApps = {"cd", "cat", "cut", "pwd", "echo", "find", "grep", "head", "sort", "tail", "uniq", "ls"};
        for (String appName : allApps) {
            Application application = new Factory().getApp(appName);
            assertNotNull(application);
        }

    }

}
