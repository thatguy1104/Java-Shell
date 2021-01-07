package uk.ac.ucl.jsh.AppTests;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.ac.ucl.jsh.Applications.Cat;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CatTest extends JshTest {

    public CatTest() {
    }

    @Test
    public void testCatSimple() throws IOException {
        String fileName = JshTest.testDirectory + File.separator + "text1.txt";
        Jsh.eval("cat " + fileName, outs);
        String expected = readFile(fileName);
        assertEquals(expected, outs.toString().trim());
    }

    @Test
    public void testCatRedirection() throws IOException {
        String fileName = JshTest.testDirectory + File.separator + "text2.txt";
        Jsh.eval("cat < " + fileName, outs);
        String expected = readFile(fileName);
        assertEquals(expected, outs.toString().trim());
    }

    @Test
    public void testCatInputSub() {
        String[] cases = {"echo `cat testDir/text1.txt`", "abcdefghiofeijnwio"};
        Jsh.eval(cases[0], outs);
        assertEquals(cases[1], outs.toString().trim());
    }

    @Test
    public void testCatMultiFile() {
        String[] cases = {"cat testDir/text1.txt testDir/text2.txt", "abcdefghi" + System.getProperty("line.separator") +
                                                                     "ofeijnwio" + System.getProperty("line.separator") +
                                                                     "AAA" + System.getProperty("line.separator") +
                                                                     "BBB" + System.getProperty("line.separator") +
                                                                     "AAA"};
        Jsh.eval(cases[0], outs);
        assertEquals(cases[1], outs.toString().trim());
    }

    @Test
    public void testCatIo() {
        String[] cases = {"cat < " + JshTest.testDirectory + File.separator + "text1.txt", "abcdefghi" + System.getProperty("line.separator") + "ofeijnwio"};
        Jsh.eval(cases[0], outs);
        assertEquals(cases[1], outs.toString().trim());
    }

    @Test
    public void testCatDoubleQuote() {
        String[] cases = {"cat < " + JshTest.testDirectory + File.separator + "text1.txt", "abcdefghi" + System.getProperty("line.separator")+ "ofeijnwio"};
        Jsh.eval(cases[0], outs);
        assertEquals(cases[1], outs.toString().trim());
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testCatFileTest() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("cat: file does not exist");
        Cat cat = new Cat();
        ArrayList<String> args = new ArrayList<>();
        args.add("cat"); args.add("nonexistentfilename.txt");
        cat.mainExec(args, System.getProperty("user.dir"), null, outs);
    }

    @Test
    public void testCatNoArgs() throws IOException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("cat: missing arguments");
        Cat cat = new Cat();
        ArrayList<String> args = new ArrayList<>();
        args.add("cat");
        cat.mainExec(args, System.getProperty("user.dir"), null, outs);
    }
}
