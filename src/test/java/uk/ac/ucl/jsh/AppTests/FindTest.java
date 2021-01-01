package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.Applications.Find;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FindTest extends JshTest {

    public FindTest() throws IOException {
    }

    @Test
    public void test_1() {
        String[][] cases = {{"find testDir/*.txt", "text1.txt"},
                            {"find run.txt", "run.txt"},
                            {"find text1.txt", "text1.txt"},
                            {"find testDir/text2.txt", "text2.txt"}};
        for (String[] aCase : cases) {
            File[] listOfFiles = new File(System.getProperty("user.dir")).listFiles();
            assert listOfFiles != null;
            for (File f : listOfFiles) {
                if (f.getName().equals(aCase[1])) {
                    assertEquals(f.getName(), aCase[1]);
                }
            }
        }
    }

    @Test
    public void test_2() {
        String[] cases = {"cd testDir; find -name text1.txt", "." + File.separator + "text1.txt\n" +
                                                              "." + File.separator + testSubDirectory + File.separator + "text1.txt"};
        Jsh.eval(cases[0], out);
        String result = getEvalResult(cases[1]);
        assertEquals(cases[1], result);
    }

    @Test
    public void test_3() {
        String[] cases = {"find testDir -name text1.txt", testDirectory + File.separator + testSubDirectory + File.separator + "text1.txt\n" +
                                                          testDirectory + File.separator + "text1.txt"};
        Jsh.eval(cases[0], out);
        String result = getEvalResult(cases[1]);
        assertEquals(cases[1], result);
    }

    @Test
    public void test_4() {
        String[] cases = {"cd testDir; find -name *.txt", "." + File.separator + "text2.txt\n" +
                                                          "." + File.separator + "text3.txt\n" +
                                                          "." + File.separator + "text1.txt\n" +
                                                          "." + File.separator + testSubDirectory + File.separator + "text1.txt"};
        Jsh.eval(cases[0], out);
        String result = getEvalResult(cases[1]);
        assertEquals(cases[1], result);
    }

    @Test
    public void test_5() {
        String[] cases = {"find testDir/testSubDir -name *.txt", testDirectory + File.separator + testSubDirectory + File.separator + "text1.txt"};
        Jsh.eval(cases[0], out);
        String result = getEvalResult(cases[1]);
        assertEquals(cases[1], result);
    }

    public void runAllTests() {
        test_1();
        test_2();
        test_3();
        test_4();
        test_5();
    }
}
