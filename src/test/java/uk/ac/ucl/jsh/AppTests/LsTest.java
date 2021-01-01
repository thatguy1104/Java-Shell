package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class LsTest extends JshTest {

    public LsTest() throws IOException {
    }

    @Test
    public void test_ls_currentDirectory() {
        File f = new File(System.getProperty("user.dir"));
        File[] f_list = f.listFiles();
        List<String> expected = new ArrayList<>();
        List<String> results = new ArrayList<>();

        assert f_list != null;
        for (File file : f_list) {
            if (!file.getName().startsWith(".")) expected.add(file.getName());
        }

        for (String file_name : expected) {
            Jsh.eval("ls", this.out);
            results.add(pwdSupplementary(file_name));
        }

        Collections.sort(expected);
        Arrays.sort(results.toArray());

        assertArrayEquals(expected.toArray(), results.toArray());
    }

    @Test
    public void test_ls_specified_directory() {
        File f = new File(System.getProperty("user.dir") + File.separator + JshTest.testDirectory);
        File[] f_list = f.listFiles();
        List<String> expected = new ArrayList<>();
        List<String> results = new ArrayList<>();

        assert f_list != null;
        for (File file : f_list) {
            if (!file.getName().startsWith(".")) expected.add(file.getName());
        }

        for (String file_name : expected) {
            Jsh.eval("ls testDir", this.out);
            results.add(pwdSupplementary(file_name));
        }

        Collections.sort(expected);
        Arrays.sort(results.toArray());

        assertArrayEquals(expected.toArray(), results.toArray());
    }

    @Test
    public void test_ls_specified_subDirectory() {
        File f = new File(System.getProperty("user.dir") + File.separator + JshTest.testDirectory + File.separator + JshTest.testSubDirectory);
        File[] f_list = f.listFiles();
        List<String> expected = new ArrayList<>();
        List<String> results = new ArrayList<>();

        assert f_list != null;
        for (File file : f_list) {
            if (!file.getName().startsWith(".")) expected.add(file.getName());
        }

        for (String file_name : expected) {
            Jsh.eval("ls testDir" + File.separator + "testSubDir", this.out);
            results.add(pwdSupplementary(file_name));
        }

        Collections.sort(expected);
        Arrays.sort(results.toArray());

        assertArrayEquals(expected.toArray(), results.toArray());
    }

    public void runAllTests() {
        test_ls_currentDirectory();
        test_ls_specified_directory();
        test_ls_specified_subDirectory();
    }
}
