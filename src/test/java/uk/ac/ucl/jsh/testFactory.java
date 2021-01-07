package uk.ac.ucl.jsh;

import org.junit.Test;
import uk.ac.ucl.jsh.Applications.Application;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class testFactory extends JshTest {

    public testFactory() {
    }

    @Test
    public void test() {
        String[] allApps = {"cd", "cat", "cut", "pwd", "echo", "find", "grep", "head", "sort", "tail", "uniq", "ls"};
        for (String appName : allApps) {
            Application application = new Factory().getApp(appName);
            assertNotNull(application);
        }
    }
}
