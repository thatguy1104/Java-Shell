package uk.ac.ucl.jsh.Visitor;

import java.io.InputStream;
import java.io.OutputStream;

public interface Visitor<T> {
    
    void visit(Pipe pipe, InputStream is, OutputStream os, String currentDirectory) throws Exception;

    void visit(Call call, InputStream is, OutputStream os, String currentDirectory);

    void visit(Seq seq, InputStream is, OutputStream os, String currentDirectory);

}
