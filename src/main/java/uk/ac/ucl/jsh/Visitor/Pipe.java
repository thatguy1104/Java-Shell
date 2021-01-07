package uk.ac.ucl.jsh.Visitor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Pipe implements Visitable {
    private Visitable left;
    private Visitable right;

    Pipe(Visitable left, Visitable right) {
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
    public <T> T accept(Visitor<T> visitor, InputStream inputStream, OutputStream outputStream, String currentDirectory) throws IOException {
        return visitor.visit(this, inputStream, outputStream, currentDirectory);
    }
}
