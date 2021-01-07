package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;
import java.util.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class UnsafeLsTest extends JshTest {

    public UnsafeLsTest() {
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

        Jsh.eval("_ls", outs);
        results = Arrays.asList(outs.toString().trim().split("\\s+"));

        Collections.sort(expected);
        Collections.sort(results);

        assertArrayEquals(expected.toArray(), results.toArray());
    }

    @Test
    public void testUnsafeLsSpecifiedDirectory() {
        File f = new File(System.getProperty("user.dir") + File.separator + JshTest.testDirectory);
        File[] fList = f.listFiles();
        List<String> expected = new ArrayList<>();
        List<String> results = new ArrayList<>();

        assert fList != null;
        for (File file : fList) {
            if (!file.getName().startsWith(".")) expected.add(file.getName());
        }

        Jsh.eval("_ls testDir", outs);
        results = Arrays.asList(outs.toString().trim().split("\\s+"));

        Collections.sort(expected);
        Collections.sort(results);

        assertArrayEquals(expected.toArray(), results.toArray());
    }

    @Test
    public void testUnsafeLsSpecifiedSubDirectory() {
        File f = new File(System.getProperty("user.dir") + File.separator + JshTest.testDirectory + File.separator + JshTest.testSubDirectory);
        File[] fList = f.listFiles();
        List<String> expected = new ArrayList<>();
        List<String> results = new ArrayList<>();

        assert fList != null;
        for (File file : fList) {
            if (!file.getName().startsWith(".")) expected.add(file.getName());
        }

        Jsh.eval("_ls testDir" + File.separator + "testSubDir", outs);
        results = Arrays.asList(outs.toString().trim().split("\\s+"));

        Collections.sort(expected);
        Collections.sort(results);

        assertArrayEquals(expected.toArray(), results.toArray());
    }

    @Test
    public void testUnsafeLsNothing() {
        String expected = "_ls: too many arguments";
        Jsh.eval("_ls a b", outs);
        String result = outs.toString().strip();
        assertEquals(expected, result);
    }
}
