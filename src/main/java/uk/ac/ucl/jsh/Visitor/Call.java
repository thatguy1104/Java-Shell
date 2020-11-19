package uk.ac.ucl.jsh.Visitor;

public class Call implements Visitable {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
