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

    public CdTest() throws IOException {
    }

    @Test
    public void testChangeDir() throws IOException {
        String toFolder = "src";
        ArrayList<String> aCase = new ArrayList<>(Collections.singleton("cd"));
        aCase.add(toFolder);
        String result_dir = currDir + File.separator + toFolder;
        String new_dir = new Factory().getApp("cd").exec(aCase, currDir, null, this.out);
        assertEquals(result_dir, new_dir);
    }

    @Test
    public void testChangeBack() throws IOException {
        String[] args = {"cd", "src"};
        String new_dir = new Factory().getApp("cd").exec(new ArrayList<>(Arrays.asList(args)), currDir, null, this.out);

        String[] args_2 = {"cd", ".."};
        String new_dir_2 = new Factory().getApp("cd").exec(new ArrayList<>(Arrays.asList(args_2)), new_dir, null, this.out);

        assertEquals(currDir, new_dir_2);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void test_cd_no_args() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Index 1 out of bounds for length 1");
        Cd cd = new Cd();
        ArrayList<String> args = new ArrayList<>();
        args.add("cd");
        cd.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }

    @Test
    public void test_cd_exception() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("cd: nonexistingdirectory is not an existing directory");
        Cd cd = new Cd();
        ArrayList<String> args = new ArrayList<>();
        args.add("cd"); args.add("nonexistingdirectory");
        cd.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }

    @Test
    public void test_cd_empty() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("cd: missing argument");
        Cd cd = new Cd();
        ArrayList<String> args = new ArrayList<>();
        cd.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void test_cd_file_not_found() throws IOException {
        exception.expect(FileNotFoundException.class);
        exception.expectMessage("nonexistingfile.txt (No such file or directory)");
        Cd cd = new Cd();
        ArrayList<String> args = new ArrayList<>();
        args.add("cd");
        cd.mainExec(args, System.getProperty("user.dir"), new FileInputStream("nonexistingfile.txt"), out);
    }

    @Test
    public void test_cd_other() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Index 1 out of bounds for length 1");
        Cd cd = new Cd();
        ArrayList<String> args = new ArrayList<>();
        args.add("cd");
        cd.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), out);
    }
}
