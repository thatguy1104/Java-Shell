package uk.ac.ucl.jsh.Visitor;

import java.io.InputStream;
import java.io.OutputStream;

public class Seq implements Visitable {
    private Visitable left;
    private Visitable right;

    public Seq(Visitable left, Visitable right) {
        this.left = left;
        this.right = right;
    }

    public Visitable getLeft() {
        return this.left;
    }

    public Visitable getRight() {
        return this.right;
    }

    @Override
    public <T> void accept(Visitor<T> visitor, InputStream is, OutputStream os, String currentDirectory) throws Exception {
        visitor.visit(this, is, os, currentDirectory);
    }
}
