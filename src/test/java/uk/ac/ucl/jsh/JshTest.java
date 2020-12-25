package uk.ac.ucl.jsh;

import org.junit.Test;
import uk.ac.ucl.jsh.AppTests.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    protected String pwdSupplementary(String contents) {
        StringBuilder result = new StringBuilder();
        String[] words = contents.split("\\s+");

        IntStream.range(0, words.length).forEach(i -> {
            String line = scn.next();
            result.append(line);
            if (i != words.length - 1) {
                result.append(" ");
            }
        });
        return result.toString();
    }

    protected String getEvalResult(String file_contents) {
        if (file_contents.equals("")) return file_contents;

        List<String> ok = Stream.of(file_contents.split("\n")).map(String::new).collect(Collectors.toList());
        int size = ok.size();

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            String line = scn.next();
            result.append(line);
            if (i != size - 1) {
                result.append("\n");
            }
        }
        return result.toString();
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