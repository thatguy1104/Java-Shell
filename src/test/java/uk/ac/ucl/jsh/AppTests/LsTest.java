package uk.ac.ucl.jsh.AppTests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.ac.ucl.jsh.Applications.Ls;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.junit.Assert.assertArrayEquals;

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

        Jsh.eval("ls", this.out);
        for (String file_name : expected) {
            results.add(pwdSupplementary(file_name));
        }

        Collections.sort(expected);
        Collections.sort(results);

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

        Jsh.eval("ls testDir", this.out);
        for (String file_name : expected) {
            results.add(pwdSupplementary(file_name));
        }

        Collections.sort(expected);
        Collections.sort(results);

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

        Jsh.eval("ls testDir" + File.separator + "testSubDir", this.out);
        for (String file_name : expected) {
            results.add(pwdSupplementary(file_name));
        }

        Collections.sort(expected);
        Collections.sort(results);

        assertArrayEquals(expected.toArray(), results.toArray());
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void test_ls_argument_error() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("ls: too many arguments");
        Ls ls = new Ls();
        ArrayList<String> args = new ArrayList<>();
        args.add("ls"); args.add("a"); args.add("b");
        ls.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }

    @Test
    public void test_ls_directory_error() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("ls: directory does not exist");
        Ls ls = new Ls();
        ArrayList<String> args = new ArrayList<>();
        args.add("ls"); args.add("a");
        ls.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }

    //@Test
    public void test_ls_SOMETHING() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("ls: directory does not exist");
        Ls ls = new Ls();
        ArrayList<String> args = new ArrayList<>();
        args.add("ls"); args.add("a");
        ls.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }
}
