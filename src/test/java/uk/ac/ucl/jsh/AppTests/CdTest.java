package uk.ac.ucl.jsh.AppTests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.ac.ucl.jsh.Applications.Cd;
import uk.ac.ucl.jsh.Factory;
import uk.ac.ucl.jsh.JshTest;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class CdTest extends JshTest {

    private String currDir = System.getProperty("user.dir");

    public CdTest() {
    }

    @Test
    public void test_change_dir() throws IOException {
        String toFolder = "src";
        ArrayList<String> aCase = new ArrayList<>(Collections.singleton("cd"));
        aCase.add(toFolder);
        String result_dir = currDir + File.separator + toFolder;
        String new_dir = new Factory().getApp("cd").exec(aCase, currDir, null, outs);
        assertEquals(result_dir, new_dir);
    }

    @Test
    public void test_change_back() throws IOException {
        String[] args = {"cd", "src"};
        String new_dir = new Factory().getApp("cd").exec(new ArrayList<>(Arrays.asList(args)), currDir, null, outs);

        String[] args_2 = {"cd", ".."};
        String new_dir_2 = new Factory().getApp("cd").exec(new ArrayList<>(Arrays.asList(args_2)), new_dir, null, outs);

        assertEquals(currDir, new_dir_2);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void test_cd_exception() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("cd: nonexistingdirectory is not an existing directory");
        Cd cd = new Cd();
        ArrayList<String> args = new ArrayList<>();
        args.add("cd"); args.add("nonexistingdirectory");
        cd.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), outs);
    }

    @Test
    public void test_cd_empty() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("cd: missing argument");
        Cd cd = new Cd();
        ArrayList<String> args = new ArrayList<>();
        cd.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), outs);
    }

    @Test
    public void test_cd_file_not_found() throws IOException {
        exceptionRule.expect(FileNotFoundException.class);
        exceptionRule.expectMessage("nonexistingfile.txt (No such file or directory)");
        Cd cd = new Cd();
        ArrayList<String> args = new ArrayList<>();
        args.add("cd");
        cd.mainExec(args, System.getProperty("user.dir"), new FileInputStream("nonexistingfile.txt"), outs);
    }

    @Test
    public void test_cd_file_but_no_arg() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("cd: text1.txt is not an existing directory");
        Cd cd = new Cd();
        ArrayList<String> args = new ArrayList<>();
        args.add("cd");args.add("text1.txt");
        cd.mainExec(args, System.getProperty("user.dir")+File.separator+JshTest.testDirectory, InputStream.nullInputStream(), outs);
    }

    @Test
    public void test_cd_too_many_args() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("cd: too many arguments");
        Cd cd = new Cd();
        ArrayList<String> args = new ArrayList<>();
        args.add("cd");args.add("a");args.add("b");
        cd.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), outs);
    }
}
