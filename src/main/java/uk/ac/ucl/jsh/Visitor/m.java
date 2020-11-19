package uk.ac.ucl.jsh.Visitor;

public class m {
    
    public static void main(String[] args) {
        AppVisitor visitor = new AppVisitor();

        Pipe pipe = new Pipe();
        pipe.accept(visitor);
    }
}
