package uk.ac.ucl.jsh.AppTests;
import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import static org.junit.Assert.assertEquals;

public class UnsafeEchoTest extends JshTest {

    public UnsafeEchoTest() {
    }

    @Test
    public void test_unsafe_simple_1() {
        String[] arg = {"_echo hello world", "hello world"};
        Jsh.eval(arg[0], outs);
        assertEquals(arg[1], outs.toString().trim());
    }

    @Test
    public void test_unsafe_simple_2() {
        String[] arg = {"_echo foo", "foo"};
        Jsh.eval(arg[0], outs);
        assertEquals(arg[1], outs.toString().trim());
    }

    @Test
    public void test_unsafe_input_sub() {
        String[] arg = {"`_echo _echo` foo", "foo"};
        Jsh.eval(arg[0], outs);
        assertEquals(arg[1], outs.toString().trim());
    }

    @Test
    public void test_complex_unsafe_input_sub() {
        String[] arg = {"_echo \"a `_echo \"b\"`\"", "a b"};
        Jsh.eval(arg[0], outs);
        assertEquals(arg[1], outs.toString().trim());
    }

    @Test
    public void test_unsafe_double_quotes() {
        String[] arg = {"_echo \"a b\"", "a b"};
        Jsh.eval(arg[0], outs);
        assertEquals(arg[1], outs.toString().trim());
    }
}
