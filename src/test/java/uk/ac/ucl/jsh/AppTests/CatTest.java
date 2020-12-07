package uk.ac.ucl.jsh.AppTests;
import org.junit.Test;
import uk.ac.ucl.jsh.Jsh;
import uk.ac.ucl.jsh.JshTest;
import java.io.IOException;
import static org.junit.Assert.assertEquals;

public class CatTest extends JshTest {

    public CatTest() throws IOException {
    }

    @Test
    public void test_1() throws IOException {
        String file_name = "text1.txt";
        Jsh.eval("cat " + file_name, out);
        String file_contents = readFile(file_name);
        String full_string = full_line(file_contents);
        assertEquals(full_string, file_contents);
    }

    public void runAllTests() throws IOException {
        test_1();
    }
}
