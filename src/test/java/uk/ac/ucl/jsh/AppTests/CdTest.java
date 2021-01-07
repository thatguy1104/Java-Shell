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
    public void testChangeDir() throws IOException {
        String toFolder = "src";
        ArrayList<String> aCase = new ArrayList<>(Collections.singleton("cd"));
        aCase.add(toFolder);
        String resultDir = currDir + File.separator + toFolder;
        String newDir = new Factory().getApp("cd").exec(aCase, currDir, null, outs);
        assertEquals(resultDir, newDir);
    }

    @Test
    public void testChangeBack() throws IOException {
        String[] args = {"cd", "src"};
        String newDir = new Factory().getApp("cd").exec(new ArrayList<>(Arrays.asList(args)), currDir, null, outs);

        String[] args2 = {"cd", ".."};
        String newDir2 = new Factory().getApp("cd").exec(new ArrayList<>(Arrays.asList(args2)), newDir, null, outs);

        assertEquals(currDir, newDir2);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testCdException() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("cd: nonexistingdirectory is not an existing directory");
        Cd cd = new Cd();
        ArrayList<String> args = new ArrayList<>();
        args.add("cd"); args.add("nonexistingdirectory");
        cd.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), outs);
    }

    @Test
    public void testCdEmpty() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("cd: missing argument");
        Cd cd = new Cd();
        ArrayList<String> args = new ArrayList<>();
        cd.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), outs);
    }

    @Test
    public void testCdFileButNoArg() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("cd: text1.txt is not an existing directory");
        Cd cd = new Cd();
        ArrayList<String> args = new ArrayList<>();
        args.add("cd"); args.add("text1.txt");
        cd.mainExec(args, System.getProperty("user.dir")+File.separator+JshTest.testDirectory, InputStream.nullInputStream(), outs);
    }

    @Test
    public void testCdTooManyArgs() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("cd: too many arguments");
        Cd cd = new Cd();
        ArrayList<String> args = new ArrayList<>();
        args.add("cd");args.add("a");args.add("b");
        cd.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), outs);
    }
}
