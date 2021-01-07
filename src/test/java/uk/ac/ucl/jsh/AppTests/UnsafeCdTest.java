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
    public void testUnsafeChangeDir() throws IOException {
        String toFolder = "src";
        ArrayList<String> aCase = new ArrayList<>(Collections.singleton("_cd"));
        aCase.add(toFolder);
        String resultDir = currDir + File.separator + toFolder;
        String newDir = new Factory().getApp("_cd").exec(aCase, currDir, null, outs);
        assertEquals(resultDir, newDir);
    }
}
