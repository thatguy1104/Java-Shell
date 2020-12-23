package uk.ac.ucl.jsh;

import org.junit.Test;
import uk.ac.ucl.jsh.AppTests.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class JshTest {

    private PipedInputStream in = new PipedInputStream();
    protected OutputStream out = new PipedOutputStream(in);
    private Scanner scn = new Scanner(in);

    public JshTest() throws IOException {
    }

    protected String readFile(String file_name) throws IOException {
        List<String> contents = Files.readAllLines(Paths.get(file_name));
        return contents.stream().map(String::valueOf).collect(Collectors.joining("\n"));
    }

    private String last_string(String line) {
        String[] lines = line.split("\\n");
        return lines[lines.length - 1];
    }

    protected String full_line(String file_contents) {
        int limit = 50, i = 0;

        if (file_contents.equals("")) return "";

        StringBuilder line = new StringBuilder(scn.next());
        if (file_contents.contains("\n")) {
            while (scn.hasNextLine()) {
                String temp = scn.next();
                line.append("\n").append(temp);
                if (temp.equals(last_string(file_contents))) break;
                i++;
                if (i > limit) break;
            }
            return line.toString();
        }
        return file_contents;
    }

    protected String getActualResult(String file_name) throws IOException {
        StringBuilder result = new StringBuilder();
        int file_lines = countFileLines(file_name);
        for (int i = 0; i < file_lines; i++) {
            String line = scn.next();
            result.append(line);
            if (i != file_lines - 1) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    private int countFileLines(String fileName) throws IOException {
        int lines = 0;
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        while (reader.readLine() != null) lines++;
        reader.close();
        return lines;
    }

    @Test
    public void testEcho() throws Exception {
        new EchoTest().runAllTests();
    }

    @Test
    public void testCat() throws Exception {
        new CatTest().runAllTests();
    }

    @Test
    public void testCd() throws Exception {
        new CdTest().runAllTests();
    }

    @Test
    public void testCut() throws Exception {
        new CutTest().runAllTests();
    }

    @Test
    public void testFind() throws Exception {
        new FindTest().runAllTests();
    }

    @Test
    public void testGrep() throws Exception {
        new GrepTest().runAllTests();
    }

    @Test
    public void testHead() throws Exception {
        new HeadTest().runAllTests();
    }

    @Test
    public void testLs() throws Exception {
        new LsTest().runAllTests();
    }

    @Test
    public void testPwd() throws Exception {
        new PwdTest().runAllTests();
    }

    @Test
    public void testSort() throws Exception {
        new SortTest().runAllTests();
    }

    @Test
    public void testTail() throws Exception {
        new TailTest().runAllTests();
    }

    @Test
    public void testUniq() throws Exception {
        new UniqTest().runAllTests();
    }

    @Test
    public void testFactory() throws IOException {
        new testFactory().test();
    }

    @Test // TODO
    public void testUnsafe() throws Exception {
        // TODO
    }
}