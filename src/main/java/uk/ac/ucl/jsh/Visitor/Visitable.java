package uk.ac.ucl.jsh.Visitor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Visitable {

    /**
     * @param visitor visitable object (includes Call, Pipe, Sequence)
     * @param inputStream input stream to use as arguments in certain cases
     * @param outputStream output stream to write to
     * @param currentDirectory to operate in
     * @return visitable object (includes Call, Pipe, Sequence)
     * @throws IOException in case of IO stream error
     */
    <T> T accept(Visitor<T> visitor, InputStream inputStream, OutputStream outputStream, String currentDirectory) throws IOException;

}
