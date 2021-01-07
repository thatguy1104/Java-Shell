package uk.ac.ucl.jsh.Visitor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @param <T> Visitable object type (includes Pipe, Seq, Call)
 */
public interface Visitor<T> {

    /**
     * Pipe Visitable Object
     * @param pipe Visitable object that inputStream visited
     * @param inputStream input stream to use as arguments in certain cases
     * @param outputStream output stream to write to
     * @param currentDirectory to operate on
     * @return correct object to be visited
     * @throws IOException thrown in case of IO stream error
     */
    T visit(Pipe pipe, InputStream inputStream, OutputStream outputStream, String currentDirectory) throws IOException;

    /**
     * Call Visitable Object
     * @param call Visitable object that inputStream visited
     * @param inputStream input stream to use as arguments in certain cases
     * @param outputStream output stream to write to
     * @param currentDirectory to operate on
     * @return correct object to be visited
     * @throws IOException thrown in case of IO stream error
     */
    T visit(Call call, InputStream inputStream, OutputStream outputStream, String currentDirectory) throws IOException;

    /**
     * Sequence Visitable Object
     * @param seq Visitable object that inputStream visited
     * @param inputStream input stream to use as arguments in certain cases
     * @param outputStream output stream to write to
     * @param currentDirectory to operate on
     * @return correct object to be visited
     * @throws IOException thrown in case of IO stream error
     */
    T visit(Seq seq, InputStream inputStream, OutputStream outputStream, String currentDirectory) throws IOException;

}
