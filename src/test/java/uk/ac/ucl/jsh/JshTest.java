package uk.ac.ucl.jsh;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import uk.ac.ucl.jsh.AppTests.*;

public class JshTest {

    private PipedInputStream in = new PipedInputStream();
    protected OutputStream out = new PipedOutputStream(in);
    private Scanner scn = new Scanner(in);
    protected static String testDirectory = "testDir";
    protected static String testSubDirectory = "testSubDir";
    protected static String subDirString = testDirectory + File.separator + testSubDirectory;
    protected static String dotDirectory = ".dotDir";
    private static String dotDirString = testDirectory + File.separator + dotDirectory;

    public JshTest() throws IOException {
    }

    @BeforeClass
    public static void createFiles() throws IOException {
        File theDir = new File(testDirectory);
        if (!theDir.exists()) {
            if (!theDir.mkdirs()) {
                throw new IOException("Failed to create directory: " + theDir);
            }
        }
        theDir = new File(subDirString);
        if (!theDir.exists()) {
            if (!theDir.mkdirs()) {
                throw new IOException("Failed to create directory: " + theDir);
            }
        }
        theDir = new File(dotDirString);
        if (!theDir.exists()) {
            if (!theDir.mkdirs()) {
                throw new IOException("Failed to create directory: " + theDir);
            }
        }

        String[][] files_and_contents = {
                {testDirectory + File.separator + "text1.txt", "abcdefghi\nofeijnwio"},
                {testDirectory + File.separator + "text2.txt", "AAA\nBBB\nAAA"},
                {testDirectory + File.separator + "text3.txt", "AAA\nBBB\nAAA\nCCC\nccc\na\nb\nc\nd\ne\nf\ng\nh\ni"},
                {subDirString + File.separator + "text1.txt", "1\n2\n3\n4\n5"},
                {subDirString + File.separator + "text2.txt", "A\na\nB\nb\nC\nc"},
                {subDirString + File.separator + "text3.txt", ""},
                {dotDirString + File.separator + ".dotFile.csv", "dot"}};

        for (String[] files_and_content : files_and_contents) {
            File f = new File(files_and_content[0]);
            FileWriter writer;
            if (!f.exists()) {
                if (!f.createNewFile()) {
                    throw new IOException("Failed to create file");
                }
                writer = new FileWriter(files_and_content[0]);
                writer.write(files_and_content[1]);
                writer.close();
            }
        }
        System.out.println("Files created");
    }

    @AfterClass
    public static void deleteFiles() throws IOException {
        recursiveDelete(new File(subDirString));
        recursiveDelete(new File(dotDirString));
        recursiveDelete(new File(testDirectory));
        System.out.println("Files deleted");
    }

    private static void recursiveDelete(File file) throws IOException {
        if (!file.exists()) return;
        if (file.isDirectory()) {
            for (File f : Objects.requireNonNull(file.listFiles())) {
                recursiveDelete(f);
            }
        }
        if (!file.delete()) throw new IOException();
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
        new EchoTest();
    }

    @Test
    public void testCat() throws Exception {
        new CatTest();
    }

    @Test
    public void testCd() throws Exception {
        new CdTest();
    }

    @Test
    public void testCut() throws Exception {
        new CutTest();
    }

    @Test
    public void testFind() throws Exception {
        new FindTest();
    }

    @Test
    public void testGrep() throws Exception {
        new GrepTest();
    }

    @Test
    public void testHead() throws Exception {
        new HeadTest();
    }

    @Test
    public void testLs() throws Exception {
        new LsTest();
    }

    @Test
    public void testPwd() throws Exception {
        new PwdTest();
    }

    @Test
    public void testSort() throws Exception {
        new SortTest();
    }

    @Test
    public void testTail() throws Exception {
        new TailTest();
    }

    @Test
    public void testUniq() throws Exception {
        new UniqTest();
    }

    @Test
    public void testFactory() throws IOException {
        new testFactory();
    }

    @Test
    public void testUnsafeCat() throws IOException {
        new UnsafeCatTest();
    }

    @Test
    public void testUnsafeCd() throws IOException {
        new UnsafeCdTest();
    }

    @Test
    public void testUnsafeCut() throws IOException {
        new UnsafeCutTest();
    }

    @Test
    public void testUnsafeEcho() throws IOException {
        new UnsafeEchoTest();
    }

    @Test
    public void testUnsafeFind() throws IOException {
        new UnsafeFindTest();
    }

    @Test
    public void testUnsafeGrep() throws IOException {
        new UnsafeGrepTest();
    }

    @Test
    public void testUnsafeLs() throws IOException {
        new UnsafeLsTest();
    }

    @Test
    public void testUnsafeHead() throws IOException {
        new UnsafeHeadTest();
    }

    @Test
    public void testUnsafePwd() throws IOException {
        new UnsafePwdTest();
    }

    @Test
    public void testUnsafeSort() throws IOException {
        new UnsafeSortTest();
    }

    @Test
    public void testUnsafeTail() throws IOException {
        new UnsafeTailTest();
    }

    @Test
    public void testUnsafeUniq() throws IOException {
        new UnsafeUniqTest();
    }

    @Test
    public void testUnsafeUnsafe() throws Exception {
        new UnsafeUnsafeTest();
    }

    @Test
    public void testGlobbing() throws Exception {
        new GlobTest();
    }
}