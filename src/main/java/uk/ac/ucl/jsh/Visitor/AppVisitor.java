package uk.ac.ucl.jsh.Visitor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class AppVisitor<T> implements Visitor<T> {

    @Override
    public void visit(Pipe pipe, InputStream is, OutputStream os, String currentDirectory) throws Exception {
        ByteArrayOutputStream newStream = new ByteArrayOutputStream();
        pipe.getLeft().accept(this, is, newStream, currentDirectory);
        ByteArrayInputStream newInputStream = new ByteArrayInputStream(newStream.toByteArray());
        pipe.getRight().accept(this, newInputStream, os, currentDirectory);

    }

    @Override
    public void visit(Call call, InputStream is, OutputStream os, String currentDirectory) {


    }

    @Override
    public void visit(Seq seq, InputStream is, OutputStream os, String currentDirectory) {

    }
}
