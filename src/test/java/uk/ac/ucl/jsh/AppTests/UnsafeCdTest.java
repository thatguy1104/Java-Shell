package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.Factory;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class UnsafeCdTest extends JshTest {

    private String currDir = System.getProperty("user.dir");

    public UnsafeCdTest() {
    }

    @Test
    public void test_unsafe_change_dir() throws IOException {
        String toFolder = "src";
        ArrayList<String> aCase = new ArrayList<>(Collections.singleton("_cd"));
        aCase.add(toFolder);
        String result_dir = currDir + File.separator + toFolder;
        String new_dir = new Factory().getApp("_cd").exec(aCase, currDir, null, outs);
        assertEquals(result_dir, new_dir);
    }
}
