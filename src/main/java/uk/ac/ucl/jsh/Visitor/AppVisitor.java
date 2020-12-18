package uk.ac.ucl.jsh.Visitor;

import java.io.InputStream;
import java.io.OutputStream;

public class AppVisitor<T> implements Visitor<T> {
    @Override
    public T visit(Pipe pipe, InputStream is, OutputStream os, String currentDirectory) {
        return null;
    }

    @Override
    public T visit(Call call, InputStream is, OutputStream os, String currentDirectory) {
        return null;
    }

    @Override
    public T visit(Seq seq, InputStream is, OutputStream os, String currentDirectory) {
        return null;
    }
}
