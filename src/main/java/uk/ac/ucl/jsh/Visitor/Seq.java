package uk.ac.ucl.jsh.Visitor;

public class Seq implements Visitable {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
