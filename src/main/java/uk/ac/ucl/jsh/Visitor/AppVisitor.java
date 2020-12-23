package uk.ac.ucl.jsh.Visitor;

import uk.ac.ucl.jsh.Factory;
import uk.ac.ucl.jsh.Parser.Parser;

import java.io.*;
import java.util.ArrayList;

public class AppVisitor<T> implements Visitor<T> {

    @Override
    public void visit(Pipe pipe, InputStream is, OutputStream os, String currentDirectory) throws Exception {
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        pipe.getLeft().accept(this, is, newOutputStream, currentDirectory);
        ByteArrayInputStream newInputStream = new ByteArrayInputStream(newOutputStream.toByteArray());
        pipe.getRight().accept(this, newInputStream, os, currentDirectory);
    }

    @Override
    public void visit(Call call, InputStream is, OutputStream os, String currentDirectory) throws Exception {
        ArrayList<String> tokens = Parser.parseCallCommand(call.getVisitable());

        is = getInputStream(tokens, is);
        os = getOutputStream(tokens, os);
        Factory factory = new Factory();
        assert tokens != null;
        factory.getApp(tokens.get(0)).mainExec(tokens, currentDirectory, is, os);
    }

    @Override
    public void visit(Seq seq, InputStream is, OutputStream os, String currentDirectory) throws Exception {
        seq.getLeft().accept(this, is, os, currentDirectory);
        seq.getRight().accept(this, is,  os, currentDirectory);
    }

    private InputStream getInputStream(ArrayList<String> tokens, InputStream is) throws Exception {
        if (countChars(tokens, '<') > 1) {
            throw new Exception("IO Re-direction: Too many files for input redirection");
        }

        return null;
    }

    private OutputStream getOutputStream(ArrayList<String> tokens, OutputStream os) throws Exception {
        if (countChars(tokens, '>') > 1) {
            throw new Exception("IO Re-direction: Too many files for input redirection");
        }

        return null;
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
