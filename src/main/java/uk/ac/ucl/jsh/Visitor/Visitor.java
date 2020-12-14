package uk.ac.ucl.jsh.Visitor;

import java.io.InputStream;
import java.io.OutputStream;

public interface Visitor<T> {
    
    T visit(Pipe pipe, InputStream is, OutputStream os, String currentDirectory);

    T visit(Call call, InputStream is, OutputStream os, String currentDirectory);

    T visit(Seq seq, InputStream is, OutputStream os, String currentDirectory);

}
