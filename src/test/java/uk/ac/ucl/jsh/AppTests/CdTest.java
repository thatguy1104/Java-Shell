package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.Factory;
import uk.ac.ucl.jsh.JshTest;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CdTest extends JshTest {

    public CdTest() throws IOException {
    }

    @Test
    public void test_1() throws IOException {
        String toFolder = "src";

        ArrayList<String> aCase = new ArrayList<>();
        aCase.add(0, toFolder);

        String curr_dir = System.getProperty("user.dir");
        String result_dir = System.getProperty("user.dir") + "/" + toFolder;

        String new_dir = new Factory().getApp("cd").exec(aCase, curr_dir, null, this.out);

        assertEquals(new_dir, result_dir);
    }

    public void runAllTests() throws IOException {
        test_1();
    }
}
