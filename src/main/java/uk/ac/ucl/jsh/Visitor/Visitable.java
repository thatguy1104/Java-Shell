package uk.ac.ucl.jsh.Visitor;
import java.io.InputStream;
import java.io.OutputStream;

public interface Visitable {

    public abstract <T> T accept(Visitor<T> visitor, InputStream is, OutputStream os, String currentDirectory) throws Exception;

    //void accept(Visitor visitor);

}
