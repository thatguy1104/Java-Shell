package uk.ac.ucl.jsh;

public class Factory {
    
    public Application getApp(String app) {

        switch(app) {
            case "cd": return new Cd();
            case "pwd": return new Pwd();
            case "ls": return new Ls();
            case "cat": return new Cat();
            case "echo": return new Echo();
        }
        return null;
    }
}
