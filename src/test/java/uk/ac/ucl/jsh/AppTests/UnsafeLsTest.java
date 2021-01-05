package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class UnsafeLsTest extends JshTest {

    public UnsafeLsTest() throws IOException {
    }

    OutputStream outs = new ByteArrayOutputStream();

    @Test
    public void test_ls_current_directory() {
        File f = new File(System.getProperty("user.dir"));
        File[] f_list = f.listFiles();
        List<String> expected = new ArrayList<>();
        List<String> results;

        assert f_list != null;
        for (File file : f_list) {
            if (!file.getName().startsWith(".")) expected.add(file.getName());
        }

        Jsh.eval("_ls", outs);
//        for (String file_name : expected) {
//            results.add(pwdSupplementary(file_name));
//        }
        results = Arrays.asList(outs.toString().trim().split("\\s+"));

        Collections.sort(expected);
        Collections.sort(results);

        assertArrayEquals(expected.toArray(), results.toArray());
    }

    @Test
    public void test_unsafe_ls_specified_directory() {
        File f = new File(System.getProperty("user.dir") + File.separator + JshTest.testDirectory);
        File[] f_list = f.listFiles();
        List<String> expected = new ArrayList<>();
        List<String> results = new ArrayList<>();

        assert f_list != null;
        for (File file : f_list) {
            if (!file.getName().startsWith(".")) expected.add(file.getName());
        }

        Jsh.eval("_ls testDir", outs);
//        for (String file_name : expected) {
//            results.add(pwdSupplementary(file_name));
//        }
        results = Arrays.asList(outs.toString().trim().split("\\s+"));

        Collections.sort(expected);
        Collections.sort(results);

        assertArrayEquals(expected.toArray(), results.toArray());
    }

    @Test
    public void test_unsafe_ls_specified_sub_directory() {
        File f = new File(System.getProperty("user.dir") + File.separator + JshTest.testDirectory + File.separator + JshTest.testSubDirectory);
        File[] f_list = f.listFiles();
        List<String> expected = new ArrayList<>();
        List<String> results = new ArrayList<>();

        assert f_list != null;
        for (File file : f_list) {
            if (!file.getName().startsWith(".")) expected.add(file.getName());
        }

        Jsh.eval("_ls testDir" + File.separator + "testSubDir", outs);
//        for (String file_name : expected) {
//            results.add(pwdSupplementary(file_name));
//        }
        results = Arrays.asList(outs.toString().trim().split("\\s+"));

        Collections.sort(expected);
        Collections.sort(results);

        assertArrayEquals(expected.toArray(), results.toArray());
    }

    @Test
    public void test_unsafe_ls_nothing() {
        String expected = "_ls: too many arguments";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Jsh.eval("_ls a b", outputStream);
        String result = outputStream.toString().replaceAll("\n", "").replaceAll("\r", "");
        assertEquals(expected, result);
    }
}
