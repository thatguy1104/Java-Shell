package uk.ac.ucl.jsh.Visitor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Call implements Visitable {
    private String visitable;

    public Call(String visitable) {
        this.visitable = visitable;
    }

    String getVisitable(){
        return this.visitable;
    }

    @Override
    public <T> T accept(Visitor<T> visitor, InputStream inputStream, OutputStream outputStream, String currentDirectory) throws IOException {
        return visitor.visit(this, inputStream, outputStream, currentDirectory);
    }
}
