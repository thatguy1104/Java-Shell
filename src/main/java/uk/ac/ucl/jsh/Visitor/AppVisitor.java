package uk.ac.ucl.jsh.Visitor;

public class AppVisitor implements Visitor {

    @Override
    public void visit(Pipe pipe) {
        System.out.println("Pipe");
        
    }

    @Override
    public void visit(Call call) {
        System.out.println("Call");

    }

    @Override
    public void visit(Seq Seq) {
        System.out.println("Seq");

    }
    
}
