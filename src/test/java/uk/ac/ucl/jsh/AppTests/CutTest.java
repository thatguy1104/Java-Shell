package uk.ac.ucl.jsh.AppTests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.ac.ucl.jsh.Applications.Cut;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CutTest extends JshTest {

    public CutTest() {
    }

    @Test
    public void testCutSimple() {
        String[] arg = {"cut -b 1 " + JshTest.testDirectory + File.separator + "text1.txt", "a" + System.getProperty("line.separator") +
                                                                                            "o"};
        Jsh.eval(arg[0], outs);
        assertEquals(arg[1], outs.toString().trim());
    }

    @Test
    public void testCutSimple2() {
        String[] arg = {"cut -b 1,2 " + JshTest.testDirectory + File.separator + "text1.txt", "ab" + System.getProperty("line.separator") +
                                                                                              "of"};
        Jsh.eval(arg[0], outs);
        assertEquals(arg[1], outs.toString().trim());
    }

    @Test
    public void testCutSimple3() {
        String[] arg = {"cut -b 1- " + JshTest.testDirectory + File.separator + "text1.txt", "abcdefghi" + System.getProperty("line.separator") +
                                                                                             "ofeijnwio"};
        Jsh.eval(arg[0], outs);
        assertEquals(arg[1], outs.toString().trim());
    }

    @Test
    public void testCutSimple4() {
        String[] arg = {"cut -b 1,3-4 " + JshTest.testDirectory + File.separator + "text1.txt", "acd" + System.getProperty("line.separator") +
                                                                                                "oei"};
        Jsh.eval(arg[0], outs);
        assertEquals(arg[1], outs.toString().trim());
    }

    @Test
    public void testCutEchoPipe() {
        String[] arg = {"echo abc | cut -b 1", "a"};
        Jsh.eval(arg[0], outs);
        assertEquals(arg[1], outs.toString().trim());
    }

    @Test
    public void testCutEchoPipe2() {
        String[] arg = {"echo abc | cut -b -1,2-", "abc"};
        Jsh.eval(arg[0], outs);
        assertEquals(arg[1], outs.toString().trim());
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testCutFileTest() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("cut: file does not exist");
        Cut cut = new Cut();
        ArrayList<String> args = new ArrayList<>();
        args.add("cut"); args.add("-b"); args.add("1"); args.add("nonexistentfilename.txt");
        cut.mainExec(args, System.getProperty("user.dir"), null, outs);
    }

    @Test
    public void testCutWrongArgs() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("cut: wrong arguments");
        Cut cut = new Cut();
        ArrayList<String> args = new ArrayList<>();
        args.add("cut"); args.add("1,2"); args.add("text1.txt");
        cut.mainExec(args, System.getProperty("user.dir"), null, outs);
    }

    @Test
    public void testCutByteIndex() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("cut: byte index specified does not exist");
        Cut cut = new Cut();
        ArrayList<String> args = new ArrayList<>();
        args.add("cut"); args.add("-b"); args.add("1, -1, 0, -0"); args.add(JshTest.testDirectory + File.separator + "text1.txt");
        cut.mainExec(args, System.getProperty("user.dir"), new FileInputStream(JshTest.testDirectory + File.separator + "text1.txt"), outs);
    }

    @Test
    public void testCutZeroArg() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("cut: could not convert arguments");
        Cut cut = new Cut();
        ArrayList<String> args = new ArrayList<>();
        args.add("cut"); args.add("-b"); args.add("-0"); args.add(JshTest.testDirectory + File.separator + "text1.txt");
        cut.mainExec(args, System.getProperty("user.dir"), new FileInputStream(JshTest.testDirectory + File.separator + "text1.txt"), outs);
    }

    @Test
    public void testCutMissingArgs() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("cut: missing arguments");
        Cut cut = new Cut();
        ArrayList<String> args = new ArrayList<>();
        args.add(JshTest.testDirectory + File.separator + "text1.txt");
        cut.mainExec(args, System.getProperty("user.dir"), new FileInputStream(JshTest.testDirectory + File.separator + "text1.txt"), outs);
    }

    @Test
    public void testCutWrongFile3() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("cut: wrong argument " + JshTest.testDirectory + File.separator + "text1.txt");
        Cut cut = new Cut();
        ArrayList<String> args = new ArrayList<>();
        args.add("cut");  args.add(JshTest.testDirectory + File.separator + "text1.txt");
        cut.mainExec(args, System.getProperty("user.dir"), null, outs);
    }
}
