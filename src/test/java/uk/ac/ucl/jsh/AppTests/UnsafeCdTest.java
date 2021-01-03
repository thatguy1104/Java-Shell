package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.Factory;
import uk.ac.ucl.jsh.JshTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class UnsafeCdTest extends JshTest {

    private String currDir = System.getProperty("user.dir");

    public UnsafeCdTest() throws IOException {
    }

    @Test
    public void testUnsafeChangeDir() throws IOException {
        String toFolder = "src";
        ArrayList<String> aCase = new ArrayList<>(Collections.singleton("_cd"));
        aCase.add(toFolder);
        String result_dir = currDir + File.separator + toFolder;
        String new_dir = new Factory().getApp("_cd").exec(aCase, currDir, null, this.out);
        assertEquals(result_dir, new_dir);
    }

//    @Test
//    public void testUnsafeChangeBack() throws IOException {
//        String[] args = {"cd", ".."};
//        String new_dir = new Factory().getApp("cd").exec(new ArrayList<>(Arrays.asList(args)), currDir, null, this.out);
//        int checker = currDir.lastIndexOf(File.separator);
//        String actualDirectory = currDir.substring(0, checker);
//        assertEquals(actualDirectory, new_dir);
//    }
}
