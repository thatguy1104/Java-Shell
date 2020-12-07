package uk.ac.ucl.jsh;

import uk.ac.ucl.jsh.Applications.*;

public class Factory {
    
    public Application getApp(String app) {

        switch(app) {
            case "cd": return new Cd();
            case "_cd": return new Unsafe(new Cd());
            case "pwd": return new Pwd();
            case "_pwd": return new Unsafe(new Pwd());
            case "ls": return new Ls();
            case "_ls": return new Unsafe(new Ls());
            case "cat": return new Cat();
            case "_cat": return new Unsafe(new Cat());
            case "echo": return new Echo();
            case "_echo": return new Unsafe(new Echo());
            case "head": return new Head();
            case "_head": return new Unsafe(new Head());
            case "tail": return new Tail();
            case "_tail": return new Unsafe(new Tail());
            case "grep": return new Grep();
            case "_grep": return new Unsafe(new Grep());
            case "find": return new Find();
            case "_find": return new Unsafe(new Find());
            case "cut": return new Cut();
            case "_cut": return new Unsafe(new Cut());
            case "uniq": return new Uniq();
            case "_uniq": return new Unsafe(new Uniq());
            case "sort": return new Sort();
            case "_sort": return new Unsafe(new Sort());
        }
        return null;
    }
}