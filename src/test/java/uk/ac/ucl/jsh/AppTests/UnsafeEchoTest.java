package uk.ac.ucl.jsh.AppTests;
import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import static org.junit.Assert.assertEquals;

public class UnsafeEchoTest extends JshTest {

    public UnsafeEchoTest() {
    }

    @Test
    public void testUnsafeSimple1() {
        String[] arg = {"_echo hello world", "hello world"};
        Jsh.eval(arg[0], outs);
        assertEquals(arg[1], outs.toString().trim());
    }

    @Test
    public void testUnsafeSimple2() {
        String[] arg = {"_echo foo", "foo"};
        Jsh.eval(arg[0], outs);
        assertEquals(arg[1], outs.toString().trim());
    }

    @Test
    public void testUnsafeInputSub() {
        String[] arg = {"`_echo _echo` foo", "foo"};
        Jsh.eval(arg[0], outs);
        assertEquals(arg[1], outs.toString().trim());
    }

    @Test
    public void testComplexUnsafeInputSub() {
        String[] arg = {"_echo \"a `_echo \"b\"`\"", "a b"};
        Jsh.eval(arg[0], outs);
        assertEquals(arg[1], outs.toString().trim());
    }

    @Test
    public void testUnsafeDoubleQuotes() {
        String[] arg = {"_echo \"a b\"", "a b"};
        Jsh.eval(arg[0], outs);
        assertEquals(arg[1], outs.toString().trim());
    }
}
