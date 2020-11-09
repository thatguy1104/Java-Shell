package uk.ac.ucl.jsh;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public interface Application {

    // app => cd, pwd
    String exec(ArrayList<String> args, String currDir, OutputStream output) throws IOException;

}
