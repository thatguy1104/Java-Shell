package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class LsTest extends JshTest {

    public LsTest() throws IOException {
    }

    @Test
    public void test_1() {
        File f = new File(System.getProperty("user.dir"));
        List<String> expected = Arrays.asList(Objects.requireNonNull(f.list()));
        List<String> results = new ArrayList<>();

        for (String file_name : expected) {
            Jsh.eval("ls", this.out);
            results.add(pwdSupplementary(file_name));
        }
        Collections.sort(expected);
        Arrays.sort(results.toArray());


        for (String s : results) {
            if (expected.contains(s)) {
                assertEquals(expected.get(expected.indexOf(s)), s);
            }
        }
    }

    public void runAllTests() {
        test_1();
    }
}
