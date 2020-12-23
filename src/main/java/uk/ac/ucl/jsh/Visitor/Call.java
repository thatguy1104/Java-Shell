package uk.ac.ucl.jsh.Visitor;

import java.io.InputStream;
import java.io.OutputStream;

public class Call implements Visitable {
    private String visitable;

    public Call(String visitable) {
        this.visitable = visitable;
    }

    public String getVisitable(){
        return this.visitable;
    }

    @Override
    public <T> void accept(Visitor<T> visitor, InputStream is, OutputStream os, String currentDirectory) throws Exception {
        visitor.visit(this, is, os, currentDirectory);
    }
}
