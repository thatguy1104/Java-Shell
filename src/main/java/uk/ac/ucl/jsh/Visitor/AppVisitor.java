package uk.ac.ucl.jsh.Visitor;

import uk.ac.ucl.jsh.Applications.Application;
import uk.ac.ucl.jsh.Factory;
import uk.ac.ucl.jsh.Parser.Parser;
import uk.ac.ucl.jsh.Jsh;

import java.io.*;
import java.util.ArrayList;

public class AppVisitor extends Jsh implements Visitor<Void> {

    @Override
    public Void visit(Pipe pipe, InputStream inputStream, OutputStream outputStream, String currentDirectory) throws IOException {
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        pipe.getLeft().accept(this, inputStream, newOutputStream, currentDirectory);
        ByteArrayInputStream newInputStream = new ByteArrayInputStream(newOutputStream.toByteArray());
        pipe.getRight().accept(this, newInputStream, outputStream, currentDirectory);
        return null;
    }

    @Override
    public Void visit(Call call, InputStream inputStream, OutputStream outputStream, String currentDirectory) throws IOException {
        ArrayList<String> tokens = Parser.parseCallCommand(call.getVisitable());

        inputStream = getInputStream(tokens, inputStream);
        outputStream = getOutputStream(tokens, outputStream);
        Factory factory = new Factory();
        Application app = factory.getApp(tokens.get(0));
        Jsh.currentDirectory = app.mainExec(tokens, currentDirectory, inputStream, outputStream);
        return null;
    }

    @Override
    public Void visit(Seq seq, InputStream inputStream, OutputStream outputStream, String currentDirectory) throws IOException {
        seq.getLeft().accept(this, inputStream, outputStream, currentDirectory);
        seq.getRight().accept(this, inputStream, outputStream, Jsh.currentDirectory);
        return null;
    }

    private InputStream getInputStream(ArrayList<String> tokens, InputStream is) throws IOException {
        try {
            int position = tokens.indexOf("<");
            if (position != -1 && position + 1 < tokens.size()) {
                is = new FileInputStream(new File(tokens.get(position + 1)));
                tokens.subList(position, position + 2).clear();
            }
        } catch (FileNotFoundException e) {
            throw new IOException(e.getMessage());
        }
        return is;
    }

    private OutputStream getOutputStream(ArrayList<String> tokens, OutputStream os) throws IOException {
        try {
            int position = tokens.indexOf(">");
            if (position != -1 && position + 1 < tokens.size()) {
                os = new FileOutputStream(new File(tokens.get(position + 1)));
                tokens.subList(position, position + 2).clear();
            }
        } catch (FileNotFoundException e) {
            throw new IOException(e.getMessage());
        }
        return os;
    }
}
