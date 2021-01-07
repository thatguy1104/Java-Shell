package uk.ac.ucl.jsh.AppTests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.ac.ucl.jsh.Applications.Ls;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.*;
import java.util.*;

import static org.junit.Assert.assertArrayEquals;

public class LsTest extends JshTest {

    public LsTest() {
    }

    @Test
    public void testLsCurrentDirectory() {
        File f = new File(System.getProperty("user.dir"));
        File[] fList = f.listFiles();
        List<String> expected = new ArrayList<>();
        List<String> results;

        assert fList != null;
        for (File file : fList) {
            if (!file.getName().startsWith(".")) expected.add(file.getName());
        }

        Jsh.eval("ls", outs);
        results = Arrays.asList(outs.toString().trim().split("\\s+"));

        Collections.sort(expected);
        Collections.sort(results);

        assertArrayEquals(expected.toArray(), results.toArray());
    }

    @Test
    public void testLsSpecifiedDirectory() {
        File f = new File(System.getProperty("user.dir") + File.separator + JshTest.testDirectory);
        File[] fList = f.listFiles();
        List<String> expected = new ArrayList<>();
        List<String> results;

        assert fList != null;
        for (File file : fList) {
            if (!file.getName().startsWith(".")) expected.add(file.getName());
        }

        Jsh.eval("ls testDir", outs);
        results = Arrays.asList(outs.toString().trim().split("\\s+"));

        Collections.sort(expected);
        Collections.sort(results);

        assertArrayEquals(expected.toArray(), results.toArray());
    }

    @Test
    public void testLsSpecifiedSubDirectory() {
        File f = new File(System.getProperty("user.dir") + File.separator + JshTest.testDirectory + File.separator + JshTest.testSubDirectory);
        File[] fList = f.listFiles();
        List<String> expected = new ArrayList<>();
        List<String> results;

        assert fList != null;
        for (File file : fList) {
            if (!file.getName().startsWith(".")) expected.add(file.getName());
        }

        Jsh.eval("ls testDir" + File.separator + "testSubDir", outs);
        results = Arrays.asList(outs.toString().trim().split("\\s+"));

        Collections.sort(expected);
        Collections.sort(results);

        assertArrayEquals(expected.toArray(), results.toArray());
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testLsArgumentError() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("ls: too many arguments");
        Ls ls = new Ls();
        ArrayList<String> args = new ArrayList<>();
        args.add("ls"); args.add("a"); args.add("b");
        ls.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), outs);
    }

    @Test
    public void testLsDirectoryError() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("ls: directory does not exist");
        Ls ls = new Ls();
        ArrayList<String> args = new ArrayList<>();
        args.add("ls"); args.add("a");
        ls.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), outs);
    }
}
