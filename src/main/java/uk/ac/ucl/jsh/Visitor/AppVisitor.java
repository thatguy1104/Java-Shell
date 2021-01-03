package uk.ac.ucl.jsh.Visitor;

import uk.ac.ucl.jsh.Applications.Application;
import uk.ac.ucl.jsh.Factory;
import uk.ac.ucl.jsh.Parser.Parser;
import uk.ac.ucl.jsh.Jsh;

import java.io.*;
import java.util.ArrayList;

public class AppVisitor extends Jsh implements Visitor<Void> {

    @Override
    public Void visit(Pipe pipe, InputStream is, OutputStream os, String currentDirectory) throws IOException {
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        pipe.getLeft().accept(this, is, newOutputStream, currentDirectory);
        ByteArrayInputStream newInputStream = new ByteArrayInputStream(newOutputStream.toByteArray());
        pipe.getRight().accept(this, newInputStream, os, currentDirectory);
        return null;
    }

    @Override
    public Void visit(Call call, InputStream is, OutputStream os, String currentDirectory) throws IOException {
        ArrayList<String> tokens = Parser.parseCallCommand(call.getVisitable());

        is = getInputStream(tokens, is);
        os = getOutputStream(tokens, os);
        Factory factory = new Factory();
        assert tokens != null;
        Application app = factory.getApp(tokens.get(0));
        Jsh.currentDirectory = app.mainExec(tokens, currentDirectory, is, os);
        return null;
    }

    @Override
    public Void visit(Seq seq, InputStream is, OutputStream os, String currentDirectory) throws IOException {
        seq.getLeft().accept(this, is, os, currentDirectory);
        seq.getRight().accept(this, is,  os, Jsh.currentDirectory);
        return null;
    }

    private InputStream getInputStream(ArrayList<String> tokens, InputStream is) throws IOException {
        if (countChars(tokens, '<') > 1) {
            throw new IOException("Number of files exceeding limit for IO");
        }

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
        if (countChars(tokens, '>') > 1) {
            throw new IOException("Number of files exceeding limit for IO");
        }

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

    private int countChars(ArrayList<String> tokens, char elem) {
        int counter = 0;
        for (String i : tokens) {
            for (int j = 0; j < i.length(); j++) {
                if (i.charAt(j) == elem) counter++;
            }
        }
        return counter;
    }
}
