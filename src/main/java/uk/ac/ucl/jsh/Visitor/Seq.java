package uk.ac.ucl.jsh.Visitor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Seq implements Visitable {
    private Visitable left;
    private Visitable right;

    Seq(Visitable left, Visitable right) {
        this.left = left;
        this.right = right;
    }

    Visitable getLeft() {
        return this.left;
    }

    Visitable getRight() {
        return this.right;
    }

    @Override
    public <T> T accept(Visitor<T> visitor, InputStream is, OutputStream os, String currentDirectory) throws IOException {
        return visitor.visit(this, is, os, currentDirectory);
    }
}
