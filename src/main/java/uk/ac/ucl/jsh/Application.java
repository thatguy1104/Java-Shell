package uk.ac.ucl.jsh;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public interface Application {
    String exec(ArrayList<String> args, InputStream input, OutputStream output, String currDir) throws IOException;
}
