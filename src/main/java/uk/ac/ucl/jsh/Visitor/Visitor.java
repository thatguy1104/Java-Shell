package uk.ac.ucl.jsh.Visitor;

public interface Visitor<T> {
    
    void visit(Pipe pipe);

    void visit(Call call);

    void visit(Seq seq);
}
