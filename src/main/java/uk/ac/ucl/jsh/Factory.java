package uk.ac.ucl.jsh;

public class Factory {
    
    public Application getApp(String app) {

        switch(app) {
            case "cd": return new Cd();
            case "pwd": return new Pwd();
            case "ls": return new Ls();
            case "cat": return new Cat();
            case "echo": return new Echo();
            case "head": return new Head();
            case "tail": return new Tail();
            case "grep": return new Grep();
            case "find": return new Find();
            case "cut": return new Cut();
            case "uniq": return new Uniq();
            case "sort": return new Sort();
        }
        return null;
    }
}
