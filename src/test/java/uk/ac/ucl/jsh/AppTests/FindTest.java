package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
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
        String[][] cases = {{"find *.txt", "text1.txt"}, {"find run.txt", "run.txt"}, {"find text1.txt", "text1.txt"}, {"find text2.txt", "text2.txt"}};
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

    public void runAllTests() {
        test_1();
    }
}
