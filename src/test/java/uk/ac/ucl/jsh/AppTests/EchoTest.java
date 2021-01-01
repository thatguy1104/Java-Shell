package uk.ac.ucl.jsh.AppTests;

import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class EchoTest extends JshTest {

    public EchoTest() throws IOException {
    }

    @Test
    public void test_simple_1() {
        String[] arg = {"echo hello world", "hello world"};
        Jsh.eval(arg[0], this.out);
        String result = pwdSupplementary(arg[1]);
        assertEquals(arg[1], result);
    }

    @Test
    public void test_simple_2() {
        String[] arg = {"echo foo", "foo"};
        Jsh.eval(arg[0], this.out);
        String result = pwdSupplementary(arg[1]);
        assertEquals(arg[1], result);
    }

    @Test
    public void input_sub() {
        String[] arg = {"`echo echo` foo", "foo"};
        Jsh.eval(arg[0], this.out);
        String result = pwdSupplementary(arg[1]);
        assertEquals(arg[1], result);
    }

    @Test
    public void test_complex_input_sub() {
        String[] arg = {"echo \"a `echo \"b\"`\"", "a b"};
        Jsh.eval(arg[0], this.out);
        String result = pwdSupplementary(arg[1]);
        assertEquals(arg[1], result);
    }

    @Test
    public void test_doube_quotes() {
        String[] arg = {"echo \"a b\"", "a b"};
        Jsh.eval(arg[0], this.out);
        String result = pwdSupplementary(arg[1]);
        assertEquals(arg[1], result);
    }

    public void runAllTests() {
        test_simple_1();
        test_simple_2();
        input_sub();
        test_complex_input_sub();
        test_doube_quotes();
    }
}
