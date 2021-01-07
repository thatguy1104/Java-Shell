package uk.ac.ucl.jsh.AppTests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.ac.ucl.jsh.Applications.Uniq;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class UniqTest extends JshTest {

    public UniqTest() {
    }

    @Test
    public void testUniqSimple() {
        String filepath = JshTest.testDirectory + File.separator;
        String[] args = {"uniq " + filepath + "text3.txt", "AAA" + System.getProperty("line.separator") +
                                                           "BBB" + System.getProperty("line.separator") +
                                                           "AAA" + System.getProperty("line.separator") +
                                                           "CCC" + System.getProperty("line.separator") +
                                                           "ccc" + System.getProperty("line.separator") +
                                                           "a" + System.getProperty("line.separator") +
                                                           "b" + System.getProperty("line.separator") +
                                                           "c" + System.getProperty("line.separator") +
                                                           "d" + System.getProperty("line.separator") +
                                                           "e" + System.getProperty("line.separator") +
                                                           "f" + System.getProperty("line.separator") +
                                                           "g" + System.getProperty("line.separator") +
                                                           "h" + System.getProperty("line.separator") +
                                                           "i"};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void testUniqEmpty() {
        String filepath = JshTest.testDirectory + File.separator + JshTest.testSubDirectory + File.separator;
        String[] args = {"uniq " + filepath + "text3.txt", ""};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void testUniqI() {
        String filepath = JshTest.testDirectory + File.separator + JshTest.testSubDirectory + File.separator;
        String[] args = {"uniq -i " + filepath + "text2.txt", "A" + System.getProperty("line.separator") +
                                                              "B" + System.getProperty("line.separator") +
                                                              "C"};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void testUniqStdin() {
        String filepath = JshTest.testDirectory + File.separator + JshTest.testSubDirectory + File.separator;
        String[] args = {"uniq < " + filepath + "text2.txt", "A" + System.getProperty("line.separator") +
                                                             "a" + System.getProperty("line.separator") +
                                                             "B" + System.getProperty("line.separator") +
                                                             "b" + System.getProperty("line.separator") +
                                                             "C" + System.getProperty("line.separator") +
                                                             "c"};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void testUniqStdinI() {
        String filepath = JshTest.testDirectory + File.separator + JshTest.testSubDirectory + File.separator;
        String[] args = {"uniq -i < " + filepath + "text2.txt", "A" + System.getProperty("line.separator") +
                                                                "B" + System.getProperty("line.separator") +
                                                                "C"};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Test
    public void testTailPipeUniqI() {
        String filepath = JshTest.testDirectory + File.separator + JshTest.testSubDirectory + File.separator;
        String[] args = {"tail -n 4 " + filepath + "text2.txt | uniq -i", "B" + System.getProperty("line.separator") +
                                                                          "C"};
        Jsh.eval(args[0], outs);
        assertEquals(args[1], outs.toString().trim());
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testUniqArgumentError() throws IOException{
        Uniq uniq = new Uniq();
        ArrayList<String> args = new ArrayList<>();
        args.add("uniq");args.add("a");args.add("b");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("uniq: wrong argument a");
        uniq.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), outs);
    }

    @Test
    public void testUniqArgumentError2() throws IOException{
        Uniq uniq = new Uniq();
        ArrayList<String> args = new ArrayList<>();
        args.add("uniq");args.add("a");args.add("b");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("uniq: wrong argument a");
        uniq.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), outs);
    }

    @Test
    public void testUniqWrongFilename() throws IOException{
        Uniq uniq = new Uniq();
        ArrayList<String> args = new ArrayList<>();
        args.add("uniq");args.add("nonExistentFile");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("uniq: nonExistentFile does not exist");
        uniq.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), outs);
    }

    @Test
    public void testUniqCannotOpen() throws IOException{
        Uniq uniq = new Uniq();
        ArrayList<String> args = new ArrayList<>();
        args.add("uniq");args.add("target");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("uniq: cannot open target");
        uniq.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), outs);
    }

    @Test
    public void testUniqTooManyArgs() throws IOException{
        Uniq uniq = new Uniq();
        ArrayList<String> args = new ArrayList<>();
        args.add("uniq");args.add("a");args.add("b");args.add("c");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("uniq: too many arguments");
        uniq.mainExec(args, System.getProperty("user.dir"), InputStream.nullInputStream(), outs);
    }
}
