package uk.ac.ucl.jsh;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import uk.ac.ucl.jsh.AppTests.*;

public class JshTest {

    protected OutputStream outs = new ByteArrayOutputStream();
    protected static String testDirectory = "testDir";
    protected static String testSubDirectory = "testSubDir";
    protected static String subDirString = testDirectory + File.separator + testSubDirectory;
    protected static String dotDirectory = ".dotDir";
    private static String dotDirString = testDirectory + File.separator + dotDirectory;

    public JshTest() {
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
        return contents.stream().map(String::valueOf).collect(Collectors.joining(System.getProperty("line.separator")));
    }

    @Test
    public void testEcho() {
        new EchoTest();
    }

    @Test
    public void testCat() {
        new CatTest();
    }

    @Test
    public void testCd() {
        new CdTest();
    }

    @Test
    public void testCut() {
        new CutTest();
    }

    @Test
    public void testFind() {
        new FindTest();
    }

    @Test
    public void testGrep() {
        new GrepTest();
    }

    @Test
    public void testHead() {
        new HeadTest();
    }

    @Test
    public void testLs() {
        new LsTest();
    }

    @Test
    public void testPwd() {
        new PwdTest();
    }

    @Test
    public void testSort() {
        new SortTest();
    }

    @Test
    public void testTail() {
        new TailTest();
    }

    @Test
    public void testUniq() {
        new UniqTest();
    }

    @Test
    public void testFactory() throws IOException {
        new testFactory();
    }

    @Test
    public void testUnsafeCat() {
        new UnsafeCatTest();
    }

    @Test
    public void testUnsafeCd() {
        new UnsafeCdTest();
    }

    @Test
    public void testUnsafeCut() {
        new UnsafeCutTest();
    }

    @Test
    public void testUnsafeEcho() {
        new UnsafeEchoTest();
    }

    @Test
    public void testUnsafeFind() {
        new UnsafeFindTest();
    }

    @Test
    public void testUnsafeGrep() {
        new UnsafeGrepTest();
    }

    @Test
    public void testUnsafeLs() {
        new UnsafeLsTest();
    }

    @Test
    public void testUnsafeHead() {
        new UnsafeHeadTest();
    }

    @Test
    public void testUnsafePwd() {
        new UnsafePwdTest();
    }

    @Test
    public void testUnsafeSort() {
        new UnsafeSortTest();
    }

    @Test
    public void testUnsafeTail() {
        new UnsafeTailTest();
    }

    @Test
    public void testUnsafeUniq() {
        new UnsafeUniqTest();
    }

    @Test
    public void testUnsafeUnsafe() {
        new UnsafeUnsafeTest();
    }

    @Test
    public void testGlobbing() {
        new GlobTest();
    }
}