package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class LsTest extends JshTest {

    public LsTest() throws IOException {
    }

    @Test
    public void test_1() throws IOException {
        File f = new File(System.getProperty("user.dir"));
        String[] path_names = f.list();
        assert path_names != null;

        for (String file_name : path_names) {
            String full_string = eval_result("ls", file_name);
            assertEquals(full_string, file_name);
        }
    }

    public void runAllTests() throws IOException {
        test_1();
    }
}