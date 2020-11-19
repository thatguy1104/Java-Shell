package uk.ac.ucl.jsh.Visitor;

public class Pipe implements Visitable {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);

    }
}
